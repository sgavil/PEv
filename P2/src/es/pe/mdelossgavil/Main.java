package es.pe.mdelossgavil;

import java.util.ArrayList;

import javax.swing.*;
//import org.math.plot.*;

import es.pe.mdelossgavil.Cruce.*;
import es.pe.mdelossgavil.GUI.P1Frame;
import es.pe.mdelossgavil.Graficas.Grafica;
import es.pe.mdelossgavil.Mutacion.*;
import es.pe.mdelossgavil.Poblacion.*;
import es.pe.mdelossgavil.Seleccion.*;

public class Main {

	public static boolean MAXIMIZAR = false;

	public static String PROBLEMA = "P2";

	public static boolean INICIAR_ALGORITMO = false;

	public static float ELITISMO = 0f;

	static double[] iteraciones;
	static double[] graficaMejorAbs;
	static double[] graficaMedia;
	static double[] graficaMejorRelativo;

	static P1Frame p1frame;

	public static String SELECCION;
	public static String CRUCE;

	private static JTextArea textResultado;

	private static final String DIR_DATOS = "datos/";

	public static int TAM_POB = 100;
	public static int N_GENERACIONES = 100;

	public static void main(String[] args) {

		AlgoritmoEvolutivo aEvolutivo = new AlgoritmoEvolutivo(TAM_POB, N_GENERACIONES, DIR_DATOS + "ajuste.txt");
		aEvolutivo.inicializa(new Torneos(2,false), new CO(), new MutacionPorReemplazamiento());

		aEvolutivo.funcion_revisar_adaptacion_minimiza();
		aEvolutivo.evaluar_poblacion();

		iteraciones = new double[N_GENERACIONES];
		graficaMejorAbs = new double[N_GENERACIONES];
		graficaMedia = new double[N_GENERACIONES];
		graficaMejorRelativo = new double[N_GENERACIONES];

		int i = 0;
		while (i < N_GENERACIONES) {
			//aEvolutivo.seleccion();
			//aEvolutivo.cruce();
			aEvolutivo.mutacion();
			aEvolutivo.evaluar_poblacion();
			iteraciones[i] = i;
			graficaMejorAbs[i] = aEvolutivo.mejor_abs.get_aptitud();
			graficaMedia[i] = aEvolutivo.get_aptitud_media();
			graficaMejorRelativo[i] = aEvolutivo.getEl_mejor().get_aptitud();
			i++;
		}

		System.out.println(aEvolutivo.getEl_mejor().get_aptitud());
		System.out.println((((CromosomaHospitales) aEvolutivo.getEl_mejor()).get_fenotipo()));
		// p1frame = new P1Frame();
		// p1frame.show();

	creaGrafica();

	}

	  public static void creaGrafica() {
		  
		  Grafica grafica = new Grafica(600, 600); grafica.inicializa_grafica();
		  grafica.agregar_linea("Mejor Absoluto", iteraciones, graficaMejorAbs);
		  grafica.agregar_linea("Mejor de cada generación", iteraciones,
		  graficaMejorRelativo); grafica.agregar_linea("Media", iteraciones,
		  graficaMedia);
		  
		  grafica.pinta_grafica(); }
	  
