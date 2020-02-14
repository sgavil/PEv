package es.pe.mdelossgavil.Seleccion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.Cromosoma;

public abstract class Seleccion {
	protected ArrayList<Cromosoma> Poblacion;
	public abstract void hacerSeleccion(ArrayList<Cromosoma> poblacion);
}
		