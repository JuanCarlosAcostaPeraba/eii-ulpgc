// Librerías
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <ctype.h>
#include <readline/readline.h>

// Variables globales
char *path = "$ ";

/**
 * @brief Función que imprime el prompt
 *
 * @param input Cadena de caracteres que contiene el prompt
 * @return char**
 */
char **adapta_entrada(char *input)
{
    // Variables
    char **instrucciones = malloc(8 * sizeof(char *));
    char *troceada;
    char *separador = " ";
    int index = 0;
    // Separa la cadena de caracteres por espacios
    for (int i = 0; input[i] != '\0'; ++i)
    {
        input[i] = tolower(input[i]);
    }
    // Troceamos lo que nos pasaron, por espacios
    troceada = strtok(input, separador);
    // Mientras no lleguemos al final
    while (troceada != NULL)
    {
        // Guardamos la instrucción en el arreglo
        instrucciones[index] = troceada;
        // Avanzamos en el array
        index++;
        // Avanzamos
        troceada = strtok(NULL, separador);
    }
    // Terminamos de llenar el arreglo
    instrucciones[index] = NULL;
    // Retornamos el arreglo
    return instrucciones;
}

/**
 * @brief Función principal
 *
 * @return int
 */
int main()
{
    // Variables
    char **instrucciones;
    char *input;
    int stat_loc;

    // Bucle infinito
    while (1)
    {
        // Imprimimos el prompt
        input = readline(path);
        // Lo troceamos y preparamos como necesitamos
        instrucciones = adapta_entrada(input);
        // Controlamos comandos vacios
        if (!instrucciones[0])
        {
            free(input);
            free(instrucciones);
            continue;
        }

        if (strcmp(instrucciones[0], "cd") == 0)
        {
            if (chdir(instrucciones[1]) == -1)
            {
                printf("Error estableciendo el nuevo directorio.\n");
            }
            else
            {
                // Actualizamos el path
                printf("%s\n", instrucciones[1]);
                continue;
            }
        }

        // Hacemos el Fork
        int pid = fork();

        if (pid == 0)
        { // Si es el hijo.
            // Hacemos exec con los argumentos que nos han pasado preparados
            execvp(instrucciones[0], instrucciones);
            // Recordar que si se entra bien al exec, por aqui no pasa.
            printf("Error. Debes pasar bien los parametros.\n");
        }
        else
        { // Si no, es el padre.
            waitpid(pid, &stat_loc, WUNTRACED);
        }
        // Liberamos recursos
        free(input);
        free(instrucciones);
    }
    // Fin
    return 0;
}
