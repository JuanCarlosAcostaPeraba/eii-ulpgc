// Prueba concurrente de acceso al búfer
// Se lanzan hilos productores y consumidores
// Al no haber sincronización entre los hilos,
// el resultado es que se puede corromper el búfer

// Archivos cabecera del sistema
#include <stdlib.h> // atoi(), exit()...
#include <stdio.h>	// printf(), fputs(), stderr...
#include <string.h>
#include <errno.h>
#include <pthread.h>
#include <unistd.h>
#include "buffer_circular.h"

// Variables condicionales para controlar la espera de los hilos
pthread_cond_t productores = PTHREAD_COND_INITIALIZER;
pthread_cond_t consumidores = PTHREAD_COND_INITIALIZER;

// Variable mutex para controlar que los hilos esperen su turno de forma no competitiva
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

// Variables globales
int numProductores;
int numConsumidores;

// Función auxiliar para comprobar errores
// e imprimir un mensaje si hay error
void check_error(int return_code, const char *msg)
{
	if (return_code != 0)
	{
		if (msg != NULL)
		{
			fprintf(stderr, "%s\n", msg);
		}
		else
		{
			fprintf(stderr, "error %d: %s\n", errno, strerror(errno));
		}
		exit(-1);
	}
}

// Hilo Productor: inserta un item en el búfer
void *Productor(void *item)
{
	// lanza una espera aleatoria
	sleep((rand() % 1000000) / 100000);
	// bloqueo del mutex
	pthread_mutex_lock(&mutex);
	// espera mientras el búfer está lleno
	while (buffer_pendientes_consumir() == buffer_capacidad())
	{
		// si no hay ningún consumidores
		if (numConsumidores == 0)
		{
			printf("No quedan consumidores\n");
			// eliminamos un productor
			numProductores--;
			// desbloqueo de todos los hilos productores que están esperando
			pthread_cond_broadcast(&productores);
			// desbloqueo del mutex
			pthread_mutex_unlock(&mutex);
			// termina la llamada del hilo productor
			pthread_exit(NULL);
		}
		else
		{ // si hay consumidores, se produce una espera hasta que se produzca una señal
			pthread_cond_wait(&productores, &mutex);
		}
	}
	// se inserta el item en el búfer
	for (int i = 0; i < 2; i++)
	{
		// si hay espacio en el buffer
		if (buffer_pendientes_consumir() != buffer_capacidad())
		{
			// se inserta el item en el buffer
			buffer_inserta((ITEM)item);
		}
		else
		{ // si no hay espacio en el buffer se rompe el bucle
			break;
		}
	}
	// desbloqueo todos los hilos consumidores que están esperando
	pthread_cond_broadcast(&consumidores);
	// eliminamos un productor
	numProductores--;
	// desbloqueo del mutex
	pthread_mutex_unlock(&mutex);
	// termina la llamada del hilo productor
	pthread_exit(NULL);
}

// Hilo Consumidor: extrae un item del buffer
void *Consumidor(void *no_se_usa)
{
	// lanza una espera aleatoria
	sleep((rand() % 1000000) / 100000);
	// bloqueo del mutex
	pthread_mutex_lock(&mutex);
	// espera si el buffer está vacío
	while (buffer_pendientes_consumir() == 0)
	{
		// si no hay ningún productor
		if (numProductores == 0)
		{
			printf("No quedan productores\n");
			// eliminamos un consumidor
			numConsumidores--;
			// desbloqueo de todos los hilos consumidores que están esperando
			pthread_cond_broadcast(&consumidores);
			// desbloqueo del mutex
			pthread_mutex_unlock(&mutex);
			// termina la llamada del hilo consumidor
			pthread_exit(NULL);
		}
		else
		{ // si hay productores, se produce una espera hasta que se produzca una señal
			pthread_cond_wait(&consumidores, &mutex);
		}
	}
	// se llama un item del búfer
	ITEM item;
	// si hay espacio en el buffer
	for (int i = 0; i < 3; i++)
	{
		// si hay espacio en el buffer
		if (buffer_pendientes_consumir() != 0)
		{
			// se extrae el item del buffer
			buffer_extrae(&item);
		}
		else
		{ // si no hay espacio en el buffer se rompe el bucle
			break;
		}
	}
	// desbloqueo todos los hilos productores que están esperando
	pthread_cond_broadcast(&productores);
	// eliminamos un consumidor
	numConsumidores--;
	// desbloqueo del mutex
	pthread_mutex_unlock(&mutex);
	// termina la llamada del hilo consumidor
	pthread_exit(NULL);
}

