package es.pe.mdelossgavil.Seleccion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaF1;
import es.pe.mdelossgavil.Poblacion.CromosomaF2;
import es.pe.mdelossgavil.Poblacion.CromosomaF3;
import es.pe.mdelossgavil.Poblacion.CromosomaF4;
import es.pe.mdelossgavil.Poblacion.CromosomaP2;
import es.pe.mdelossgavil.Seleccion.ISeleccion;

/**
 * Los individuos se mapean en segmentos continuos cuyo tamaño es el de su
 * aptitud. La posición de la primera marca se obtiene a partir de un número
 * aleatorio entre 0 y 1/N, Siendo N el número de individuos que se quieren
 * seleccionar
 *
 */
public class EstocasticoUniversal implements ISeleccion {

	@Override
	public ArrayList<ACromosoma> hacer_seleccion(ArrayList<ACromosoma> poblacion, String tipoProblema) {

		ArrayList<ACromosoma> nueva_pob = new ArrayList<ACromosoma>();

		float distEntreMarcas = 1 / poblacion.size();
		double primeraMarca = Math.random() * (distEntreMarcas + 1);
		
		for (int i = 0; i < poblacion.size(); i++) {

			double valorABuscar = (primeraMarca + i - 1) / poblacion.size();
			
			int k = 0;
			while (valorABuscar > poblacion.get(k).get_punt_acum() && k < poblacion.size() - 1)
				k++;

			// Al llegar al elemento lo guardamos en nuestra selección de población
			if (tipoProblema.equals("F1"))
				nueva_pob.add(new CromosomaF1(poblacion.get(k)));
			else if (tipoProblema.equals("F2"))
				nueva_pob.add(new CromosomaF2(poblacion.get(k)));
			else if (tipoProblema.equals("F3"))
				nueva_pob.add(new CromosomaF3(poblacion.get(k)));
			else if (tipoProblema.equals("F4"))
				nueva_pob.add(new CromosomaF4(poblacion.get(k)));
			else if (tipoProblema.equals("P2"))
				nueva_pob.add(new CromosomaP2(poblacion.get(k)));
		}

		// TODO Auto-generated method stub
		return nueva_pob;
	}

}
