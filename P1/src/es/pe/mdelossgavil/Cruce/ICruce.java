package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;

public interface ICruce {
	void reproduccion(ArrayList<ACromosoma> poblacion,String problema, float probCruce);

}
