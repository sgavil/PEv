package es.pe.mdelossgavil.Mutacion;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.Arbol;
import es.pe.mdelossgavil.Poblacion.CromosomaArboles;

public class MutacionHoist implements IMutacion {

	@Override
	public void mutar(ACromosoma individuo) {
		Arbol funcion = ((CromosomaArboles)individuo).getArbol().getFuncionAleatoria();

		((CromosomaArboles)individuo).setMutatedTree(funcion.copia());
	}

}
