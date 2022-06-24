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

// Variables globales
int l = 0;
int s = 0;
int U = 0;
int C = 0;
int t = 0;

/**
 * @brief Funcion para comprobar los errores
 */
void ErrorCheck()
{
    fprintf(stderr, "[*] Error\t- %d : %s\n", errno, strerror(errno));
    errno = 0;
}

/**
 * @brief Funcion para comprobar las flags
 *
 * @param argc
 * @param argv
 */
void FlagCheck(int argc, char *argv[])
{
    int error;
    if (argc < 5)
    {
        while ((error = getopt(argc, argv, "slUCt")) != -1)
        {
            switch (error)
            {
            case 's':
                s = 1;
                break;
            case 'l':
                l = 1;
                break;
            case 'U':
                U = 1;
                break;
            case 'C':
                C = 1;
                break;
            case 't':
                t = 1;
                break;
            default:
                break;
            }
        }

        if (s + l + U + C + t == 0)
        {
            fprintf(stderr, "%s: Comando erróneo\t[ninguna flag]\n", argv[0]);
            printf("[*] DEMO:\t- ./pcase -[s|l|U|C|t] fichero1 [fichero2]'\n");
        }
        else if (s + l + U + C + t > 1)
        {
            fprintf(stderr, "%s: Comando erróneo\t[flags de más]\n", argv[0]);
            printf("[*] DEMO:\t- ./pcase -[s|l|U|C|t] fichero1 [fichero2]'\n");
        }
        else if (argc == 2)
        {
            fprintf(stderr, "%s: Comando erróneo\t[falta fichero]\n", argv[0]);
            printf("[*] DEMO:\t- ./pcase -[s|l|U|C|t] fichero1 [fichero2]'\n");
        }
    }
}

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
        ErrorCheck();
        exit(-1);
    }
    return fd;
}

/**
 * @brief Funcion para cerrar el archivo
 *
 * @param fd
 * @return int
 */
int CloseFile(int fd)
{
    int c = close(fd);
    if (c == -1)
    {
        ErrorCheck();
        exit(-1);
    }
    return c;
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

/**
 * @brief Funcion main
 *
 * @param argc
 * @param argv
 * @return int
 */
int main(int argc, char **argv)
{
    FlagCheck(argc, argv);
    int i;
    int fd;
    for (i = 2; i < argc; i++)
    {
        fd = OpenFile(argv[i]);
        if (s == 1)
            SentenceCase(argv[i]);
        else if (l == 1)
            LowwerCase(argv[i]);
        else if (U == 1)
            UpperCase(argv[i]);
        else if (C == 1)
            Capitalize(argv[i]);
        else if (t == 1)
            ToggleCase(argv[i]);
        close(fd);
        // ErrorCheck();
    }
    exit(0);
}