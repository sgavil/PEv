package es.pe.mdelossgavil.Mutacion;

import java.util.ArrayList;
import java.util.Random;

import org.omg.CORBA.portable.IndirectionException;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.TGen;

/**
 * Clase que realiza la mutación de genes de tipo Boolean, es decir, mutación bit a bit
 *
 */
public class MutacionPorInversion implements IMutacion{

	
	public MutacionPorInversion() {
		
	}
	
	
	@Override
	public void mutar(ArrayList<ACromosoma> poblacion,float probMutacion) {
				
		if(probMutacion <= 0f)
			return;
		
		//Recorremos todos los individuos de la población
		for (int i = 0; i < poblacion.size(); i++) 
		{
			boolean mutado = false;
			ACromosoma individuo = poblacion.get(i);
			
			//Generamos un número aleatorio
			float rndProb = (float)Math.random();
			
			//Si es menor que la probabilidad de mutación actualizamos el bit
			// y lo marcamos como mutado
			if(rndProb < probMutacion) {
				
				int principio,fin;
				int lCrom = individuo.get_longitud();
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
				
				aplicarMutacion(individuo, principio, fin);
				mutado=true;
				
				/* Actualizamos los genes */
				int comienzo = 0;
				for (int j = 0; j < individuo.get_genes().size(); j++) {
					/* Cogemos el tamanio del gen */
					TGen gen = (TGen) individuo.get_genes().get(j);
					int tam = gen.getGenotipo().size();
					/* Trasladamos el Array */
					actualizarGen(individuo, tam, comienzo, j);
					comienzo += tam;

				}
				
				mutado=true;
			}
			
			//Si se ha producido una mutación tenemos que volver a calcular la aptitud del individuo
			if(mutado) {
				poblacion.get(i).set_aptitud(poblacion.get(i).evaluar());

			}
		}
	}
	
	private <T> void aplicarMutacion(ACromosoma invididuo,int comienzo, int fin)
	{
		for (int i = comienzo; i < (fin/2); i++) {
			T aux=(T)invididuo.getCodificacion().get(comienzo+i);
			invididuo.getCodificacion().set(comienzo+i, invididuo.getCodificacion().get(fin-i));
			invididuo.getCodificacion().set(fin-i, aux);
		}
	}
	
	private void actualizarGen(ACromosoma cromosoma, int tam, int comienzo, int gen) {
		for (int i = 0; i < tam; i++) {
			((TGen) cromosoma.get_genes().get(gen)).getGenotipo().set(i, cromosoma.getCodificacion().get(i + comienzo));
		}
	}

}
