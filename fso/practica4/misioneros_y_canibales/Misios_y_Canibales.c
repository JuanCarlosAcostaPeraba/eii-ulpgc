// PROBLEMA DE SINCRONIZACION: MISIONEROS Y CANÍBALES
// Autor: Alejandro Vialard Santana

// Archivos cabecera del sistema
#include <stdlib.h> // atoi(), exit()...
#include <stdio.h>  // printf(), fputs(), stderr...
#include <string.h>
#include <errno.h>
#include <pthread.h>
#include <unistd.h>
#include "Bote.h"

// Inicialización de variables
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;          // cerrojo
pthread_cond_t condMisionero = PTHREAD_COND_INITIALIZER;
pthread_cond_t condCanibal = PTHREAD_COND_INITIALIZER;

int misionerosABordo = 0;
int canibalesABordo = 0;
int listoZarpar = 0;
int misioneros;
int canibales;

void retardoAleatorio()
{
    unsigned long retardo = rand() % 10;
    sleep(retardo);
}

// Funcion auxiliar para comprobar errores
// e imprimir un mensaje si hay error
void checkError(int return_code, const char* msg) 
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

// Hilo para llegar un misionero
void* llegaMisionero(void *item) 
{
    // retardo aleatorio
    retardoAleatorio();

    // tomar el cerrojo
    pthread_mutex_lock(&mutex);

    // Mientras esté lleno el bote o haya un misionero y un canibal, espera
    while (listoZarpar == 1 || botePendienteViajar() == boteCapacidad() || (misionerosABordo == 1 && canibalesABordo == 1)) {
        pthread_cond_wait(&condMisionero, &mutex);
    }

    // Comprobaciones de cuando quedan solo tres personas por entrar
    if (misioneros + canibales <= 3) {
        
        // No se puede dar el caso de que haya dos misioneros y un canibal
        if (misioneros == 2 && canibales == 1) {
            printf("Dos misioneros y un canibal no pueden viajar juntos.\n");
            pthread_cond_broadcast(&condCanibal);  
            pthread_cond_broadcast(&condMisionero);
            pthread_mutex_unlock(&mutex);
            pthread_exit(NULL);
        }
        // Si se añade un número no multiplo de 3, quedará espacio en el bote y no podrá zarpar
        else if (misioneros + canibales < 3) {
            printf("El bote no se puede llenar y no puede zarpar.\n");
            pthread_cond_broadcast(&condCanibal);  
            pthread_cond_broadcast(&condMisionero);
            pthread_mutex_unlock(&mutex);
            pthread_exit(NULL);
        }
    }

    // llega un misionero
    ITEM misio;
    // Si no está lleno el bote y no está listo para zarpar, entra
    if (botePendienteViajar() != boteCapacidad() && listoZarpar == 0) {
        boteSubir(misio);
        misionerosABordo++;
        printf("Sube un misionero: misioneros = %d, canibales = %d\n", misionerosABordo, canibalesABordo);
        // pone listo para zarpar
        if (botePendienteViajar() == boteCapacidad()) {
            listoZarpar = 1;
            printf("Zarpa el bote: misioneros = %d, canibales = %d\n", misionerosABordo, canibalesABordo);
        }
    }

    // Si estan a bordo, espera
    while (listoZarpar == 0) {
        pthread_cond_wait(&condMisionero, &mutex);
    }

    // Si el bote está lleno zarpa, y se bajan
    if (listoZarpar == 1) {
        boteBajar(&misio);
        misionerosABordo--;
        misioneros--;
        printf("Baja un misionero: misioneros = %d, canibales = %d\n", misionerosABordo, canibalesABordo);
        pthread_cond_broadcast(&condCanibal);  
        pthread_cond_broadcast(&condMisionero);
        // Si se vacía, ya no está listo para zarpar
        if (botePendienteViajar() == 0) {
            listoZarpar = 0;
            printf("Bote vacío: misioneros = %d, canibales = %d\n", misionerosABordo, canibalesABordo);
            pthread_cond_broadcast(&condCanibal);  
            pthread_cond_broadcast(&condMisionero);
        }
    }

    // liberar el cerrojo
    pthread_mutex_unlock(&mutex);
    pthread_exit(NULL);
}

