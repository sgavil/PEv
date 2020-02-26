package es.pe.mdelossgavil.Mutacion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Utils;
import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.TGen;

public class MutacionReal implements IMutacion {

	private float min,max;
	public MutacionReal(float rangeMin,float rangeMax) {
		this.min = rangeMin;
		this.max = rangeMax;
	
	}

	@Override
	public void mutar(ArrayList<ACromosoma> poblacion, float probMutacion) {

		// Recorremos todos los individuos de la población
		for (int i = 0; i < poblacion.size(); i++) {

			boolean mutado = false;
			ACromosoma individuo = poblacion.get(i);
			ArrayList<TGen> genes = individuo.get_genes();

			// Recorremos cada gen ( valor real )
			for (int j = 0; j < genes.size(); j++) {

				// Generamos un número aleatorio
				float rndProb = (float) Math.random();

				// Si es menor que la probabilidad de mutación actualizamos el bit
				// y lo marcamos como mutado
				if (rndProb < probMutacion) {
					genes.get(j).getGenotipo().set(0, Utils.float_between_range(min,max));
					mutado = true;
				}
			}

			// Si se ha producido una mutación tenemos que volver a calcular la aptitud del
			// individuo
			if (mutado) {
				poblacion.get(i).set_aptitud(poblacion.get(i).evaluar());

			}
		}

	}

}
