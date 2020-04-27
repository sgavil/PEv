package es.pe.mdelossgavil.Mutacion;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.Arbol;
import es.pe.mdelossgavil.Poblacion.CromosomaArboles;

public class MutacionDeArbol implements IMutacion {

	public MutacionDeArbol() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void mutar(ACromosoma individuo) {
		Arbol aIndividuo = ((CromosomaArboles)individuo).getArbol();
		Arbol aux = new Arbol(aIndividuo.getMaxProf(),aIndividuo.getUseIf());
		aux.inicializacionCompleta(0, 0);
		
		((CromosomaArboles)individuo).setMutatedTree(aux);
		
	}

}
