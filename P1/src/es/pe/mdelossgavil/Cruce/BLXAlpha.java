package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;

import es.pe.mdelossgavil.Utils;
import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaP2;
import es.pe.mdelossgavil.Poblacion.TGen;

public class BLXAlpha implements ICruce{

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
		 if (problema == "P2") {
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
			
			float alpha = (float)Math.random();
			float cMax = Math.max(gen1.getGenotipo().get(0), gen2.getGenotipo().get(0));
			float cMin = Math.min(gen1.getGenotipo().get(0), gen2.getGenotipo().get(0));
			float I = cMax-cMin;
			
			float valh1 = Utils.float_between_range(cMin-I*alpha, cMax+I*alpha);
			float valh2 = Utils.float_between_range(cMin-I*alpha, cMax+I*alpha);
			
			((TGen) hijo1.get_genes().get(i)).getGenotipo().set(0, valh1);
			((TGen) hijo2.get_genes().get(i)).getGenotipo().set(0, valh2);
			
			hijo1.set_aptitud(hijo1.evaluar());
			hijo2.set_aptitud(hijo2.evaluar());

		}
	}


}
