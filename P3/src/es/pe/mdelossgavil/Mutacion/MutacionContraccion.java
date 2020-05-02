package es.pe.mdelossgavil.Mutacion;

import es.pe.mdelossgavil.Main;
import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.Arbol;
import es.pe.mdelossgavil.Poblacion.CromosomaArboles;

public class MutacionContraccion implements IMutacion {

	@Override
	public void mutar(ACromosoma individuo) {
		Arbol funcion = ((CromosomaArboles)individuo).getArbol().getFuncionAleatoria();
		

		if(Main.entradas==6)
		{
			int randTerminal = (int) (Math.random()*CromosomaArboles.terminales6.length);
			
			funcion.setValor(CromosomaArboles.terminales6[randTerminal]);
		}
		else
		{
			int randTerminal = (int) (Math.random()*CromosomaArboles.terminales11.length);
			funcion.setValor(CromosomaArboles.terminales11[randTerminal]);
		}

	}

}
