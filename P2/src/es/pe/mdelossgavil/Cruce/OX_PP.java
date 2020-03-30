package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;
import java.util.Random;

import es.pe.mdelossgavil.Poblacion.*;
import sun.security.mscapi.PRNG;

public class OX_PP implements ICruce {

	public OX_PP() {

	}

	/**
	 * A partir de una poblacion dada,reproduce algunos de sus individuos y los
	 * hijos resultantes ocupan el lugar de los padres en la poblacion
	 */
	@Override
	public void reproduccion(ACromosoma p1, ACromosoma p2, ACromosoma h1, ACromosoma h2) {

		// elegir un tramo de uno de los progenitores y
		// cruzar preservando el orden y la posición de la mayor
		// cantidad posible de ciudades del otro

		int lCrom = p1.get_longitud();
		Random r = new Random();
		ArrayList<Integer> posiciones = new ArrayList<Integer>();
		int ultimo;

		for (int i = 0; i < lCrom * 0.3; i++) {
			posiciones.add(r.nextInt(lCrom));
		}

		ultimo = posiciones.get(0);
		for (int i = 1; i < posiciones.size(); i++) {
			if (posiciones.get(i) > ultimo)
				ultimo = posiciones.get(i);
		}

		Cruce(p1, p2, h1, h2, posiciones, ultimo);

	}

	/**
	 * @param padre1    Primer cromosoma padre
	 * @param padre2    Segundo cromosoma padre
	 * @param hijo1     primer cromosoma hijo
	 * @param hijo2     segundo cromosoma hijo
	 * @param principio indice del cromosoma donde empieza el tramos escogido
	 * @param fin       indice del cromosoma donde acaba el tramos escogido
	 */
	private void Cruce(ACromosoma padre1, ACromosoma padre2, ACromosoma hijo1, ACromosoma hijo2,
			ArrayList<Integer> posiciones, int ultimo) {

		// Establecemos el tramos en los hijos
		for (int i = 0; i < posiciones.size(); i++) {
			hijo1.getCodificacion().set(posiciones.get(i), padre2.getCodificacion().get(posiciones.get(i)));
			hijo2.getCodificacion().set(posiciones.get(i), padre1.getCodificacion().get(posiciones.get(i)));
		}

		// Ahora queda rellenar aquellos elementos que no han sido escogidos en el tramo
		// Haremos un bucle para cada uno de los
		// Primero el primer hijo
		int indice = ultimo + 1;
		int indice2 = ultimo + 1;
		if (indice == padre1.get_longitud())
			indice = 0;
		if (indice2 == padre1.get_longitud())
			indice2 = 0;
		while (indice2 != ultimo) {
			if ((int) hijo1.getCodificacion().get(indice2) == 100000) {
				// Si no contiene el elemento del padre, lo metemos.En caso contrario,lo
				// intentamos con el siguiente
				if (!hijo1.getCodificacion().contains(padre1.getCodificacion().get(indice))) {
					hijo1.getCodificacion().set(indice2, padre1.getCodificacion().get(indice));
					indice2++;
				}
				// Aumentamos el puntero
				else
					indice++;

			} else
				indice2++;
			// En caso de que lleguemos al final , volvemos la principio
			if (indice == padre1.get_longitud())
				indice = 0;
			if (indice2 == padre1.get_longitud())
				indice2 = 0;
		}

		// Ahora con el segundo hijo
		indice = ultimo + 1;
		indice2 = ultimo + 1;
		if (indice == padre1.get_longitud())
			indice = 0;
		if (indice2 == padre1.get_longitud())
			indice2 = 0;
		while (indice2 != ultimo) {
			if ((int) hijo2.getCodificacion().get(indice2) == 100000) {
				// Si no contiene el elemento del padre, lo metemos.En caso contrario,lo
				// intentamos con el siguiente
				if (!hijo2.getCodificacion().contains(padre2.getCodificacion().get(indice))) {
					hijo2.getCodificacion().set(indice2, padre2.getCodificacion().get(indice));
					indice2++;
				}

				// Aumentamos el puntero
				else
					indice++;
			} else
				indice2++;
			// En caso de que lleguemos al final , volvemos la principio
			if (indice == padre1.get_longitud())
				indice = 0;
			if (indice2 == padre1.get_longitud())
				indice2 = 0;
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
