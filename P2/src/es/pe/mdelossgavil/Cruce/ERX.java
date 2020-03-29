package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;
import java.util.Random;

import es.pe.mdelossgavil.Poblacion.*;
import sun.security.mscapi.PRNG;

public class ERX implements ICruce {

	public ERX() {

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
		
		int lCrom = p1.get_longitud();
		ArrayList<ArrayList<Integer>> matriz=new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < lCrom; i++)  {
	        matriz.add(new ArrayList<Integer>());
	    }
		
		//Primero metemos los adyacentes al primer hijo
		for(int i=0;i<p1.get_longitud();i++)
		{
			//Por la derecha
			if(i+1==p1.get_longitud())
				matriz.get((int) p1.getCodificacion().get(i)).add((int)p1.getCodificacion().get(0));
			else
				matriz.get((int) p1.getCodificacion().get(i)).add((int)p1.getCodificacion().get(i+1));
			//Por la izquierda
			if(i-1<0)
				matriz.get((int) p1.getCodificacion().get(i)).add((int)p1.getCodificacion().get(p1.get_longitud()-1));
			else
				matriz.get((int) p1.getCodificacion().get(i)).add((int)p1.getCodificacion().get(i-1));
		}
		
		//Ahora metemos los del segundo hijo
		for(int i=0;i<p2.get_longitud();i++)
		{
			//Por la derecha
			if(i+1==p2.get_longitud())
			{
				if(!matriz.get((int) p2.getCodificacion().get(i)).contains((int)p2.getCodificacion().get(0)))
					matriz.get((int) p2.getCodificacion().get(i)).add((int)p2.getCodificacion().get(0));
			
			}
				
			else if (!matriz.get((int) p2.getCodificacion().get(i)).contains((int)p2.getCodificacion().get(i+1)))
				matriz.get((int) p2.getCodificacion().get(i)).add((int)p2.getCodificacion().get(i+1));
			
			//Por la izquierda
			if(i-1<0)
			{
				if(!matriz.get((int) p2.getCodificacion().get(i)).contains((int)p2.getCodificacion().get(p2.get_longitud()-1)))
					matriz.get((int) p2.getCodificacion().get(i)).add((int)p2.getCodificacion().get(p2.get_longitud()-1));
			
			}
			else if(!matriz.get((int) p2.getCodificacion().get(i)).contains((int)p2.getCodificacion().get(i-1)))
				matriz.get((int) p2.getCodificacion().get(i)).add((int)p2.getCodificacion().get(i-1));
		}
		Cruce(p1, p2, h1, h2,matriz);

	}

	/**
	 * @param padre1     Primer cromosoma padre
	 * @param padre2     Segundo cromosoma padre
	 * @param hijo1      primer cromosoma hijo
	 * @param hijo2      segundo cromosoma hijo
	 * @param principio  indice del cromosoma donde empieza el tramos escogido	
	 * @param fin      	 indice del cromosoma donde acaba el tramos escogido
	 */
	private void Cruce(ACromosoma padre1, ACromosoma padre2, ACromosoma hijo1, ACromosoma hijo2, ArrayList<ArrayList<Integer>> matriz) {
	
		//El primer indice por donde empezaremos
		int indice=(int) padre1.getCodificacion().get(0);
		
		//Random para luego
		Random r=new Random();
		
		//Metemos el primer elemento
		hijo1.getCodificacion().set(0, indice);
		int index=1;
		while(index<hijo1.getCodificacion().size())
		{
				ArrayList<Integer> posiblesIndices=new ArrayList<Integer>();
				int menor=Integer.MAX_VALUE;
				if(matriz.get(indice).size()>0)
				{
					for(int i=0;i<matriz.get(indice).size();i++) {
						if(!hijo1.getCodificacion().contains(matriz.get(indice).get(i)))
							posiblesIndices.add(matriz.get(indice).get(i));
					}
					
					//Tenemos todos los adyacentes de ese numero ya metidos, por tanto tenemos que volver atras
					//y desechar el numero
					if(posiblesIndices.size()==0)
					{
						index=1;
						for (int i = 1; i < hijo1.getCodificacion().size(); i++) {
							hijo1.getCodificacion().set(i, 100000);
						}
						indice=(int) hijo1.getCodificacion().get(0);
					}
					
					//En caso contrario nos dirijimos
					else
					{
						int posicion=r.nextInt(posiblesIndices.size());
						indice=posiblesIndices.get(posicion);
						hijo1.getCodificacion().set(index, indice);
						index++;
					}
				}
		
		}
		
		//Ahora para el hijo2
		//El primer indice por donde empezaremos
		//Metemos el primer elemento
		indice=(int) padre2.getCodificacion().get(0);
		hijo2.getCodificacion().set(0, indice);
		
		index=1;
		while(index<hijo2.getCodificacion().size())
		{
			ArrayList<Integer> posiblesIndices=new ArrayList<Integer>();
			int menor=Integer.MAX_VALUE;
			if(matriz.get(indice).size()>0)
			{
				for(int i=0;i<matriz.get(indice).size();i++) {
					if(!hijo2.getCodificacion().contains(matriz.get(indice).get(i)))
						posiblesIndices.add(matriz.get(indice).get(i));
				}
					
				//Tenemos todos los adyacentes de ese numero ya metidos, por tanto tenemos que volver atras
				//y desechar el numero
				if(posiblesIndices.size()==0)
				{
					index=1;
					for (int i = 1; i < hijo2.getCodificacion().size(); i++) {
						hijo2.getCodificacion().set(i, 100000);
					}
					indice=(int) hijo2.getCodificacion().get(0);
				}
				
				//En caso contrario nos dirijimos
				else
				{
					int posicion=r.nextInt(posiblesIndices.size());
					indice=posiblesIndices.get(posicion);
					hijo2.getCodificacion().set(index, indice);
					index++;
				}
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

		for(int i=0;i<hijo1.getCodificacion().size();i++) {
			System.out.print(hijo1.getCodificacion().get(i) +" ");
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
