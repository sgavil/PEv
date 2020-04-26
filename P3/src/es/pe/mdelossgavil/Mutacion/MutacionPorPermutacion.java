package es.pe.mdelossgavil.Mutacion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.Arbol;
import es.pe.mdelossgavil.Poblacion.CromosomaArboles;

public class MutacionPorPermutacion implements IMutacion {

	 public MutacionPorPermutacion() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void mutar(ACromosoma individuo) {
		// TODO Auto-generated method stub
		Arbol raiz = ((CromosomaArboles)individuo).getArbol().copia();
		
		ArrayList<Arbol> nodosFunciones = new ArrayList<Arbol>();
		nodosFunciones.add(raiz);
		
		raiz.getFunciones(raiz.getHijos(), nodosFunciones);
		
		if(nodosFunciones.size()==0)
		{
			int i =0;
			i+=7;
			
		}
		System.out.println(nodosFunciones.size());
		System.out.println(raiz.getNumHijos());
		int randNodoFuncion = (int) (Math.random()*nodosFunciones.size());
		Arbol nodoFuncAMutar = nodosFunciones.get(randNodoFuncion);
		
		if(nodoFuncAMutar.getHijos().size() > 2) {
			//Suponemos que es la funcion If con 3 partes y cambiamos el central y el derecho
			//Guardamos el central
			Arbol aux = nodoFuncAMutar.getHijos().get(1);
			//Se pone el derecho en el centro
			nodoFuncAMutar.getHijos().set(1, nodoFuncAMutar.getHijos().get(2));
			
			//Recuperamos el central colocandolo en el lado derecho
			nodoFuncAMutar.getHijos().set(2, aux);
			
			((CromosomaArboles)individuo).setMutatedTree(raiz.copia());

		}
		else if(nodoFuncAMutar.getHijos().size() == 2){
			//Intercambiamos el hijo izdo por el derecho
			
			//Guardamos el hijo izdo
			Arbol aux = nodoFuncAMutar.getHijos().get(0);
			
			//Ponemos el derecho en el lugar del izquierdo
			nodoFuncAMutar.getHijos().set(0, nodoFuncAMutar.getHijos().get(1));
			
			//Recuperamos el izquierdo en la posicion derecha
			nodoFuncAMutar.getHijos().set(1, aux);
			
			((CromosomaArboles)individuo).setMutatedTree(raiz.copia());

		}
		
		
	}

}
