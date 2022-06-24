// PRÁCTICA 5 - FSO
// Autor: Alejandro Vialard Santana

#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include <sys/wait.h>

char string[1024];  // Buffer para leer la cadena de entrada
char* argList[100];  // Argumentos para la consola
int condicionTuberia = 0;

// Método para tratar los errores
void checkError(char* msg, int error) {
    if (error != 0) {
        if (msg != NULL) {
            fprintf(stderr, "%s\n", msg);
        } else {
            fprintf(stderr, "error %d: %s\n", errno, strerror(errno));
        }
    }
}

// Método para testear los argumentos de la línea de comandos
void testArgs(int argc, char *argv[]) {
    if (argc > 1) {
        fprintf(stderr, "Invocación incorrecta: ./shell\n");
        exit(1);
    }
}

// Método para ver que instrucciones se pasan por la línea de comandos
void analizeLine(char* string, char* argv[]) {

    char *character;
    char limit[] = " \n";

    int i = 0;
    character = strtok(string, limit);
    while (character != NULL) {
        argv[i] = character;
        i++;
        character = strtok(NULL, limit);
    }

    argv[i] = NULL;
    
}

// Método para analizar la línea si es una tubería
void analizeLinePipe(char* string, char* argv[]) {
    
        char *character;
        char limit[] = " |\n";
    
        int i = 0;
        character = strtok(string, limit);
        while (character != NULL) {
            argv[i] = character;
            i++;
            character = strtok(NULL, limit);
        }

        argv[i] = NULL;
}

// Método para pasar a minúsculas las instrucciones
void lowercase(char* argv[]) {
    int i = 0;
    int length = strlen(argv[0]);   // Longitud del argumento

    // Se recorre los caracteres del argumento y se cambian a minúsculas
    while (i < length) {
        if ((argv[0][i] >= 65) && (argv[0][i] <= 90)) {
            argv[0][i] += 32;
        }
        i++;
    }
}

// Método para implementar tuberias
void pipes(char* argv[]) {

    int pipefd[2];
    int pid;
    int status;

    // Se crea un pipe
    pipe(pipefd);

    // Se crea un nuevo proceso hijo
    pid = fork();
    checkError("Error al crear el proceso hijo", pid);

    // Se ejecuta el proceso hijo
    if (pid == 0) {

        // Se cierra el extremo de lectura del pipe
        status = close(pipefd[0]);
        checkError("Error al cerrar el extremo de lectura del pipe", status);

        // Se cambia el extremo de escritura del pipe
        status = dup2(pipefd[1], STDOUT_FILENO);
        checkError("Error al cambiar el extremo de escritura del pipe", status);

        // Se cierra el extremo de escritura del pipe
        status = close(pipefd[1]);
        checkError("Error al cerrar el extremo de escritura del pipe", status);

        // Se ejecuta la instrucción
        status = execvp(argv[0], argv);
        checkError("Error al ejecutar la instrucción", status);
    } else {

        // Se cierra el extremo de escritura del pipe
        status = close(pipefd[1]);
        checkError("Error al cerrar el extremo de escritura del pipe", status);

        // Se cammbia el extremo de lectura del pipe
        status = dup2(pipefd[0], STDIN_FILENO);
        checkError("Error al cambiar el extremo de lectura del pipe", status);

        // Se cierra el extremo de lectura del pipe
        status = close(pipefd[0]);
        checkError("Error al cerrar el extremo de lectura del pipe", status);

        // Se ejecuta la instrucción
        status = execvp(argv[1], argv);
        checkError("Error al ejecutar la instrucción", status);

        // Se espera a que el proceso hijo termine
        waitpid(pid, &status, 0);
    }    
}

// Método para ejecutar las instrucciones
void execute(char *argv[]) {

    // Variables de distincion de procesos
    int pid, status;

    // Crear un proceso hijo
    pid = fork();

    // Comprobar si hay error
    if (pid == -1) {
        checkError("Error al crear el proceso hijo: \n", 1);
        exit(-1);
    }
    else if (pid == 0) {
        status = execvp(argv[0], argv);
        // Ejecutar el proceso hijo
        if (status < 0) {
            checkError("Error al ejecutar el proceso hijo\n", 0);
            exit(1);
        }
    }
    // Esperar a que el proceso hijo termine
    waitpid(pid, &status, 0);
}

// Funcion principal
int main(int argc, char *argv[]) {

    testArgs(argc, argv);
    
    // Propmt 
    char buf[100];
    char hostname[100];
    char s[100];

    // Nombre de usuario
    int status = getlogin_r(buf, 100);
    checkError(0, status);

    // Nombre del host
    status = gethostname(hostname, 100);
    checkError(0, status);

    char *name = strtok(hostname, ".");
    
    printf("\nSHELL CREADO POR ALEJANDRO VIALARD SANTANA\n");

    // Bucle que se ejecutará hasta que el usuario escriba "exit"
    while(1) {

        printf("[AVS ~%s@%s ~%s]$ ", buf, name, getcwd(s, 100));

        // Leer la cadena de entrada
        fgets(string, 1024, stdin);

        // Comprobar si la cadena de entrada es vacía
        if (strcmp(string, "\n") == 0) continue;

        // Comprobar si la hay | en la linea de comandos
        char* stringCopia = string;
        while (*stringCopia != '\0') {
            if (*stringCopia == '|') {
                condicionTuberia = 1;
                break;
            }
            stringCopia++;
        }

        if (condicionTuberia == 1) {
            analizeLinePipe(string, argList);

            lowercase(argList);
            
            // pipes(argList);

            condicionTuberia = 0;
        }
        else {
            analizeLine(string, argList);
            
            lowercase(argList);

            // Tratamiento de exit y cd
            if (strcmp(argList[0], "exit") == 0) {
            exit(0);
            }
            else if (strcmp(argList[0], "cd") == 0) {
                int ch = chdir(argList[1]);
                if (ch < 0) checkError(0, ch);
                continue;
            }

            execute(argList);
        }
    }
}