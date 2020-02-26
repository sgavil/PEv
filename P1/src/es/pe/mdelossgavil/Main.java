package es.pe.mdelossgavil;

import java.util.ArrayList;

import javax.swing.*;
//import org.math.plot.*;

import es.pe.mdelossgavil.Cruce.*;
import es.pe.mdelossgavil.Graficas.Grafica;
import es.pe.mdelossgavil.Mutacion.*;
import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Seleccion.*;

public class Main {

	public static final int TAM_POB = 100;
	public static final int N_GENERACIONES = 100;
	public static boolean MAXIMIZAR = false;
	public static boolean ELITISMO= true;
	public static int tamElite=5;
	public static void main(String[] args) {

		AlgoritmoGenetico a_genetico = new AlgoritmoGenetico(TAM_POB, N_GENERACIONES,MAXIMIZAR);
		double[] iteraciones = new double[N_GENERACIONES];
		double[] graficaMejorAbs = new double[N_GENERACIONES];
		double[] graficaMedia = new double[N_GENERACIONES];
		double[] graficaMejorRelativo = new double[N_GENERACIONES];

		
		
		Ruleta ruleta = new Ruleta();
		Torneos torneos = new Torneos(2,MAXIMIZAR);
		EstocasticoUniversal estocasticoUniversal = new EstocasticoUniversal();
		Ranking ranking = new Ranking(1.5f);
		
		Monopunto mono = new Monopunto();
		DiscretoUniforme DU=new DiscretoUniforme();
		MutacionBoolean mutacion = new MutacionBoolean();
		
		ArrayList<ACromosoma> elite=new ArrayList<ACromosoma>();


		a_genetico.inicializa(ranking, mono, mutacion, "F4");
		a_genetico.evaluar_poblacion();

		int i = 0;
		while (i < N_GENERACIONES) {
			iteraciones[i] = i;
			
			//Primero separamos los mejores
			if(ELITISMO)
				elite=a_genetico.separaMejores(tamElite);
			a_genetico.seleccion();
			a_genetico.cruce();
			a_genetico.mutacion();
			
			//Antes de evaluar incluimos la elite
			if(ELITISMO)
			{
				a_genetico.incluyeElite(elite);
				elite.clear();
			}
			a_genetico.evaluar_poblacion();
			
			//Graficas
			graficaMejorAbs[i] = a_genetico.mejor_abs.get_aptitud();
			graficaMedia[i] = a_genetico.get_aptitud_media();
			graficaMejorRelativo[i] = a_genetico.getEl_mejor().get_aptitud();
			i++;
		}

		Grafica grafica = new Grafica(600, 600);
		grafica.inicializa_grafica();
		grafica.agregar_linea("Mejor Absoluto", iteraciones, graficaMejorAbs);
		grafica.agregar_linea("Media", iteraciones, graficaMedia);
		grafica.agregar_linea("Mejor de cada generaci�n", iteraciones, graficaMejorRelativo);
		
		grafica.pinta_grafica();
		System.out.println("EL MILL0R: " + a_genetico.mejor_abs.get_aptitud());

		

	}
}