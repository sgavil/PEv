package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;
import java.util.Random;

import com.sun.org.apache.bcel.internal.generic.LCONST;

import es.pe.mdelossgavil.Poblacion.*;
import sun.security.mscapi.PRNG;

public class CO implements ICruce {

	public CO() {

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
		// escogemos dos puntos al azar
		Random r = new Random();

		ArrayList<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < lCrom; i++) {
			lista.add(i);
		}

		int pCorte = r.nextInt(lCrom);

		Cruce(p1, p2, h1, h2, lista, pCorte);

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
			ArrayList<Integer> lista, int pCorte) {

		// Lo primero que hacemos es codificar a los padres segun la lista
		// El primer padre
		ArrayList<Integer> auxList = new ArrayList<Integer>(lista);
		ArrayList<Integer> padre1Cod = new ArrayList<Integer>();
		ArrayList<Integer> padre2Cod = new ArrayList<Integer>();
		for (int i = 0; i < padre1.get_longitud(); i++) {
			for (int j = 0; j < auxList.size(); j++) {
				if (auxList.get(j) == padre1.getCodificacion().get(i)) {
					padre1Cod.add(j);
					auxList.remove(j);
					break;
				}
			}
		}

		// El segundo padre
		auxList = new ArrayList<Integer>(lista);
		for (int i = 0; i < padre2.get_longitud(); i++) {
			for (int j = 0; j < auxList.size(); j++) {
				if (auxList.get(j) == padre2.getCodificacion().get(i)) {
					padre2Cod.add(j);
					auxList.remove(j);
					break;
				}
			}
		}

		// Una vez tenemos los padre codificados , aplicamos el monopunto sobre los
		// hijos
		// primera parte del intercambio: 1 a 1 y 2 a 2
		for (int i = 0; i < pCorte; i++) {
			int n1, n2;
			n1 = (int) padre1Cod.get(i);
			n2 = (int) padre2Cod.get(i);
			hijo1.getCodificacion().set(i, n1);
			hijo2.getCodificacion().set(i, n2);
		}

		// segunda parte: 1 a 2 y 2 a 1
		for (int i = pCorte; i < hijo1.get_longitud(); i++) {
			int n1, n2;
			n1 = (int) padre1Cod.get(i);
			n2 = (int) padre2Cod.get(i);
			hijo1.getCodificacion().set(i, n2);
			hijo2.getCodificacion().set(i, n1);
		}
		// Ahora que los tenemos mezclados, decodificamos cada unos de los hijos

		// Primer hijo
		auxList = new ArrayList<Integer>(lista);
		for (int i = 0; i < padre1.get_longitud(); i++) {
			int elem = (int) hijo1.getCodificacion().get(i);
			hijo1.getCodificacion().set(i, auxList.get(elem));
			auxList.remove(elem);
		}

		// Segundo hijo
		auxList = new ArrayList<Integer>(lista);
		for (int i = 0; i < padre2.get_longitud(); i++) {
			int elem = (int) hijo2.getCodificacion().get(i);
			hijo2.getCodificacion().set(i, auxList.get(elem));
			auxList.remove(elem);
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
