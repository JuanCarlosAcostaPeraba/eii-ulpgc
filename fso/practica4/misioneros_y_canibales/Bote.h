#ifndef _BUFFER
#define _BUFFER

// Interfaz pública del búfer compartido
// El búfer contiene un número variable de números enteros (long)

// Los elementos serán de tipo entero largo (long)
// Se define un alias "ITEM" para facilitar en un futuro
// cambiar el tipo de datos, ej. float, double, un struct, etc.
typedef long ITEM;

// Inicializa el búfer compartido
// Parámetro:
//	capacidad: número de objetos que pueden guardarse en el búfer = 3
// Devuelve:
//	Si no hay error retorna valor cero, 
//  en caso contrario devuelve un valor negativo
int boteInicia (int capacidad);

// Inserta un objeto en el búfer
// Parámetro:
//	item: objeto para guardar en el búfer
void boteSubir (ITEM item);

// Extrae un objeto del búfer
// Parámetro:
//	item: Puntero donde se copiará el objeto extraído
void boteBajar (ITEM* item);

// Devuelve cuántos objetos están guardados actualmente en el búfer
// y por tanto, están pendientes embarcar
int botePendienteViajar();

// Devuelve la capacidad total del búfer
// - debería coincidir con el parámetro que se pasó en boteInicia()
int boteCapacidad();

#endif
