package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;
import java.util.Random;

import es.pe.mdelossgavil.Poblacion.*;
import es.pe.mdelossgavil.Poblacion.TGen;

public class Monopunto implements ICruce {

	public Monopunto() {

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

		// Se cruzan los individuos seleccionados en un punto al azar
		int lCrom = poblacion.get(0).get_longitud();
		Random r = new Random();
		punto_cruce = r.nextInt(lCrom);

		for (int i = 0; i < num_sele_cruce; i += 2) {
			ACromosoma padre1 = poblacion.get(seleccionCruce[i]);
			ACromosoma padre2 = poblacion.get(seleccionCruce[i + 1]);
			Cruce(padre1, padre2, hijo1, hijo2, punto_cruce);
			poblacion.set(seleccionCruce[i], hijo1);
			poblacion.set(seleccionCruce[i + 1], hijo2);
			
			
		}

	}

	/**
	 * @param padre1     Primer cromosoma padre
	 * @param padre2     Segundo cromosoma padre
	 * @param hijo1      primer cromosoma hijo
	 * @param hijo2      segundo cromosoma hijo
	 * @param puntoCruce prob de hacer el cruce entre dos crosomas
	 */
	private void Cruce(ACromosoma padre1, ACromosoma padre2, ACromosoma hijo1, ACromosoma hijo2, int puntoCruce) {
		
	
		// primera parte del intercambio: 1 a 1 y 2 a 2
		for (int i = 0; i < puntoCruce; i++) {
			hijo1.getCodificacion().set(i, padre1.getCodificacion().get(i));
			hijo2.getCodificacion().set(i, padre2.getCodificacion().get(i));
		}

		// segunda parte: 1 a 2 y 2 a 1
		for (int i = puntoCruce; i < hijo1.get_longitud(); i++) {
			hijo1.getCodificacion().set(i, padre2.getCodificacion().get(i));
			hijo2.getCodificacion().set(i, padre1.getCodificacion().get(i));
		}

		/* Actualizamos los genes */
		int comienzo = 0;
		for (int i = 0; i < hijo1.get_genes().size(); i++) {
			/* Cogemos el tamanio del gen */
			TGen gen = (TGen) hijo1.get_genes().get(i);
			int tam = gen.getGenotipo().size();
			/* Trasladamos el Array */
			actualizarGen(hijo1, tam, comienzo, i);
			actualizarGen(hijo2, tam, comienzo, i);
			comienzo += tam;

		}

		
		
		// Una vez hecho el cruce, se evalua
		hijo1.set_aptitud(hijo1.evaluar());
		hijo2.set_aptitud(hijo2.evaluar());
	}

	private void actualizarGen(ACromosoma cromosoma, int tam, int comienzo, int gen) {
		for (int i = 0; i < tam; i++) {
			((TGen) cromosoma.get_genes().get(gen)).getGenotipo().set(i, cromosoma.getCodificacion().get(i + comienzo));
		}
	}

}
