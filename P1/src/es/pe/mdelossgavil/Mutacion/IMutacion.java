package es.pe.mdelossgavil.Mutacion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;

public interface IMutacion {
	public void mutar(ArrayList<ACromosoma> poblacion);
}
