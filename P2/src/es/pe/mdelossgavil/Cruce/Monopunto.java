package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;
import java.util.Random;

import es.pe.mdelossgavil.Poblacion.*;

public class Monopunto implements ICruce {

	public Monopunto() {

	}

	/**
	 * A partir de una poblacion dada,reproduce algunos de sus individuos y los
	 * hijos resultantes ocupan el lugar de los padres en la poblacion
	 */
	@Override
	public void reproduccion(ACromosoma p1, ACromosoma p2, ACromosoma h1, ACromosoma h2) {
		
		// Se cruzan los individuos seleccionados en un punto al azar
		int punto_cruce;
		int lCrom = p1.get_longitud();
		Random r = new Random();
		punto_cruce = r.nextInt(lCrom);

		Cruce(p1, p2, h1, h2, punto_cruce);

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