// Función auxiliar:
// espera a que termine un lote de hilos
// el lote es un vector de pthread_t
void espera_lote_hilos(pthread_t *lote, int nhilos)
{
	void *dummy; // pthread_join obliga a pasar un void*

	for (int i = 0; i < nhilos; i++)
	{
		int ret = pthread_join(lote[i], &dummy);
		check_error(ret, 0);
	}
}

// Rutina que lanza los hilos productores y consumidores
void test_hilos(int nprod, int ncons)
{
	// vectores de hilos (de tamaño variable)
	pthread_t productores[nprod];
	pthread_t consumidores[ncons];

	long contProductor = nprod;
	long contConsumidor = ncons;

	// se establecen los números de productores y consumidores
	numProductores = nprod;
	numConsumidores = ncons;

	int random;
	void *item;
	int ret;

	while (contProductor != 0 && contConsumidor != 0)
	{

		// creamos un numero random para ver quien llega primero
		random = rand() % 2; // de 0 a 1

		if (random == 0)
		{
			contProductor--;
			item = (void *)contProductor;
			ret = pthread_create(&productores[contProductor], NULL, Productor, item);
			check_error(ret, 0);
		}
		else
		{
			contConsumidor--;
			item = (void *)contConsumidor;
			ret = pthread_create(&consumidores[contConsumidor], NULL, Consumidor, NULL);
			check_error(ret, 0);
		}
	}

	if (contConsumidor == 0 && contProductor != 0)
	{
		while (contProductor != 0)
		{
			contProductor--;
			item = (void *)contProductor;
			ret = pthread_create(&productores[contProductor], NULL, Productor, item);
			check_error(ret, 0);
		}
	}
	else if (contProductor == 0 && contConsumidor != 0)
	{
		while (contConsumidor != 0)
		{
			contConsumidor--;
			ret = pthread_create(&consumidores[contConsumidor], NULL, Consumidor, NULL);
			check_error(ret, 0);
		}
	}

	// Esperamos a que todos los hilos finalicen
	espera_lote_hilos(productores, nprod);
	espera_lote_hilos(consumidores, ncons);
}

// Estructura con los parámetros de configuración
// para la simulación (se rellenan desde
// los argumentos argc y argv)
struct config
{
	int capacidad;
	int nprod;
	int ncons;
};

// Función que comprueba y captura los parámetros pasados por línea de órdenes
void lee_argumentos(int argc, char *argv[], struct config *config)
{
	check_error(argc != 4,
				"Invocación incorrecta. Sintaxis: ./fich_ejec capacidad num_prod num_cons");

	config->capacidad = atoi(argv[1]);
	check_error(config->capacidad < 1,
				"Invocación incorrecta. La dimensión del búfer debe ser mayor que cero.");

	config->nprod = atoi(argv[2]);
	check_error(config->nprod < 0,
				"Invocación incorrecta. El número de hilos productores debe ser mayor que cero.");

	config->ncons = atoi(argv[3]);
	check_error(config->ncons < 0,
				"Invocación incorrecta. El número de hilos consumidores debe ser mayor que cero.");
}

// Función main:
// Invocación:
// 	# ./fich_ejec capacidad num_prod num_cons
// 	fich_ejec: nombre del archivo que contiene el código ejecutable
// 	capacidad: capacidad máxima del búfer (>0)
// 	num_prod: número de hilos productores (>=0)
// 	num_cons: número de hilos consumidores (>=0)
//
int main(int argc, char *argv[])
{
	struct config configuracion;

	puts("»»» Comienza la prueba del búfer...");
	lee_argumentos(argc, argv, &configuracion);

	srand(getpid());
	int ret = buffer_inicia(configuracion.capacidad);
	check_error(ret, "Error al crear el búfer");

	test_hilos(configuracion.nprod, configuracion.ncons);
	puts("»»» Fin de la prueba del búfer");
	exit(0);
}