void* llegaCanibal(void *item) 
{
    // retardo aleatorio
    retardoAleatorio();

    // toma el cerrojo
    pthread_mutex_lock(&mutex);

    // Mientras esté lleno el bote o haya dos misioneros, espera
    while (listoZarpar == 1 || (botePendienteViajar() == boteCapacidad()) || (misionerosABordo == 2)) {
        pthread_cond_wait(&condCanibal, &mutex);
    }

    // Comprobaciones de cuando quedan solo tres personas por entrar
    if (misioneros + canibales <= 3) {
        
        // No se puede dar el caso de que haya dos misioneros y un canibal
        if (misioneros == 2 && canibales == 1) {
            printf("Dos misioneros y un canibal no pueden viajar juntos.\n");
            pthread_cond_broadcast(&condCanibal);  
            pthread_cond_broadcast(&condMisionero);
            pthread_mutex_unlock(&mutex);
            pthread_exit(NULL);
        }
        // Si se añade un número no multiplo de 3, quedará espacio en el bote y no podrá zarpar
        else if (misioneros + canibales < 3) {
            printf("El bote no se puede llenar y no puede zarpar.\n");
            pthread_cond_broadcast(&condCanibal);  
            pthread_cond_broadcast(&condMisionero);
            pthread_mutex_unlock(&mutex);
            pthread_exit(NULL);
        }
    }

    ITEM canibal;
    // Si no está lleno el bote y no está listo para zarpar, se inserta
    if (botePendienteViajar() != boteCapacidad() && listoZarpar == 0) {
        boteSubir(canibal);
        canibalesABordo++;
        printf("Sube un canibal: misioneros = %d, canibales = %d\n", misionerosABordo, canibalesABordo);
        if (botePendienteViajar() == boteCapacidad()) {
            listoZarpar = 1;
            printf("Zarpa el bote: misioneros = %d, canibales = %d\n", misionerosABordo, canibalesABordo);
        }
    }

    // Si estan a bordo, espera
    while (listoZarpar == 0) {
        pthread_cond_wait(&condCanibal, &mutex);
    }
    // Si el bote está lleno zarpa, y se bajan
    if (listoZarpar == 1) {
        listoZarpar = 1;
        boteBajar(&canibal);
        canibalesABordo--;
        canibales--;
        printf("Baja un canibal: misioneros = %d, canibales = %d\n", misionerosABordo, canibalesABordo);
        pthread_cond_broadcast(&condCanibal);
        pthread_cond_broadcast(&condMisionero);
        // Si se vacía, ya no está listo para zarpar
        if (botePendienteViajar() == 0) {
            listoZarpar = 0;
            printf("Bote vacío: misioneros = %d, canibales = %d\n", misionerosABordo, canibalesABordo);
            pthread_cond_broadcast(&condCanibal);
            pthread_cond_broadcast(&condMisionero);
        }
    }

    // liberar el cerrojo
    pthread_mutex_unlock(&mutex);
    pthread_exit(NULL);
}

// Funcion auxiliar:
// espera a que termine un lote de hilos
// el lote es un vector de pthread_t
void esperaLoteHilos(pthread_t* lote, int nhilos)
{
    void* dummy;    // pthread_join obliga a pasar un void*

    for (int i = 0; i < nhilos; i++) {
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
    void* item;
    int ret;

    while (contCanib != 0 && contMisio != 0) {

        // creamos un numero random para ver quien llega primero
        random = rand() % 2;    // de 0 a 1

        if (random == 0) {
            contMisio--;
            item = (void*)contMisio;
            ret = pthread_create(&misioneros[contMisio], NULL, llegaMisionero, item);
            checkError(ret, 0);
        }
        else {
            contCanib--;
            item = (void*)contCanib;
            ret = pthread_create(&canibales[contCanib], NULL, llegaCanibal, item);
            checkError(ret, 0);
        }

    }

    if (contCanib == 0 && contMisio != 0) {
        while (contMisio != 0) {
            contMisio--;
            item = (void*)contMisio;
            ret = pthread_create(&misioneros[contMisio], NULL, llegaMisionero, item);
            checkError(ret, 0);
        }
    } 
    else  if (contMisio == 0 && contCanib != 0) {
        while (contCanib != 0) {
            contCanib--;
            item = (void*)contCanib;
            ret = pthread_create(&canibales[contCanib], NULL, llegaCanibal, item);
            checkError(ret, 0);
        }
    }

    // Esperamos a que terminen los hilos
    esperaLoteHilos(misioneros, nmisioneros);
    esperaLoteHilos(canibales, ncanibales);
}

// Estructura de datos para el bote
struct config {
    int nmisioneros;
    int ncanibales;
};

// Funcion que comprueba y captura los argumentos de la linea de comandos
void leeArgumentos(int argc, char* argv[], struct config* config)
{
    checkError(argc != 3, "Invocación incorrecta. Sintaxis: ./fich_ejec num_misioneros num_canibales");

    config->nmisioneros = atoi(argv[1]);
    misioneros = atoi(argv[1]);
    checkError(config->nmisioneros < 1, "El número de misioneros debe ser mayor que 0");

    config->ncanibales = atoi(argv[2]);
    canibales = atoi(argv[2]);
    checkError(config->ncanibales < 1, "El número de canibales debe mayor que 0");
}

// Funcion main
int main(int argc, char* argv[]) 
{
    struct config configuration;

    puts("Comienza la prueba del bote...");
    leeArgumentos(argc, argv, &configuration);

    srand(getpid());
    int ret = boteInicia(3);
    checkError(ret, "Error al crear el bote");

    testHilos(configuration.nmisioneros, configuration.ncanibales);
    puts("Termina la prueba del bote.");
    exit(0);
}
