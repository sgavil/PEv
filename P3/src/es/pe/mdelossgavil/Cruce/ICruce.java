package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaArboles;

public interface ICruce {
	void reproduccion(CromosomaArboles p1, CromosomaArboles p2, CromosomaArboles h1, CromosomaArboles h2);

}
