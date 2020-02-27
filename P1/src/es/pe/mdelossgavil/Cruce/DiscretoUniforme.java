package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;
import java.util.Random;

import es.pe.mdelossgavil.Poblacion.*;

public class DiscretoUniforme implements ICruce {

	public DiscretoUniforme() {

	}
	/**
	 * A partir de una poblacion dada,reproduce algunos de sus individuos y los
	 * hijos resultantes ocupan el lugar de los padres en la poblacion
	 */
	@Override
	public void reproduccion(ArrayList<ACromosoma> poblacion, String problema, float probCruce) {
		// Seleccionados para reproducir
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
		}
		else if(problema == "F2") {
			hijo1 = new CromosomaF2();
			hijo2 = new CromosomaF2();
		}
		else if(problema == "F3") {
			hijo1 = new CromosomaF3();
			hijo2 = new CromosomaF3();
		}
		else if(problema == "F4") {
			hijo1 = new CromosomaF4();
			hijo2 = new CromosomaF4();
		}
		else if(problema == "P2") {
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
			
			if (problema == "F1") {
				poblacion.set(seleccionCruce[i], new CromosomaF1(hijo1));
				poblacion.set(seleccionCruce[i + 1], new CromosomaF1(hijo2));
			}
			else if(problema == "F2") {
				poblacion.set(seleccionCruce[i], new CromosomaF2(hijo1));
				poblacion.set(seleccionCruce[i + 1], new CromosomaF2(hijo2));
			}
			else if(problema == "F3") {
				poblacion.set(seleccionCruce[i], new CromosomaF3(hijo1));
				poblacion.set(seleccionCruce[i + 1], new CromosomaF3(hijo2));
			}
			else if(problema == "F4") {
				poblacion.set(seleccionCruce[i], new CromosomaF4(hijo1));
				poblacion.set(seleccionCruce[i + 1], new CromosomaF4(hijo2));
			}
			else if(problema == "P2") {
				poblacion.set(seleccionCruce[i], new CromosomaP2(hijo1));
				poblacion.set(seleccionCruce[i + 1], new CromosomaP2(hijo2));
			}
		}
		
		// Una vez hecho el cruce, se evalua
		hijo1.set_aptitud(hijo1.evaluar());
		hijo2.set_aptitud(hijo2.evaluar());
	}

	private void Cruce(ACromosoma padre1, ACromosoma padre2, ACromosoma hijo1, ACromosoma hijo2) {
	
	for (int i = 0; i <padre1.get_genes().size();i++) {
		// Creamos una prob aleatoria enter [0,1)
		float prob = (float) Math.random();
		if (prob < 0.1f) {
			hijo1.get_genes().set(i, padre2.get_genes().get(i));
			hijo2.get_genes().set(i, padre1.get_genes().get(i));
		}
		else
		{
			hijo1.get_genes().set(i, padre1.get_genes().get(i));
			hijo2.get_genes().set(i, padre2.get_genes().get(i));
		}
	}
		/* Actualizamos los genes */
		// Una vez hecho el cruce, se evalua
		hijo1.set_aptitud(hijo1.evaluar());
		hijo2.set_aptitud(hijo2.evaluar());
	}
}
