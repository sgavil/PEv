package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;
import javafx.util.*;

import java.util.Random;

import es.pe.mdelossgavil.Poblacion.*;
import sun.security.mscapi.PRNG;

public class CruceImparesOrdenados implements ICruce {

	public CruceImparesOrdenados() {

	}

	/**
	 * A partir de una poblacion dada,reproduce algunos de sus individuos y los
	 * hijos resultantes ocupan el lugar de los padres en la poblacion
	 */
	@Override
	public void reproduccion(ACromosoma p1, ACromosoma p2, ACromosoma h1, ACromosoma h2) {
		Cruce(p1, p2, h1, h2);

	}

	/**
	 * @param padre1     Primer cromosoma padre
	 * @param padre2     Segundo cromosoma padre
	 * @param hijo1      primer cromosoma hijo
	 * @param hijo2      segundo cromosoma hijo
	 * @param principio  indice del cromosoma donde empieza el tramos escogido	
	 * @param fin      indice del cromosoma donde acaba el tramos escogido
	 */
	private void Cruce(ACromosoma padre1, ACromosoma padre2, ACromosoma hijo1,ACromosoma hijo2) {

		//Primero rellenamos los hijos con las posiciones impares de los padres contrarios
		for(int i=0;i<padre1.getCodificacion().size();i+=2)
		{
			hijo1.getCodificacion().set(i,padre2.getCodificacion().get(i));
			hijo2.getCodificacion().set(i,padre1.getCodificacion().get(i));
		}
		
		//Los elementos que faltes los rellenaremos con los elementos de los propios en padres en orden.
		//Es decir, intentaremos introducir el primero elemento del padre respectivo en el hijo y en caso de que
		//no podamos pasaremos al siguiente, y asi hasta acabar.
		
		//Primero con el hijo1

		int fatherIndex=0;
		int childInex=1;
		while(hijo1.getCodificacion().contains(100000))
		{
			if(!hijo1.getCodificacion().contains(padre1.getCodificacion().get(fatherIndex)))
			{
				hijo1.getCodificacion().set(childInex, padre1.getCodificacion().get(fatherIndex));
				childInex+=2;
			}
			fatherIndex++;
		}
		
		//Ahora para el segundo hijo
		fatherIndex=0;
		childInex=1;
		while(hijo2.getCodificacion().contains(100000))
		{
			if(!hijo2.getCodificacion().contains(padre2.getCodificacion().get(fatherIndex)))
			{
				
				hijo2.getCodificacion().set(childInex, padre2.getCodificacion().get(fatherIndex));
				childInex+=2;
			}
			fatherIndex++;
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