	/*
	public static void iniciaAlgoritmo() 
	  {
	  
	  // Panel de poblacion /*TAM_POB = (Integer)
	  p1frame.pPoblacion.pobSpinner.getValue(); N_GENERACIONES = (Integer)
	  p1frame.pPoblacion.genSpinner.getValue();
	  
	  // Panel de otros MAXIMIZAR = p1frame.pOtros.checkMaximizar.isSelected();
	  
	  // Panel seleccion ISeleccion iSeleccion = null;
	  
	  if (SELECCION.equals("Ruleta")) iSeleccion = new Ruleta();
	  
	  else if (SELECCION.equals("Estocástico Universal")) iSeleccion = new
	  EstocasticoUniversal();
	  
	  else if (SELECCION.equals("Torneos")) iSeleccion = new Torneos(2, MAXIMIZAR);
	  
	  else if (SELECCION.equals("Ranking")) iSeleccion = new Ranking(1.5f);
	  
	  // Panel cruce ICruce iCruce = null;
	  
	  if (CRUCE.equals("Monopunto")) iCruce = new Monopunto();
	  
	  else if (CRUCE.equals("Uniforme")) iCruce = new Uniforme();
	  
	  else if (CRUCE.equals("Discreto Uniforme")) iCruce = new DiscretoUniforme();
	  
	  else if (CRUCE.equals("Aritmético")) iCruce = new Aritmetico(0.6f);
	  
	  else if (CRUCE.equals("BLX-Alpha")) iCruce = new BLXAlpha();
	  
	  // PROBABILIDADES AlgoritmoGenetico.prob_cruce =
	  Float.parseFloat((p1frame.pCruce.probCruce.getText())) / 100;
	  AlgoritmoGenetico.prob_mut =
	  Float.parseFloat((p1frame.pMutacion.probMut.getText())) / 100; ELITISMO =
	  Float.parseFloat((p1frame.pOtros.valElitismo.getText())) / 100;
	  
	  // Cuadro de texto con resultados textResultado =
	  p1frame.pSelectorProblema.textArea;
	  
	  AlgoritmoGenetico a_genetico = new AlgoritmoGenetico(TAM_POB, N_GENERACIONES,
	  MAXIMIZAR);
	  
	  AlgoritmoGenetico.tolerancia =
	  Float.parseFloat(p1frame.pPoblacion.toleranciaTF.getText());
	  
	  IMutacion iMutacion = null; if (PROBLEMA.equals("P2")) iMutacion = new
	  MutacionReal(0, (float) Math.PI); else iMutacion = new MutacionBoolean();
	  
	  // INICIALIZACION DEL ALGORITMO GENETICO
	  
	  a_genetico.inicializa(iSeleccion, iCruce, iMutacion, PROBLEMA);
	  
	  ////////////////////////////////////////////////////////////////
	  
	  ArrayList<ACromosoma> elite = new ArrayList<ACromosoma>();
	  
	  a_genetico.evaluar_poblacion();
	  
	  // Inicializacion de graficas iteraciones = new double[N_GENERACIONES];
	  graficaMejorAbs = new double[N_GENERACIONES]; graficaMedia = new
	  double[N_GENERACIONES]; graficaMejorRelativo = new double[N_GENERACIONES];
	  
	  int i = 0; while (i < N_GENERACIONES) { 
	  iteraciones[i] = i;
	  
	  // Primero separamos los mejores if (ELITISMO > 0f) elite =
	  a_genetico.separaMejores(ELITISMO);
	  
	  a_genetico.seleccion(); a_genetico.cruce(); a_genetico.mutacion();
	  
	  // Antes de evaluar incluimos la elite if (ELITISMO > 0f) {
	  a_genetico.incluyeElite(elite); elite.clear(); }
	  
	  a_genetico.evaluar_poblacion();
	  
	  // Graficas
	  
	  graficaMejorAbs[i] = a_genetico.mejor_abs.get_aptitud(); graficaMedia[i] =
	  a_genetico.get_aptitud_media(); graficaMejorRelativo[i] =
	  a_genetico.getEl_mejor().get_aptitud(); i++; }

	float mejorValor = a_genetico.mejor_abs.get_aptitud();

	String resultado = "";

	resultado+="Resultado: "+mejorValor+"\n";

	if(PROBLEMA.equals("F1"))
	{
		resultado += "x1: " + ((CromosomaF1) (a_genetico.mejor_abs)).fenotipo_x1() + ", ";
		resultado += "x2: " + ((CromosomaF1) (a_genetico.mejor_abs)).fenotipo_x2();

	}else if(PROBLEMA.equals("F2"))
	{
		resultado += "x1: " + ((CromosomaF2) (a_genetico.mejor_abs)).fenotipo_x1() + ", ";
		resultado += "x2: " + ((CromosomaF2) (a_genetico.mejor_abs)).fenotipo_x2();

	}else if(PROBLEMA.equals("F3"))
	{
		resultado += "x1: " + ((CromosomaF3) (a_genetico.mejor_abs)).fenotipo_x1() + ", ";
		resultado += "x2: " + ((CromosomaF3) (a_genetico.mejor_abs)).fenotipo_x2();

	}else if(PROBLEMA.equals("F4"))
	{
		ArrayList<Float> arr;
		arr = ((CromosomaF4) (a_genetico.mejor_abs)).getFenotipos();
		for (int j = 0; j < arr.size(); j++) {
			resultado += "x" + j + ":" + arr.get(j) + ", ";
		}

	}else if(PROBLEMA.equals("P2"))
	{
		ArrayList<Float> arr;
		arr = ((CromosomaP2) (a_genetico.mejor_abs)).getFenotipos();
		for (int j = 0; j < arr.size(); j++) {
			resultado += "x" + j + ":" + arr.get(j) + ", ";
		}
	}

	textResultado.setText(resultado);resultado="";
}


*/
 

}