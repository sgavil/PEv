package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;
import java.util.Random;

import es.pe.mdelossgavil.Poblacion.*;
import sun.security.mscapi.PRNG;

public class PMX implements ICruce {

	public PMX() {

	}

	/**
	 * A partir de una poblacion dada,reproduce algunos de sus individuos y los
	 * hijos resultantes ocupan el lugar de los padres en la poblacion
	 */
	@Override
	public void reproduccion(ACromosoma p1, ACromosoma p2, ACromosoma h1, ACromosoma h2) {
		
		//elegir un tramo de uno de los progenitores y
		//cruzar preservando el orden y la posici�n de la mayor
		//cantidad posible de ciudades del otro
		
		int principio,fin;
		int lCrom = p1.get_longitud();
		//escogemos dos puntos al azar
		Random r = new Random();
		
		principio = r.nextInt(lCrom);
		fin=r.nextInt(lCrom);
		while(fin==principio)fin=r.nextInt(lCrom);
		//En caso de que esten intercambiados . hacemos swap
		if(fin<principio)
		{
			int aux=principio;
			principio=fin;
			fin=aux;
		}
		
		Cruce(p1, p2, h1, h2, principio,fin);

	}

	/**
	 * @param padre1     Primer cromosoma padre
	 * @param padre2     Segundo cromosoma padre
	 * @param hijo1      primer cromosoma hijo
	 * @param hijo2      segundo cromosoma hijo
	 * @param principio  indice del cromosoma donde empieza el tramos escogido	
	 * @param fin      indice del cromosoma donde acaba el tramos escogido
	 */
	private void Cruce(ACromosoma padre1, ACromosoma padre2, ACromosoma hijo1, ACromosoma hijo2, int principio,int fin) {

		// Establecemos el tramos en los hijos
		for (int i = principio; i < fin; i++) {
			hijo1.getCodificacion().set(i, padre2.getCodificacion().get(i));
			hijo2.getCodificacion().set(i, padre1.getCodificacion().get(i));
		}

		// Ahora queda rellenar aquellos elementos que no han sido escogidos en el tramo
		
		//Del principio del cromosoma al principio del tramo
		for (int i = 0; i < principio; i++) {
			
			//hijo1
			if(hijo1.getCodificacion().contains(padre1.getCodificacion().get(i)))
				hijo1.getCodificacion().set(i,padre2.getCodificacion().get(i));
			else hijo1.getCodificacion().set(i,padre2.getCodificacion().get(i));
			
			//hijo2
			if(hijo2.getCodificacion().contains(padre2.getCodificacion().get(i)))
				hijo2.getCodificacion().set(i,padre1.getCodificacion().get(i));
			else hijo2.getCodificacion().set(i,padre2.getCodificacion().get(i));
		
		}
		
		//Del final del tramos al final del cromosoma
		for (int i = fin; i < padre1.getCodificacion().size(); i++) {
			
			//hijo1
			if(hijo1.getCodificacion().contains(padre1.getCodificacion().get(i)))
				hijo1.getCodificacion().set(i,padre2.getCodificacion().get(i));
			else hijo1.getCodificacion().set(i,padre2.getCodificacion().get(i));
			
			//hijo2
			if(hijo2.getCodificacion().contains(padre2.getCodificacion().get(i)))
				hijo2.getCodificacion().set(i,padre1.getCodificacion().get(i));
			else hijo2.getCodificacion().set(i,padre2.getCodificacion().get(i));
		
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
