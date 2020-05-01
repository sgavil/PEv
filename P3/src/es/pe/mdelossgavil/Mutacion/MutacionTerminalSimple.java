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
		
		if(Main.entradas==6)
		{
			int randTerminal = (int) (Math.random()*CromosomaArboles.terminales6.length);
			Arbol terminal = getTerminalAleatorio((CromosomaArboles)individuo);
			terminal.setValor(CromosomaArboles.terminales6[randTerminal]);
		}
		else
		{
			int randTerminal = (int) (Math.random()*CromosomaArboles.terminales11.length);
			Arbol terminal = getTerminalAleatorio((CromosomaArboles)individuo);
			terminal.setValor(CromosomaArboles.terminales11[randTerminal]);
		}
	}
	
	private Arbol getTerminalAleatorio(CromosomaArboles c) {
		ArrayList<Arbol> nodosTerminales = new ArrayList<Arbol>();
		Arbol raiz = c.getArbol();
		
		raiz.getTerminales(raiz.getHijos(), nodosTerminales);
		
		int nNodosTerminales = nodosTerminales.size();
		int randNodoTerminal = (int) (Math.random()*nNodosTerminales);
		
		return nodosTerminales.get(randNodoTerminal);

	}
	

}
