// pcase se invocará según esta sintaxis:
// pcase [-s | l | U | C | t] fichero1
// fichero1 es la ruta del archivo en el que hay que realizar los cambios. Si el fichero1 no existe o no se puede leer de él, se imprimirá un error por stderr.
// -s : Sentence case (Tipo oración) Cambiará todas las frases contenidas en fichero1 poniendo la primera letra de la palabra inicial de cada frase en mayúscula.
// -l : lowwercase (minúscula) Pondrá todo el texto contenido en fichero1 en minúscula.
// -U : UPPERCASE (MAYÚSCULA) Pondrá todo el texto contenido en fichero1 en MAYÚSCULA.
// -C : Capitalize Each Word (Tipo Título) Pondrá la primera letra de cada palabra contenida en fichero1 en Mayúscula.
// -t : tOOGLE cASE (tIPO iNVERSO) Pondrá la primera letra de cada palabra contenida en fichero1 en mINÚSCULA y el resto en mAYÚSCULA.

// Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

/**
 * @brief Funcion para abrir el archivo
 *
 * @param name
 * @return int
 */
int OpenFile(char *name)
{
    int fd = open(name, O_RDONLY);
    if (fd == -1)
    {
        fprintf(stderr, "Error %d: %s\n", errno, strerror(errno));
        exit(-1);
    }
    return fd;
}

/**
 * @brief Funcion para cerrar el archivo
 *
 * @param fd
 */
void CloseFile(int fd)
{
    if (close(fd) == -1)
    {
        fprintf(stderr, "Error %d: %s\n", errno, strerror(errno));
        exit(-1);
    }
}

/**
 * @brief Funcion para crear el archivo nuevo con las modificaciones
 *
 * @param ficher
 * @param buffer
 * @param longitud
 * @param permisos
 */
void NewFile(char *ficher, char *buffer, int longitud, int permisos)
{
    int fd_2 = open(ficher, O_WRONLY | O_CREAT | O_TRUNC);
    write(fd_2, buffer, longitud);
    if (fchmod(fd_2, permisos) != 0)
        perror("Error: Permiso para cambiar ficheros denegado\n");
    CloseFile(fd_2);
}

/**
 * @brief Funcion para la flag -l
 *
 * @param ficher
 */
void LowwerCase(char *ficher)
{
    int fd = OpenFile(ficher);
    struct stat conten;
    stat(ficher, &conten);
    int per = conten.st_mode;
    int len = conten.st_size;
    char buffer[len];
    read(fd, buffer, len);
    int i = 0;
    for (i = 0; i < len; i++)
    {
        if (buffer[i] <= 90 && buffer[i] >= 65)
            buffer[i] = buffer[i] + 32;
    }
    CloseFile(fd);
    unlink(ficher);
    NewFile(ficher, buffer, len, per);
}

/**
 * @brief Funcion para la flag -s
 *
 * @param ficher
 */
void SentenceCase(char *ficher)
{
    LowwerCase(ficher);
    int fd = OpenFile(ficher);
    struct stat conten;
    stat(ficher, &conten);
    int per = conten.st_mode;
    int len = conten.st_size;
    char buffer[len];
    read(fd, buffer, len);
    int p = 0;
    int i = 0;
    for (i = 0; i < len; i++)
    {
        if (buffer[i] == 33 || buffer[i] == 46 || buffer[i] == 63 || buffer[i] == 168)
            p = 1;
        if ((i == 0 || p == 1) && (buffer[i] <= 122 && buffer[i] >= 97))
        {
            buffer[i] = buffer[i] - 32;
            p = 0;
        }
    }
    CloseFile(fd);
    unlink(ficher);
    NewFile(ficher, buffer, len, per);
}

/**
 * @brief Funcion para la flag -U
 *
 * @param ficher
 */
void UpperCase(char *ficher)
{
    LowwerCase(ficher);
    int fd = OpenFile(ficher);
    struct stat conten;
    stat(ficher, &conten);
    int per = conten.st_mode;
    int len = conten.st_size;
    char buffer[len];
    read(fd, buffer, len);
    int i = 0;
    for (i = 0; i < len; i++)
    {
        if (buffer[i] <= 122 && buffer[i] >= 97)
            buffer[i] = buffer[i] - 32;
    }
    CloseFile(fd);
    unlink(ficher);
    NewFile(ficher, buffer, len, per);
}

/**
 * @brief Funcion para la flag -C
 *
 * @param ficher
 */
void Capitalize(char *ficher)
{
    LowwerCase(ficher);
    int fd = OpenFile(ficher);
    struct stat conten;
    stat(ficher, &conten);
    int per = conten.st_mode;
    int len = conten.st_size;
    char buffer[len];
    read(fd, buffer, len);
    int i = 0;
    for (i = 0; i < len; i++)
    {
        if ((i == 0 || buffer[i - 1] == 168 || buffer[i - 1] == 32) && (buffer[i] <= 122 && buffer[i] >= 97))
            buffer[i] = buffer[i] - 32;
    }
    CloseFile(fd);
    unlink(ficher);
    NewFile(ficher, buffer, len, per);
}

/**
 * @brief Funcion para la flag -t
 *
 * @param ficher
 */
void ToggleCase(char *ficher)
{
    LowwerCase(ficher);
    int fd = OpenFile(ficher);
    struct stat conten;
    stat(ficher, &conten);
    int per = conten.st_mode;
    int len = conten.st_size;
    char buffer[len];
    read(fd, buffer, len);
    int i = 0;
    for (i = 0; i < len; i++)
    {
        if ((i == 0 || buffer[i - 1] == 32) && (buffer[i] <= 122 && buffer[i] >= 97))
            continue;
        if ((i == 0 || buffer[i - 1] == 32) && (buffer[i] <= 90 && buffer[i] >= 65))
        {
            buffer[i] = buffer[i] + 32;
        }
        else if ((i != 0 || buffer[i - 1] != 32) && (buffer[i] <= 122 && buffer[i] >= 97))
        {
            buffer[i] = buffer[i] - 32;
        }
    }
    CloseFile(fd);
    unlink(ficher);
    NewFile(ficher, buffer, len, per);
}

// Variables globales
int e;

/**
 * @brief Funcion main
 *
 * @param argc
 * @param argv
 * @return int
 */
int main(int argc, char **argv)
{
    if ((e = getopt(argc, argv, "slUCt")) == -1)
        fprintf(stderr, "ERROR: opción -%e NO CORRECTA\n", (char)e);
    int i = 2;
    for (i = 2; i < argc; i++)
    {
        switch (e)
        {
        case 's':
            SentenceCase(argv[i]);
            break;
        case 'l':
            LowwerCase(argv[i]);
            break;
        case 'U':
            UpperCase(argv[i]);
            break;
        case 'C':
            Capitalize(argv[i]);
            break;
        case 't':
            ToggleCase(argv[i]);
            break;
        }
    }
    exit(0);
}