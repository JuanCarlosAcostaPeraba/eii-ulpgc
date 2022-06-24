// código de la biblioteca mi_string.h
#include <stdio.h>
#include <stdlib.h>

/**
 * @brief Número de caracteres hasta llegar al NULL
 *
 * @param str [char*]
 * @return int - Devuelve un entero con la longitud de str
 */
int mi_strlen(char *str)
{
    int i = 0;
    while (*str != '\0')
    {
        i++;
        str++;
    }
    return i;
}

/**
 * @brief Copia los caracteres de s2 en s1 y añade un NULL al final
 *
 * @param s1 [char*]
 * @param s2 [char*]
 * @return char* - Dirección de s1
 */
char *mi_strcpy(char *s1, char *s2)
{
    do
    {
        *s1++ = *s2;
    } while (*s2++);
    return s1;
}

/**
 * @brief Añade los caracteres de s2 al final de s1. Es decir, concatena s2 a s1
 *
 * @param s1 [char*]
 * @param s2 [char*]
 * @return char* - Dirección de s1
 */
char *mi_strcat(char *s1, char *s2)
{
    mi_strcpy(s1 + mi_strlen(s1), s2);
    return s1;
}

/**
 * @brief Crea un duplicado de str mediante memoria dinámica.
 * El contenido del duplicado será idéntico al de str
 *
 * @param str [char*]
 * @return char* - Dirección del duplicado
 */
char *mi_strdup(char *str)
{
    char *s1 = malloc(mi_strlen(str) + 1);
    mi_strcpy(s1, str);
    return s1;
}

/**
 * @brief Compara las cadenas s1 y s2
 *
 * @param s1 [char*]
 * @param s2 [char*]
 * @return int - Si son idénticas, la función devuelve 1. Si son diferentes, la función devuelve 0
 */
int mi_strequals(char *s1, char *s2)
{
    while (*s1 != '\0')
    {
        if (*s1++ != *s2++)
        {
            return 0;
        }
    }
    return 1;
}
