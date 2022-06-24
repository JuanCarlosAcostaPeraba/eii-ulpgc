// Prueba concurrente de acceso al búfer
// Se lanzan hilos productores y consumidores
// Al no haber sincronización entre los hilos,
// el resultado es que se puede corromper el búfer

// Archivos cabecera del sistema
#include <stdlib.h> // atoi(), exit()...
#include <stdio.h>  // printf(), fputs(), stderr...
#include <string.h>
#include <errno.h>
#include <pthread.h>
#include <unistd.h>
#include "buffer.h"

// Variables globales
int numMisionerosEnBarco = 0;
int numCanibalesEnBarco = 0;
int numMisionerosEnTierra;
int numCanibalesEnTierra;
int flag = 0;

/**
 * @brief Función para comprobar errores
 *
 * @param return_code Código de retorno de la función
 * @param msg Mensaje de error
 */
void checkError(int return_code, const char *msg)
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

/**
 * @brief Función para crear un hilo misionero
 *
 * @param item Item a insertar en el búfer
 * @return void*
 */
void *Misionero(void *item)
{
    // lanza una espera aleatoria
    sleep((rand() % 1000000) / 100000);
    // mientras el bote esté lleno o hay 1 misionero y un canibal en el bote o el flag esté en 1, espera
    while (buffer_pendientes_consumir() == buffer_capacidad() || (numMisionerosEnBarco == 1 && numCanibalesEnBarco == 1) || flag == 1)
    {
    }
    // en tierra quedan 3 o menos personas
    if (numMisionerosEnTierra + numCanibalesEnTierra <= 3)
    {
        // caso que no se puede dar
        if (numMisionerosEnTierra == 2 && numCanibalesEnTierra == 1)
        {
            // imprimir mensaje de error
            printf("[*] ERROR\t- %d misioneros y %d canibales es un caso que no se puede dar\n", numMisionerosEnTierra, numCanibalesEnTierra);
            // termina el hilo
            pthread_exit(NULL);
        }
        else if (numMisionerosEnTierra + numCanibalesEnTierra < 3) // caso en el que el bote no se puede llenar
        {
            // imprimir mensaje de error
            printf("[*] ERROR\t- No hay suficientes pasajeros para zarpar\n");
            // termina el hilo
            pthread_exit(NULL);
        }
    }
    // llega un misionero
    ITEM misioneroItem;
    // si el bote no está lleno y el flag está en 0
    if (buffer_pendientes_consumir() != buffer_capacidad() && flag == 0)
    {
        // se mete un misionero
        buffer_inserta(misioneroItem);
        // se incrementa el número de misioneros en el bote
        numMisionerosEnBarco++;
        // imprimir mensaje
        printf("[*] Llega un misionero\t- %d misioneros y %d canibales en el barco\n", numMisionerosEnBarco, numCanibalesEnBarco);
        // si se llena el bote
        if (buffer_pendientes_consumir() == buffer_capacidad())
        {
            // el flag se pone en 1
            flag = 1;
            // imprime mensaje de que el bote zarpa
            printf("[*] El bote zarpa\t- %d misioneros, %d canibales\n", numMisionerosEnBarco, numCanibalesEnBarco);
        }
    }
    // el misionero ya se encuentra en el bote
    // mientras el flag no esté en 1
    while (flag == 0)
    {
    }
    // si el flag está en 1
    if (flag == 1)
    {
        // se saca el hilo misionero del buffer
        buffer_extrae(&misioneroItem);
        // se decrementa el número de misioneros en el bote
        numMisionerosEnBarco--;
        // se decrementa el número de misioneros en tierra
        numMisionerosEnTierra--;
        // imprimir mensaje
        printf("[*] El misionero sale del bote\t- %d misioneros y %d canibales en el barco\n", numMisionerosEnBarco, numCanibalesEnBarco);
        // si no quedan hilos pendientes por consumir
        if (buffer_pendientes_consumir() == 0)
        {
            // el flag se pone en 0
            flag = 0;
            // imprime mensaje de qque no quedan hilos pendientes por consumir
            printf("[*] No quedan hilos pendientes por consumir\n");
        }
    }
    // termina el hilo
    pthread_exit(NULL);
}

/**
 * @brief Función para crear un hilo canibal
 *
 * @param item Item a insertar en el búfer
 * @return void*
 */
