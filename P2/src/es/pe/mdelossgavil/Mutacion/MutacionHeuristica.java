package es.pe.mdelossgavil.Mutacion;

import java.util.ArrayList;
import java.util.Random;

import org.omg.CORBA.portable.IndirectionException;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaHospitales;
import es.pe.mdelossgavil.Poblacion.TGen;

/**
 * Clase que realiza la mutación de genes de tipo Boolean, es decir, mutación bit a bit
 *
 */
public class MutacionHeuristica implements IMutacion{

	
	public MutacionHeuristica() {
		
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
				
				int nIndividuos=r.nextInt((3 - 2) + 1) + 2;
				ArrayList<Integer> numbers = new ArrayList<Integer>();
				ArrayList<Integer> posiciones = new ArrayList<Integer>();
				for (int j = 0; j < nIndividuos; j++) {
					int random=r.nextInt(individuo.get_longitud());
					while(numbers.contains(individuo.getCodificacion().get(random)))
						random=r.nextInt(individuo.get_longitud());
					numbers.add((Integer) individuo.getCodificacion().get(random));
					posiciones.add(random);
				}
				
				aplicarMutacion(individuo,numbers,posiciones);
				mutado=true;
			}
			
			//Si se ha producido una mutación tenemos que volver a calcular la aptitud del individuo
			if(mutado) {
				poblacion.get(i).set_aptitud(poblacion.get(i).evaluar());

			}
		}
	}
	
	//Calcula todas las permutaciones dadas por un array
	public static ArrayList<ArrayList<Integer>> listPermutations(ArrayList<Integer> list) {

	    if (list.size() == 0) {
	    	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	        result.add(new ArrayList<Integer>());
	        return result;
	    }

	    ArrayList<ArrayList<Integer>> returnMe = new ArrayList<ArrayList<Integer>>();

	    Integer firstElement = list.remove(0);

	    ArrayList<ArrayList<Integer>> recursiveReturn = listPermutations(list);
	    for (ArrayList<Integer> li : recursiveReturn) {

	        for (int index = 0; index <= li.size(); index++) {
	        	ArrayList<Integer> temp = new ArrayList<Integer>(li);
	            temp.add(index, firstElement);
	            returnMe.add(temp);
	        }

	    }
	    return returnMe;
	}
	
	private <T> void aplicarMutacion(ACromosoma invididuo, ArrayList<Integer> numbers,ArrayList<Integer> posiciones)
	{
		//Lo primero sera conseguir todas las permutaciones
		ArrayList<ArrayList<Integer>> permutaciones=listPermutations(numbers);
		
		float minAptitud=Float.MAX_VALUE;
		CromosomaHospitales mejor=null;
		for(int i=0;i<permutaciones.size();i++)
		{
			CromosomaHospitales aux=new CromosomaHospitales(invididuo);
			//Por cada permutacion miramos cual es su aptitud, asi que primero construimos el cromosoma
			for(int j=0;j<posiciones.size();j++){
				aux.getCodificacion().set(posiciones.get(j), permutaciones.get(i).get(j));
			}
			
			/* Actualizamos los genes */
			int comienzo = 0;
			for (int j = 0; j < aux.get_genes().size(); j++) {
				/* Cogemos el tamanio del gen */
				TGen gen = (TGen) aux.get_genes().get(j);
				int tam = gen.getGenotipo().size();
				/* Trasladamos el Array */
				actualizarGen(aux, tam, comienzo, j);
				comienzo += tam;
			}
			
			//Ahora calculamos su aptitud y comprobamos si es mejor que el mejor absoluto
			aux.set_aptitud(aux.evaluar());
			float apt=aux.get_aptitud();
			if(apt<minAptitud)
			{
				minAptitud=apt;
				mejor=aux;
				mejor.set_puntuacion(0);
				mejor.set_punt_acum(0);
			}
		}
		invididuo = new CromosomaHospitales(mejor);
	}
	
	private void actualizarGen(ACromosoma cromosoma, int tam, int comienzo, int gen) {
		for (int i = 0; i < tam; i++) {
			((TGen) cromosoma.get_genes().get(gen)).getGenotipo().set(i, cromosoma.getCodificacion().get(i + comienzo));
		}
	}

}
