package es.pe.mdelossgavil.Mutacion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Main;
import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.Arbol;
import es.pe.mdelossgavil.Poblacion.CromosomaArboles;

public class MutacionTerminalSimple implements IMutacion {

	public MutacionTerminalSimple() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void mutar(ACromosoma individuo) {
		// TODO Auto-generated method stub
		
		Arbol terminal = ((CromosomaArboles)individuo).getArbol().getTerminalAleatorio();

	
		if(Main.entradas==6)
		{
			int randTerminal = (int) (Math.random()*CromosomaArboles.terminales6.length);
			terminal.setValor(CromosomaArboles.terminales6[randTerminal]);
		}
		else
		{
			int randTerminal = (int) (Math.random()*CromosomaArboles.terminales11.length);
			terminal.setValor(CromosomaArboles.terminales11[randTerminal]);
		}
	}
	
	
}
