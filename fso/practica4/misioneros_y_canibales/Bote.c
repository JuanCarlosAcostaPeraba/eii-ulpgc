// DEFINICION DE LAS FUNCIONES DE LA LANCHA

#include "Bote.h"
#include <assert.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

struct Bote {
	ITEM* datos;
	int capacidad;
	int ptr_frente;
	int ptr_final;
	int pendientes_consumir;
};

static struct Bote bote = { .datos = NULL };

// OPERACIONES DE LA LANCHA

// Función de construcción
int boteInicia (int capacidad)
{
	bote.datos = malloc(sizeof(ITEM)*capacidad);
	if (bote.datos != NULL) 
	{
		bote.capacidad = capacidad;
		bote.ptr_frente = 0;
		bote.ptr_final = 0;
		bote.pendientes_consumir = 0;
		return 0;
	} else {
		return -1;
	}
}

// Se sube una persona al bote y se actualiza los datos
void boteSubir (ITEM item)
{
	assert (bote.datos != NULL);
	assert (bote.pendientes_consumir < bote.capacidad);

	bote.datos[bote.ptr_final] = item;

	// introducimos un retardo arbitrario 
	// si el ítem es un número impar
	if ( item % 2 == 1 ) { sleep(1); }

	bote.ptr_final = (bote.ptr_final+1) % bote.capacidad;
	bote.pendientes_consumir++;
}

// Se baja alguien del bote y se actualiza los datos
void boteBajar (ITEM* item)
{
	assert (bote.datos != NULL);
	assert (bote.pendientes_consumir > 0);

	*item = bote.datos[bote.ptr_frente];

	bote.ptr_frente = (bote.ptr_frente+1) % bote.capacidad;
	bote.pendientes_consumir--;
}

// Función que retorna el número de personas pendientes de subir
int botePendienteViajar()
{
	assert (bote.datos != NULL);
	return bote.pendientes_consumir;
}

// Función que retorna la capacidad máxima del bote
int boteCapacidad()
{
	assert (bote.datos != NULL);
	return bote.capacidad;
}
