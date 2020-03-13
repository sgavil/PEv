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
public class MutacionPorReemplazamiento implements IMutacion{

	
	public MutacionPorReemplazamiento() {
		
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
				int lCrom = individuo.get_longitud();
				//Escogemos un numero de desplazamientos aleatorios
				Random r = new Random();
				int puntodeCorte = r.nextInt(lCrom);
				aplicarMutacion(individuo,puntodeCorte);
				/* Actualizamos los genes */
				int comienzo=0;
				for (int j = 0; j < individuo.get_genes().size(); j++) {
					/* Cogemos el tamanio del gen */
					TGen gen = (TGen) individuo.get_genes().get(j);
					int tam = gen.getGenotipo().size();
					/* Trasladamos el Array */
					actualizarGen(individuo, tam, comienzo, j);
					comienzo += tam;

				}
			}
			
			//Si se ha producido una mutación tenemos que volver a calcular la aptitud del individuo
			if(mutado) {
				poblacion.get(i).set_aptitud(poblacion.get(i).evaluar());

			}
		}
	}
	
	private void aplicarMutacion(ACromosoma invididuo,int puntodeCorte)
	{
		ArrayList<TGen> parte1=new ArrayList<TGen>();
		ArrayList<TGen> parte2=new ArrayList<TGen>();
		ArrayList<TGen> ultimo=new ArrayList<TGen>();
		for(int i=0;i<puntodeCorte;i++)
		{
			parte1.add((TGen) invididuo.getCodificacion().get(i));
		}
		for(int i=puntodeCorte;i<invididuo.get_longitud();i++)
		{
			parte2.add((TGen) invididuo.getCodificacion().get(i));
		}
		
		System.arraycopy(parte2, 0, ultimo, 0, parte2.size());
        System.arraycopy(parte1, 0, ultimo, parte2.size(), parte1.size());
        
        System.arraycopy(ultimo, 0, invididuo, 0, ultimo.size());
	}
		
	private void actualizarGen(ACromosoma cromosoma, int tam, int comienzo, int gen) {
		for (int i = 0; i < tam; i++) {
			((TGen) cromosoma.get_genes().get(gen)).getGenotipo().set(i, cromosoma.getCodificacion().get(i + comienzo));
		}
	}

}
