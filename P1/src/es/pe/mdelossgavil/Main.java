package es.pe.mdelossgavil;

import javax.swing.*;
//import org.math.plot.*;

import es.pe.mdelossgavil.Cruce.*;
import es.pe.mdelossgavil.Graficas.Grafica;
import es.pe.mdelossgavil.Mutacion.*;
import es.pe.mdelossgavil.Seleccion.*;

public class Main {

	public static final int TAM_POB = 100;
	public static final int N_GENERACIONES = 100;

	public static void main(String[] args) {

		AlgoritmoGenetico a_genetico = new AlgoritmoGenetico(TAM_POB, N_GENERACIONES);
		double[] x = new double[N_GENERACIONES];
		double[] y = new double[N_GENERACIONES];
		Ruleta ruleta = new Ruleta();
		Monopunto cruce = new Monopunto();
		MutacionBoolean mutacion = new MutacionBoolean();

		a_genetico.inicializa(ruleta, cruce, mutacion, "F1");
		a_genetico.evaluar_poblacion();

		int i = 0;
		while (i < N_GENERACIONES) {
			x[i] = i;
			a_genetico.seleccion();
			// System.out.println(a_genetico.get_aptitud_media());
			//a_genetico.cruce();
			a_genetico.mutacion();
			a_genetico.evaluar_poblacion();
			y[i] = a_genetico.getEl_mejor().get_aptitud();

			i++;
		}

		Grafica grafica = new Grafica(600, 600);
		grafica.inicializa_grafica();
		grafica.agregar_linea("lineaUno", x, y);
		grafica.pinta_grafica();
		System.out.println("EL MILL0R: " + a_genetico.mejor_abs.get_aptitud());

		a_genetico.mejor_abs.evaluar();

	}
}