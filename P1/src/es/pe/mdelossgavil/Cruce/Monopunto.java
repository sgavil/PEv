package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;
import java.util.Random;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaF1;
import es.pe.mdelossgavil.Poblacion.TGen;

public class Monopunto implements ICruce {

	public Monopunto() {
		
	}
	
	/**
	 * A partir de una poblacion dada,reproduce algunos de sus individuos
	 * y los hijos resultantes ocupan el lugar de los padres en la poblacion
	 */
	@Override
	public void reproduccion(ArrayList<ACromosoma> poblacion, String problema,float probCruce) {
		//Seleccionados para reproducir
		int tam_pob=poblacion.size();
		int seleccionCruce [] = new int[tam_pob];
		
		//Contador seleccionados
		int num_sele_cruce=0;
		int punto_cruce;
		float prob;
		
		ACromosoma hijo1 = null,hijo2=null;
		
		/*Cogemos el tipo de Cromosoma del problema*/
		if(problema=="F1")
		{
			hijo1=new CromosomaF1();
			hijo2=new CromosomaF1();
		}
		
		for(int i=0;i<tam_pob;i++)
		{
			//Creamos una prob aleatoria entre [0,1)
			prob=(float) Math.random();
			
			//En caso de que sea menos a la prob de Cruce
			//se escoge a ese invididuo para cruzarse
			if(prob<probCruce)
			{
				seleccionCruce[num_sele_cruce]=i;
				num_sele_cruce++;
			}
		}
		
		//El numero de seleccionados se hace par
		if(num_sele_cruce % 2==1)
			num_sele_cruce--;
		
		//Se cruzan los individuos seleccionados en un punto al azar
		int lCrom=hijo1.get_longitud();
		Random r=new Random();
		punto_cruce=r.nextInt(lCrom);
		
		for(int i=0;i<num_sele_cruce;i+=2)
		{
			ACromosoma padre1=poblacion.get(seleccionCruce[i]);
			ACromosoma padre2=poblacion.get(seleccionCruce[i]);
			Cruce(padre1, padre2, hijo1, hijo2, punto_cruce);
		}
				
	}
	
	/**
	 * @param padre1 Primer cromosoma padre
	 * @param padre2 Segundo cromosoma padre
	 * @param hijo1 primer cromosoma hijo
	 * @param hijo2 segundo cromosoma hijo
	 * @param puntoCruce prob de hacer el cruce entre dos crosomas
	 */
	private void Cruce(ACromosoma padre1,ACromosoma padre2,ACromosoma hijo1,ACromosoma hijo2,int puntoCruce)
	{
		
		// primera parte del intercambio: 1 a 1 y 2 a 2
		for(int i=0;i<puntoCruce;i++)
		{
			hijo1.get_genes().add(padre1.get_genes().get(i));
			hijo2.get_genes().add(padre2.get_genes().get(i));
		}
		
		// segunda parte: 1 a 2 y 2 a 1
		for(int i=puntoCruce;i<hijo1.get_longitud();i++)
		{
			hijo1.get_genes().add(padre2.get_genes().get(i));
			hijo2.get_genes().add(padre1.get_genes().get(i));
		}
		
		//Una vez hecho el cruce, se evalua
		hijo1.set_aptitud(hijo1.evaluar());
		hijo2.set_aptitud(hijo2.evaluar());	
	}

}
