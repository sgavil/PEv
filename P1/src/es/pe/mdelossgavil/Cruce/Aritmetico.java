package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaF1;
import es.pe.mdelossgavil.Poblacion.CromosomaF2;
import es.pe.mdelossgavil.Poblacion.CromosomaF3;
import es.pe.mdelossgavil.Poblacion.CromosomaF4;
import es.pe.mdelossgavil.Poblacion.CromosomaP2;
import es.pe.mdelossgavil.Poblacion.TGen;

public class Aritmetico implements ICruce {

	float alpha;
	public Aritmetico(float alpha) {
		this.alpha = alpha;
	}
	@Override
	public void reproduccion(ArrayList<ACromosoma> poblacion, String problema, float probCruce) {
		int tam_pob = poblacion.size();
		int seleccionCruce[] = new int[tam_pob];

		// Contador seleccionados
		int num_sele_cruce = 0;
		int punto_cruce;
		float prob;

		ACromosoma hijo1 = null, hijo2 = null;

		/* Cogemos el tipo de Cromosoma del problema */
		if (problema == "F1") {
			hijo1 = new CromosomaF1();
			hijo2 = new CromosomaF1();
		} else if (problema == "F2") {
			hijo1 = new CromosomaF2();
			hijo2 = new CromosomaF2();
		} else if (problema == "F3") {
			hijo1 = new CromosomaF3();
			hijo2 = new CromosomaF3();
		} else if (problema == "F4") {
			hijo1 = new CromosomaF4();
			hijo2 = new CromosomaF4();
		} else if (problema == "P2") {
			hijo1 = new CromosomaP2();
			hijo2 = new CromosomaP2();
		}

		hijo1.inicializa_cromosoma();
		hijo2.inicializa_cromosoma();

		for (int i = 0; i < tam_pob; i++) {
			// Creamos una prob aleatoria entre [0,1)
			prob = (float) Math.random();

			// En caso de que sea menos a la prob de Cruce
			// se escoge a ese invididuo para cruzarse
			if (prob < probCruce) {
				seleccionCruce[num_sele_cruce] = i;
				num_sele_cruce++;
			}
		}

		// El numero de seleccionados se hace par
		if (num_sele_cruce % 2 == 1)
			num_sele_cruce--;

		for (int i = 0; i < num_sele_cruce; i += 2) {
			ACromosoma padre1 = poblacion.get(seleccionCruce[i]);
			ACromosoma padre2 = poblacion.get(seleccionCruce[i + 1]);
			Cruce(padre1, padre2, hijo1, hijo2);

			if (problema == "P2") {
				poblacion.set(seleccionCruce[i], new CromosomaP2(hijo1));
				poblacion.set(seleccionCruce[i + 1], new CromosomaP2(hijo2));
			}
		}

	}

	private void Cruce(ACromosoma padre1, ACromosoma padre2, ACromosoma hijo1, ACromosoma hijo2) {
		for (int i = 0; i < padre1.get_longitud(); i++) {
			TGen<Float> gen1 = (TGen<Float>) padre1.get_genes().get(i);
			TGen<Float> gen2 = (TGen<Float>) padre2.get_genes().get(i);
			
			float valh1 = (alpha * gen1.getGenotipo().get(0)) + ((1-alpha)*gen2.getGenotipo().get(0));
			float valh2 = (alpha * gen2.getGenotipo().get(0)) + ((1-alpha)*gen1.getGenotipo().get(0));

			((TGen) hijo1.get_genes().get(i)).getGenotipo().set(0, valh1);
			((TGen) hijo2.get_genes().get(i)).getGenotipo().set(0, valh2);
			
			hijo1.set_aptitud(hijo1.evaluar());
			hijo2.set_aptitud(hijo2.evaluar());

		}
	}

}
