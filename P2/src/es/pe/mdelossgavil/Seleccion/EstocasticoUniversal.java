package es.pe.mdelossgavil.Seleccion;

import java.util.ArrayList;
import java.util.Collections;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaHospitales;

/**
 * Los individuos se mapean en segmentos continuos cuyo tamaño es el de su
 * aptitud. La posición de la primera marca se obtiene a partir de un número
 * aleatorio entre 0 y 1/N, Siendo N el número de individuos que se quieren
 * seleccionar
 *
 */
public class EstocasticoUniversal implements ISeleccion {

	@Override
	public void hacer_seleccion(ArrayList<ACromosoma> poblacion, String tipoProblema) {

		ArrayList<ACromosoma> nueva_pob = new ArrayList<ACromosoma>();

		float distEntreMarcas = 1 / poblacion.size();
		double primeraMarca = Math.random() * (distEntreMarcas + 1);
		
		for (int i = 0; i < poblacion.size(); i++) {

			double valorABuscar = (primeraMarca + i - 1) / poblacion.size();
			
			int k = 0;
			while (valorABuscar > poblacion.get(k).get_punt_acum() && k < poblacion.size() - 1)
				k++;

			nueva_pob.add(new CromosomaHospitales(poblacion.get(k)));
		}

		// TODO Auto-generated method stub
		Collections.copy(poblacion, nueva_pob);
	}

}
