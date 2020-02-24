package es.pe.mdelossgavil.Mutacion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.TGen;

/**
 * Clase que realiza la mutación de genes de tipo Boolean, es decir, mutación bit a bit
 *
 */
public class MutacionBoolean implements IMutacion{

	
	public MutacionBoolean() {
		
	}
	
	
	@Override
	public void mutar(ArrayList<ACromosoma> poblacion,float probMutacion) {
				
		//Recorremos todos los individuos de la población
		for (int i = 0; i < poblacion.size(); i++) 
		{
			
			boolean mutado = false;
			ACromosoma individuo = poblacion.get(i);
			ArrayList<TGen> genes = individuo.get_genes();
			
		
			//Recorremos todos los bits de cada cromosoma
			for (int j = 0; j < genes.size(); j++) 
			{
				//Recorremos los alelos de cada gen
				ArrayList<Boolean> alelos = genes.get(j).getGenotipo();
				for (int k = 0; k < alelos.size(); k++) {
					//Generamos un número aleatorio
					float rndProb = (float)Math.random();
					
					//Si es menor que la probabilidad de mutación actualizamos el bit
					// y lo marcamos como mutado
					if(rndProb < probMutacion) {
						genes.get(j).getGenotipo().set(k, !alelos.get(k));
						mutado = true;
					}
				}
				
			}
			//Si se ha producido una mutación tenemos que volver a calcular la aptitud del individuo
			if(mutado) {
				poblacion.get(i).set_aptitud(poblacion.get(i).evaluar());

			}
		}
		
	}

}
