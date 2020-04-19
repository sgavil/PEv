package es.pe.mdelossgavil.Cruce;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.Arbol;
import es.pe.mdelossgavil.Poblacion.CromosomaArboles;

public class CruceArboles implements ICruce {

	private static final double PROB_FUNC = 0.9;

	@Override
	public void reproduccion(CromosomaArboles p1, CromosomaArboles p2, CromosomaArboles h1, CromosomaArboles h2) {
		// TODO Auto-generated method stub

		ArrayList<Arbol> nodos_selec1 = new ArrayList<Arbol>();
		ArrayList<Arbol> nodos_selec2 = new ArrayList<Arbol>();

		// Seleccionamos los nodos más relevante según la probabilidad
		// 0.9 se cruzará en una función
		// resto se cruzará en un terminal
		nodos_selec1 = obtieneNodos(p1.getArbol().copia());
		nodos_selec2 = obtieneNodos(p2.getArbol().copia());

		// obtenemos los puntos de cruce a partir de los nodos seleccionados
		int puntoCruce1 = (int) (Math.random() * nodos_selec1.size());
		int puntoCruce2 = (int) (Math.random() * nodos_selec2.size());

		// copiamos los cromosomas padre en los hijos
		h1 = p1.copia();
		h2 = p2.copia();

		// Cogemos los nodos de cruce seleccionados
		Arbol temp1 = nodos_selec1.get(puntoCruce1).copia();
		Arbol temp2 = nodos_selec2.get(puntoCruce2).copia();

		// realizamos el corte sobre los arboles de los hijos
		corte((CromosomaArboles)h1, temp2, puntoCruce1, temp1.isEsRaiz());
		corte((CromosomaArboles)h2, temp1, puntoCruce2, temp2.isEsRaiz());

		int nodos = h1.getArbol().obtieneNodos(h1.getArbol(), 0);

		((CromosomaArboles)h1).getArbol().setNumNodos(nodos);
		nodos = ((CromosomaArboles)h2).getArbol().obtieneNodos(h2.getArbol(), 0);
		((CromosomaArboles)h2).getArbol().setNumNodos(nodos);

		// Finalmente se evalúan
		h1.evaluar();
		h2.evaluar();

	}

	private void corte(CromosomaArboles hijo, Arbol temp, int puntoCruce, boolean esRaiz) 
	{
		if (!esRaiz) {
			// dependiendo de qué tipo era el nodo que ya no va a estar, se inserta el nuevo
			hijo.getArbol().insertTerminal(hijo.getArbol().getHijos(), temp, puntoCruce, 0);
		} else {
			hijo.getArbol().insertFuncion(hijo.getArbol().getHijos(), temp, puntoCruce, 0);
		}
	}

	private ArrayList<Arbol> obtieneNodos(Arbol arbol) 
	{
		ArrayList<Arbol> nodos = new ArrayList<Arbol>();
		// Obtenemos una probabilidad al azar
		if (seleccionaFunciones()) {// Si devuelve true, el corte se hará en una función
			arbol.getFunciones(arbol.getHijos(), nodos);
			if (nodos.size() == 0) {// Si no existen funciones, se seleccionan los terminales
				arbol.getTerminales(arbol.getHijos(), nodos);
			}
		} else {// Si devuelve false, el corte se hará por un terminal
			arbol.getTerminales(arbol.getHijos(), nodos);
		}
		return nodos;
	}

	private boolean seleccionaFunciones() 
	{
		double prob = Math.random();
		if (prob < PROB_FUNC) {
			return true;
		} else {
			return false;
		}
	}
}
