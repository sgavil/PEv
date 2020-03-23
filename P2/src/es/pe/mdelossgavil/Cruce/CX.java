package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;
import java.util.Random;

import es.pe.mdelossgavil.Poblacion.*;
import javafx.util.Pair;
import sun.security.mscapi.PRNG;

public class CX implements ICruce {

	public CX() {

	}

	/**
	 * A partir de una poblacion dada,reproduce algunos de sus individuos y los
	 * hijos resultantes ocupan el lugar de los padres en la poblacion
	 */
	@Override
	public void reproduccion(ACromosoma p1, ACromosoma p2, ACromosoma h1, ACromosoma h2) {
		
		//elegir un tramo de uno de los progenitores y
		//cruzar preservando el orden y la posición de la mayor
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
		
		Cruce(p1, p2, h1, h2);

	}

	/**
	 * @param padre1     Primer cromosoma padre
	 * @param padre2     Segundo cromosoma padre
	 * @param hijo1      primer cromosoma hijo
	 * @param hijo2      segundo cromosoma hijo
	 * @param principio  indice del cromosoma donde empieza el tramos escogido	
	 * @param fin      	 indice del cromosoma donde acaba el tramos escogido
	 */
	private void Cruce(ACromosoma padre1, ACromosoma padre2, ACromosoma hijo1, ACromosoma hijo2) {

		//Creamos las parejas para hacer los ciclos
		ArrayList<Pair<Integer,Integer>> parejas=new ArrayList<Pair<Integer,Integer>>();
		for(int i=0;i<padre1.getCodificacion().size();i++)
		{
			Pair<Integer,Integer> pareja=new Pair<Integer, Integer>( (int)padre1.getCodificacion().get(i), 
					(int)padre2.getCodificacion().get(i));
			parejas.add(pareja);
		}
			
		//Ciclo del primer hijo
		int elem = (int) padre1.getCodificacion().get(0);
		while(!hijo1.getCodificacion().contains(elem))
		{
			int pos=0;
			for(int i=0;i<padre1.getCodificacion().size();i++){
				if((int)padre1.getCodificacion().get(i)==elem)
					pos=i;
			}
			hijo1.getCodificacion().set(pos, elem);
			//Buscamos el siguiente elem a insertar mientras se pueda
			int aux;
			for (int j = 0; j < parejas.size(); j++) {
				if(parejas.get(j).getKey()==elem)
				{
					elem=parejas.get(j).getValue();
					break;
				}
			}
		}
		
		//Ciclo para el segundo hijo
		while(!hijo2.getCodificacion().contains(elem))
		{
			int pos=0;
			for(int i=0;i<padre2.getCodificacion().size();i++){
				if((int)padre2.getCodificacion().get(i)==elem)
					pos=i;
			}
			hijo2.getCodificacion().set(pos, elem);
			//Buscamos el siguiente elem a insertar mientras se pueda
			int aux;
			for (int j = 0; j < parejas.size(); j++) {
				if(parejas.get(j).getValue()==elem)
				{
					elem=parejas.get(j).getKey();
					break;
				}
			}
		}
		
		//Por ultimo , intercambiamos los elementos restantes
		for(int i=0;i<hijo1.getCodificacion().size();i++) {
			if((int)hijo1.getCodificacion().get(i)==100000)
			{
				hijo1.getCodificacion().set(i,padre2.getCodificacion().get(i));
				hijo2.getCodificacion().set(i,padre1.getCodificacion().get(i));
			}
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