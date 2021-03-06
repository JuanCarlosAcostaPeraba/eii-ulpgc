// Prueba concurrente de acceso al búfer
// Se lanzan hilos productores y consumidores
// Al no haber sincronización entre los hilos,
// el resultado es que se puede corromper el búfer

//Archivos cabecera del sistema
#include <stdlib.h> // atoi(), exit()...
#include <stdio.h>  // printf(), fputs(), stderr...
#include <string.h>
#include <errno.h>
#include <pthread.h>
#include <unistd.h>
#include "buffer_circular.h"

// variables condicion
pthread_cond_t condProd = PTHREAD_COND_INITIALIZER;
pthread_cond_t condCons = PTHREAD_COND_INITIALIZER;

// cerrojo
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

int nproductores;
int nconsumidores;

// Función auxiliar para comprobar errores
// e imprimir un mensaje si hay error
void check_error(int return_code, const char* msg)
{
  if (return_code!=0)
  {
    if (msg != NULL) {
      fprintf(stderr,"%s\n",msg);
    } else {
      fprintf(stderr,"error %d: %s\n",errno,strerror(errno));
    }
    exit(-1);
  }
}

// Hilo Productor: inserta un item en el búfer
void* Productor (void *item)
{		
	sleep((rand()%1000000)/100000);
	//toma el cerrejo 	
	pthread_mutex_lock(&mutex);
  	// espera mientras el búfer está lleno
  	while ( buffer_pendientes_consumir() == buffer_capacidad()) {
    	    if (nconsumidores == 0) {
		printf("Búfer lleno y no hay consumidores, no se puede insertar nada\n");
		nproductores--;
		pthread_cond_broadcast(&condProd);
		pthread_mutex_unlock(&mutex);
		pthread_exit(NULL);
	    } else pthread_cond_wait(&condProd, &mutex);
        }
    for (int i = 0; i < 2; i++) 
    {
    	if (buffer_pendientes_consumir() != buffer_capacidad()) {
    	buffer_inserta((ITEM)item);
    	}
		else break;
    }
	pthread_cond_broadcast(&condCons);
	nproductores--;
	pthread_mutex_unlock(&mutex);
	pthread_exit(NULL);
}

// Hilo Consumidor: extrae un item del búfer
void* Consumidor (void* no_se_usa)
{
	sleep((rand()%1000000)/100000);
	// toma el cerrojo
	pthread_mutex_lock(&mutex);
  	// espera si el búfer está vacío
  	while ( buffer_pendientes_consumir() == 0) {
	    if (nproductores == 0) {
		printf("El búfer está vacío y no hay productores, no se puede extraer nada\n");
		nconsumidores--;
		pthread_cond_broadcast(&condCons);
		pthread_mutex_unlock(&mutex);
		pthread_exit(NULL);
	    }
	    pthread_cond_wait(&condCons, &mutex);
  	}
  	ITEM item;
  	for (int i = 0; i < 3; i++) 
  	{
  		if (buffer_pendientes_consumir() != 0) {
  			buffer_extrae(&item);
  		}
  		else break;
	}
  	pthread_cond_broadcast(&condProd);
	nconsumidores--;
  	pthread_mutex_unlock(&mutex);
  	printf("Consumidor extrae: %ld\n",item);
	pthread_exit(NULL);
}

// Función auxiliar:
// espera a que termine un lote de hilos
// el lote es un vector de pthread_t
void espera_lote_hilos (pthread_t* lote, int nhilos)
{
	void* dummy; // pthread_join obliga a pasar un void*
	
	for (int i=0;i<nhilos;i++)
	{
		int ret=pthread_join(lote[i],&dummy);
		check_error(ret,0);
	}
}

// Rutina que lanza los hilos productores y consumidores
void test_hilos (int nprod, int ncons)
{
  	// vectores de hilos (de tamaño variable) 
  	pthread_t productores[nprod];
  	pthread_t consumidores[ncons];

	long contProductor = nprod;
	long contConsumidor = ncons;

	int random;
	void* item;
	int ret;

  	nproductores = nprod;
  	nconsumidores = ncons;

	while (contProductor != 0 && contConsumidor != 0) {

    	// creamos un numero random para ver quien llega primero
        random = rand() % 2;    // de 0 a 1

        if (random == 0) {
            contProductor--;
            item = (void*)contProductor;
            ret = pthread_create(&productores[contProductor], NULL, Productor, item);
            check_error(ret, 0);
        }
        else {
            contConsumidor--;
            item = (void*)contConsumidor;
            ret = pthread_create(&consumidores[contConsumidor], NULL, Consumidor, NULL);
            check_error(ret, 0);
        }

    }

    if (contConsumidor == 0 && contProductor != 0) {
        while (contProductor != 0) {
            contProductor--;
            item = (void*)contProductor;
            ret = pthread_create(&productores[contProductor], NULL, Productor, item);
            check_error(ret, 0);
        }
    } 
    else  if (contProductor == 0 && contConsumidor != 0) {
        while (contConsumidor != 0) {
            contConsumidor--;
            ret = pthread_create(&consumidores[contConsumidor], NULL, Consumidor, NULL);
            check_error(ret, 0);
        }
    }

  	// Esperamos a que todos los hilos finalicen
  	espera_lote_hilos(productores,nprod);
  	espera_lote_hilos(consumidores,ncons);
}


// Estructura con los parámetros de configuración
// para la simulación (se rellenan desde
// los argumentos argc y argv)
struct config {
	int capacidad;
	int nprod;
	int ncons;
};


// Función que comprueba y captura los parámetros pasados por línea de órdenes
void lee_argumentos (int argc, char *argv[], struct config* config)
{
	check_error( argc != 4, 
    "Invocación incorrecta. Sintaxis: ./fich_ejec capacidad num_prod num_cons");

	config->capacidad = atoi(argv[1]);
	check_error( config->capacidad < 1, 
    "Invocación incorrecta. La dimensión del búfer debe ser mayor que cero.");
	
	config->nprod = atoi(argv[2]);
	check_error( config->nprod < 0, 
    "Invocación incorrecta. El número de hilos productores debe ser mayor que cero.");

	config->ncons = atoi( argv[3] );
	check_error( config->ncons < 0, 
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
	lee_argumentos(argc,argv,&configuracion);

	srand(getpid());
	int ret = buffer_inicia(configuracion.capacidad);
	check_error(ret,"Error al crear el búfer");

	test_hilos(configuracion.nprod,configuracion.ncons);
	puts("»»» Fin de la prueba del búfer");
	exit(0);
}

