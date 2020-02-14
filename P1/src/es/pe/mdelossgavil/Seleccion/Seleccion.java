package es.pe.mdelossgavil.Seleccion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.Cromosoma;

/**
 * Interfaz que define un tipo de seleccion, recibe una poblacion y la modifica aplicando la seleccion
 * correspondiente.
 */
public interface Seleccion {
	public void hacerSeleccion(ArrayList<Cromosoma> poblacion);
}
		