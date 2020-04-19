package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.Arbol;

public class CruceArboles implements ICruce{

	private static final double PROB_FUNC = 0.9;
	
	@Override
	public void reproduccion(ACromosoma p1, ACromosoma p2, ACromosoma h1, ACromosoma h2) 
	{
		// TODO Auto-generated method stub
		
		ArrayList<Arbol> nodos_selec1 = new ArrayList<Arbol>();
		ArrayList<Arbol> nodos_selec2 = new ArrayList<Arbol>();
		
		//Seleccionamos los nodos más relevante según la probabilidad
		//0.9 se cruzará en una función
		//resto se cruzará en un terminal
		nodos_selec1 = obtieneNodos(p1.getArbol().copia());
		nodos_selec2 = obtieneNodos(p2.getArbol().copia());
		
		//obtenemos los puntos de cruce a partir de los nodos seleccionados
		int puntoCruce1 = (int) (Math.random()*nodos_selec1.size());
		int puntoCruce2 = (int) (Math.random()*nodos_selec2.size());
		
		//copiamos los cromosomas padre en los hijos
		h1 = p1.copia();
		h2 = p2.copia();
		
		//Cogemos los nodos de cruce seleccionados
		Arbol temp1 = nodos_selec1.get(puntoCruce1).copia();
		Arbol temp2 = nodos_selec2.get(puntoCruce2).copia();
		
		//realizamos el corte sobre los arboles de los hijos
		corte(h1, temp2, puntoCruce1, temp1.isEsRaiz());
		corte(h2, temp1, puntoCruce2, temp2.isEsRaiz());
		
		int nodos = h1.getArbol().obtieneNodos(h1.getArbol(), 0);
		
		h1.getArbol().setNumNodos(nodos);
		nodos = h2.getArbol().obtieneNodos(h2.getArbol(), 0);
		h2.getArbol().setNumNodos(nodos);
		
		//Finalmente se evalúan
		h1.evalua();
		h2.evalua();
		
	}
}
