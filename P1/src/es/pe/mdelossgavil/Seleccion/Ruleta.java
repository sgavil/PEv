package es.pe.mdelossgavil.Seleccion;

import java.util.ArrayList;
import java.util.Collections;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaF1;
import es.pe.mdelossgavil.Seleccion.ISeleccion;

/**
 * M�todo de selecci�n que usa la analog�a de una ruleta para seleccionar
 * individuos de una poblaci�n,Cuanto m�s alto es el fitness del individuo m�s
 * espacio se le asigna en la ruleta
 */
public class Ruleta implements ISeleccion {

	public Ruleta() {

	}

	@Override
	public ArrayList<ACromosoma> hacer_seleccion(ArrayList<ACromosoma> poblacion) 
	{
		
		// Tomamos el n�mero de elementos que se van a seleccionar
		int elementos_seleccion = poblacion.size();

		// Creamos el array donde se guardara la futura poblacion
		ArrayList<ACromosoma> nueva_pob = new ArrayList<ACromosoma>();

		for (int i = 0; i < poblacion.size(); i++) {

			// Se genera un random entre 0 y 1
			float rnd = (float) Math.random();

			// Recorremos la poblacion buscando el elemento al que corresponde la eleccion
			// de la ruleta
			int k = 0;
			while (rnd > poblacion.get(k).get_punt_acum() && k < poblacion.size() - 1)
				k++;

			// Al llegar al elemento lo guardamos en nuestra selecci�n de poblaci�n
			nueva_pob.add(new CromosomaF1(poblacion.get(k)));

		}
		
		return nueva_pob;

		
	}

}
