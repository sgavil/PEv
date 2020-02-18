package es.pe.mdelossgavil;

import javax.swing.*;
//import org.math.plot.*;

import es.pe.mdelossgavil.Cruce.*;
import es.pe.mdelossgavil.Mutacion.*;
import es.pe.mdelossgavil.Seleccion.*;

public class Main {
	
	public static final int TAM_POB = 100;
	public static final int N_GENERACIONES = 1;
	
	
	public static void main(String[] args) {
		
		AlgoritmoGenetico a_genetico = new AlgoritmoGenetico(TAM_POB,N_GENERACIONES);
		
		Ruleta ruleta = new Ruleta();
		Monopunto cruce = new Monopunto();
		MutacionBoolean mutacion = new MutacionBoolean();
		
		a_genetico.inicializa(ruleta, cruce,mutacion,"F1");
		a_genetico.evaluar_poblacion();
		
		int i = 0;
		while(i < N_GENERACIONES) {
			a_genetico.seleccion();
			a_genetico.cruce();
			a_genetico.mutacion();
			a_genetico.evaluar_poblacion();
			i++;
		}
		
	}
}