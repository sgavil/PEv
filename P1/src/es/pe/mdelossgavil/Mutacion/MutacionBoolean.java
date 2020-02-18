package es.pe.mdelossgavil.Mutacion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.TGen;

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
			
			System.out.println("Individuo: " + i + " Aptitud: "+ individuo.get_aptitud());
			
			//Recorremos todos los bits de cada cromosoma
			for (int j = 0; j < genes.size(); j++) 
			{
				//Generamos un número aleatorio
				float rndProb = (float)Math.random();
				
				//Si es menor que la probabilidad de mutación actualizamos el bit
				// y lo marcamos como mutado
				if(rndProb < probMutacion) {
					boolean bit = (Boolean)genes.get(j).getValor(); 
					genes.get(j).setValor(!bit);
					mutado = true;
				}
			}
			
			//Si se ha producido una mutación tenemos que volver a calcular la aptitud del individuo
			if(mutado) {
				individuo.set_aptitud(individuo.evaluar());
				System.out.println("Individuo MUTADO: " + i + " Aptitud" + individuo.get_aptitud());

			}
		}
		
	}

}
