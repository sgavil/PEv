package es.pe.mdelossgavil.Seleccion;

import java.util.ArrayList;
import java.util.Collections;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaComparator;
import es.pe.mdelossgavil.Poblacion.CromosomaHospitales;
import es.pe.mdelossgavil.Seleccion.ISeleccion;

/**
 * Método de selección que usa la analogía de una ruleta para seleccionar
 * individuos de una población,Cuanto más alto es el fitness del individuo más
 * espacio se le asigna en la ruleta
 */
public class Ruleta implements ISeleccion {

	public Ruleta() {

	}

	@Override
	public void hacer_seleccion(ArrayList<ACromosoma> poblacion,String tipoProblema) 
	{
		
		// Tomamos el número de elementos que se van a seleccionar
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

			// Al llegar al elemento lo guardamos en nuestra selección de población
			/*if(tipoProblema.equals("F1"))
					nueva_pob.add(new CromosomaF1(poblacion.get(k)));
			else if(tipoProblema.equals("F2"))
				nueva_pob.add(new CromosomaF2(poblacion.get(k)));
			else if(tipoProblema.equals("F3"))
				nueva_pob.add(new CromosomaF3(poblacion.get(k)));
			else if(tipoProblema.equals("F4"))
				nueva_pob.add(new CromosomaF4(poblacion.get(k)));
			else if (tipoProblema.equals("P2"))
				nueva_pob.add(new CromosomaP2(poblacion.get(k)));*/
			nueva_pob.add(new CromosomaHospitales(poblacion.get(k)));

		}
		Collections.copy(poblacion, nueva_pob);
		
		
	}

}
