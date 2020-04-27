package es.pe.mdelossgavil.Mutacion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.Arbol;
import es.pe.mdelossgavil.Poblacion.CromosomaArboles;

public class MutacionFuncionalSimple implements IMutacion {

	public MutacionFuncionalSimple() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void mutar(ACromosoma individuo) {
		// TODO Auto-generated method stub
		//int randFuncion = (int) (Math.random()*CromosomaArboles.funciones.length);
		Arbol funcion = getFuncionAleatoria((CromosomaArboles)individuo);
		
		if(funcion.getValor().equals("AND")) {
			funcion.setValor("OR");
		}
		else if(funcion.getValor().equals("OR")) {
			funcion.setValor("AND");
		}
		
	}
	
	private Arbol getFuncionAleatoria(CromosomaArboles c) {
		ArrayList<Arbol> nodosFuncion = new ArrayList<Arbol>();
		Arbol raiz = c.getArbol();
		nodosFuncion.add(raiz);
		
		raiz.getFunciones(raiz.getHijos(), nodosFuncion);
		
		int nNodosFuncion = nodosFuncion.size();
		int randNodoFuncion = (int) (Math.random()*nNodosFuncion);
		
		return nodosFuncion.get(randNodoFuncion);

	}

}
