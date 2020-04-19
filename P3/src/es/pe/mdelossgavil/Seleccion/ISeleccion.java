package es.pe.mdelossgavil.Seleccion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaArboles;


/**
 * Interfaz que define un tipo de seleccion, recibe una poblacion y la modifica
 * aplicando la seleccion correspondiente.
 */
public interface ISeleccion {
	public void hacer_seleccion(ArrayList<ACromosoma> poblacion, String tipoProblema);
}
