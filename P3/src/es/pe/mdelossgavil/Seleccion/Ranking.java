package es.pe.mdelossgavil.Seleccion;

import java.util.ArrayList;
import java.util.Collections;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaArboles;
import es.pe.mdelossgavil.Poblacion.CromosomaComparator;

public class Ranking implements ISeleccion {

	float B;

	private double[] probabilidades;

	public Ranking(float B) {
		this.B = B;
	}

	@Override
	public void  hacer_seleccion(ArrayList<ACromosoma> poblacion, String tipoProblema) {
		probabilidades = new double[poblacion.size()];

		ordenar_poblacion(poblacion);
		calcula_probabilidades(poblacion);

		ArrayList<ACromosoma> nueva_pob = new ArrayList<ACromosoma>();

		for (int i = 0; i < poblacion.size(); i++) {

			// Se genera un random entre 0 y 1
			float rnd = (float) Math.random();

			// Recorremos la poblacion buscando el elemento al que corresponde la eleccion
			// de la ruleta
			int k = 0;
			while (rnd > probabilidades[k] && k < probabilidades.length - 1)
				k++;

			nueva_pob.add(new CromosomaArboles(poblacion.get(k)));
		}
		
		Collections.copy(poblacion, nueva_pob);

	}

	private void ordenar_poblacion(ArrayList<ACromosoma> poblacion) {
		Collections.sort(poblacion, new CromosomaComparator());

	}

	private void calcula_probabilidades(ArrayList<ACromosoma> poblacion) {
		int N = poblacion.size();

		for (int i = 0; i < probabilidades.length; i++) {
			
			double probDelIesimo = (double)i/N;
			probDelIesimo = probDelIesimo * 2 * (B-1);
			probDelIesimo = B - probDelIesimo;
			probDelIesimo = (double)probDelIesimo *((double)1/N);
			if (i != 0) {
				probabilidades[i] = probabilidades[i - 1] + probDelIesimo;
			} else {
				probabilidades[i] = probDelIesimo;
			}
		}
	}

}