void *Canibal(void *item)
{
    // lanza una espera aleatoria
    sleep((rand() % 1000000) / 100000);
    // mientras el bote esté lleno o hay 1 misionero y un canibal en el bote o el flag esté en 1, espera
    while ((buffer_pendientes_consumir() == buffer_capacidad()) || (numMisionerosEnBarco == 2) || flag == 1)
    {
    }
    // en tierra quedan 3 o menos personas
    if (numMisionerosEnTierra + numCanibalesEnTierra <= 3)
    {
        // caso que no se puede dar
        if (numMisionerosEnTierra == 2 && numCanibalesEnTierra == 1)
        {
            // imprimir mensaje de error
            printf("[*] ERROR\t- %d misioneros y %d canibales es un caso que no se puede dar\n", numMisionerosEnTierra, numCanibalesEnTierra);
            // termina el hilo
            pthread_exit(NULL);
        }
        else if (numMisionerosEnTierra + numCanibalesEnTierra < 3)
        { // caso en el que el bote no se puede llenar
            // imprimir mensaje
            printf("[*] ERROR\t- No hay suficientes pasajeros para zarpar\n");
            // termina el hilo
            pthread_exit(NULL);
        }
    }
    // llega un canibal
    ITEM canibal;
    // si el bote no está lleno y el flag está en 0
    if (buffer_pendientes_consumir() != buffer_capacidad() && flag == 0)
    {
        // se mete un canibal
        buffer_inserta(canibal);
        // se incrementa el número de canibales en el bote
        numCanibalesEnBarco++;
        // imprimir mensaje
        printf("[*] Llega un canibal\t- %d misioneros y %d canibales en el barco\n", numMisionerosEnBarco, numCanibalesEnBarco);
        // si se llena el bote
        if (buffer_pendientes_consumir() == buffer_capacidad())
        {
            // el flag se pone en 1
            flag = 1;
            // imprime mensaje de que el bote zarpa
            printf("[*] El bote zarpa\t- %d misioneros, %d canibales\n", numMisionerosEnBarco, numCanibalesEnBarco);
        }
    }
    // mientras el flag no esté en 1
    while (flag == 0)
    {
    }
    // si el flag está en 1
    if (flag == 1)
    {
        // se saca el hilo canibal del buffer
        buffer_extrae(&canibal);
        // se decrementa el número de canibales en el bote
        numCanibalesEnBarco--;
        // se decrementa el número de canibales en tierra
        numCanibalesEnTierra--;
        // imprimir mensaje
        printf("[*] El canibal sale del bote\t- %d misioneros y %d canibales en el barco\n", numMisionerosEnBarco, numCanibalesEnBarco);
        // si no quedan hilos pendientes por consumir
        if (buffer_pendientes_consumir() == 0)
        {
            // el flag se pone en 0
            flag = 0;
            // imprime mensaje de qque no quedan hilos pendientes por consumir
            printf("[*] No quedan hilos pendientes por consumir\n");
        }
    }
    // termina el hilo
    pthread_exit(NULL);
}

// Funcion auxiliar:
// espera a que termine un lote de hilos
// el lote es un vector de pthread_t
void esperaLoteHilos(pthread_t *lote, int nhilos)
{
    void *dummy; // pthread_join obliga a pasar un void*

    for (int i = 0; i < nhilos; i++)
    {
        int ret = pthread_join(lote[i], &dummy);
        checkError(ret, 0);
    }
}

// Rutina que lanza los hilos
void testHilos(int nmisioneros, int ncanibales)
{
    // vectores de hilos (de tamaño variable)
    pthread_t misioneros[nmisioneros];
    pthread_t canibales[ncanibales];

    long contMisio = nmisioneros;
    long contCanib = ncanibales;

    int random;
    void *item;
    int ret;

    while (contCanib != 0 && contMisio != 0)
    {

        // creamos un numero random para ver quien llega primero
        random = rand() % 2; // de 0 a 1

        if (random == 0)
        {
            contMisio--;
            item = (void *)contMisio;
            ret = pthread_create(&misioneros[contMisio], NULL, Misionero, item);
            checkError(ret, 0);
        }
        else
        {
            contCanib--;
            item = (void *)contCanib;
            ret = pthread_create(&canibales[contCanib], NULL, Canibal, item);
            checkError(ret, 0);
        }
    }

    if (contCanib == 0 && contMisio != 0)
    {
        while (contMisio != 0)
        {
            contMisio--;
            item = (void *)contMisio;
            ret = pthread_create(&misioneros[contMisio], NULL, Misionero, item);
            checkError(ret, 0);
        }
    }
    else if (contMisio == 0 && contCanib != 0)
    {
        while (contCanib != 0)
        {
            contCanib--;
            item = (void *)contCanib;
            ret = pthread_create(&canibales[contCanib], NULL, Canibal, item);
            checkError(ret, 0);
        }
    }

    // Esperamos a que terminen los hilos
    esperaLoteHilos(misioneros, nmisioneros);
    esperaLoteHilos(canibales, ncanibales);
}

// Estructura de datos para el bote
struct config
{
    int nmisioneros;
    int ncanibales;
};

// Funcion que comprueba y captura los argumentos de la linea de comandos
void leeArgumentos(int argc, char *argv[], struct config *config)
{
    checkError(argc != 3, "Invocación incorrecta. Sintaxis: ./fich_ejec num_misioneros num_canibales");

    config->nmisioneros = atoi(argv[1]);
    numMisionerosEnTierra = atoi(argv[1]);
    checkError(config->nmisioneros < 1, "El número de misioneros debe ser mayor que 0");

    config->ncanibales = atoi(argv[2]);
    numCanibalesEnTierra = atoi(argv[2]);
    checkError(config->ncanibales < 1, "El número de canibales debe mayor que 0");
}

/**
 * @brief Funcion principal
 *
 * @param argc
 * @param argv
 * @return int
 */
int main(int argc, char *argv[])
{
    struct config configuration;

    puts(">> Inicio");
    leeArgumentos(argc, argv, &configuration);

    srand(getpid());
    int ret = buffer_inicia(3);
    checkError(ret, "Error al crear el bote");

    testHilos(configuration.nmisioneros, configuration.ncanibales);
    puts(">> Fin");
    exit(0);
}
