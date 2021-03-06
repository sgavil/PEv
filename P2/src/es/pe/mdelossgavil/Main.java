package es.pe.mdelossgavil;

import java.util.ArrayList;

import javax.swing.JTextArea;

import es.pe.mdelossgavil.Cruce.CO;
import es.pe.mdelossgavil.Cruce.CX;
import es.pe.mdelossgavil.Cruce.CruceImparesOrdenados;
import es.pe.mdelossgavil.Cruce.ERX;
import es.pe.mdelossgavil.Cruce.ICruce;
import es.pe.mdelossgavil.Cruce.OX;
import es.pe.mdelossgavil.Cruce.OX_PP;
import es.pe.mdelossgavil.Cruce.PMX;
import es.pe.mdelossgavil.GUI.P1Frame;
import es.pe.mdelossgavil.Graficas.Grafica;
import es.pe.mdelossgavil.Mutacion.IMutacion;
import es.pe.mdelossgavil.Mutacion.MutacionHeuristica;
import es.pe.mdelossgavil.Mutacion.MutacionPorInserccion;
import es.pe.mdelossgavil.Mutacion.MutacionPorIntercambio;
import es.pe.mdelossgavil.Mutacion.MutacionPorInversion;
import es.pe.mdelossgavil.Mutacion.MutacionPorReemplazamiento;
import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaHospitales;
import es.pe.mdelossgavil.Seleccion.EstocasticoUniversal;
import es.pe.mdelossgavil.Seleccion.ISeleccion;
import es.pe.mdelossgavil.Seleccion.Ranking;
import es.pe.mdelossgavil.Seleccion.Ruleta;
import es.pe.mdelossgavil.Seleccion.Torneos;
import es.pe.mdelossgavil.Seleccion.Truncamiento;

public class Main {

	public static boolean MAXIMIZAR = false;


	public static boolean INICIAR_ALGORITMO = false;

	public static float ELITISMO = 0f;

	static double[] iteraciones;
	static double[] graficaMejorAbs;
	static double[] graficaMedia;
	static double[] graficaMejorRelativo;

	static P1Frame p1frame;

	public static String SELECCION;
	public static String CRUCE;
	public static String MUTACION;

	public static String NOMBRE_ARCHIVO = "ajuste.txt";

	private static JTextArea textResultado;

	private static final String DIR_DATOS = "datos/";

	public static int TAM_POB = 100;
	public static int N_GENERACIONES = 100;
	
	private static Grafica grafica;

	public static void main(String[] args)

	{
		p1frame = new P1Frame();
		p1frame.show();
		grafica = p1frame.grafica;
		
		

	}

	public static void creaGrafica() {

		grafica.inicializa_grafica();
		grafica.agregar_linea("Mejor Absoluto", iteraciones, graficaMejorAbs);
		grafica.agregar_linea("Mejor de cada generación", iteraciones, graficaMejorRelativo);
		grafica.agregar_linea("Media", iteraciones, graficaMedia);
		grafica.pinta_grafica();
	}

	public static void iniciaAlgoritmo() {

		
		
		if (NOMBRE_ARCHIVO.equals("ajuste")) {
			NOMBRE_ARCHIVO = "ajuste.txt";

		} else if (NOMBRE_ARCHIVO.equals("datos12")) {
			NOMBRE_ARCHIVO = "datos12.txt";

			
		} 
		else if (NOMBRE_ARCHIVO.equals("datos15")) {
			NOMBRE_ARCHIVO = "datos15.txt";

		}
		else if (NOMBRE_ARCHIVO.equals("datos30")) {
			NOMBRE_ARCHIVO = "datos30.txt";

		} 

		// Panel de poblacion /*
		TAM_POB = (Integer) p1frame.panelWest.pPoblacion.pobSpinner.getValue();
		N_GENERACIONES = (Integer) p1frame.panelWest.pPoblacion.genSpinner.getValue();

		iteraciones = new double[N_GENERACIONES];

		// Panel seleccion
		ISeleccion iSeleccion = null;

		if (SELECCION.equals("Ruleta"))
			iSeleccion = new Ruleta();

		else if (SELECCION.equals("Estocástico Universal"))
			iSeleccion = new EstocasticoUniversal();

		else if (SELECCION.equals("Torneos"))
			iSeleccion = new Torneos(2, false);

		else if (SELECCION.equals("Ranking"))
			iSeleccion = new Ranking(1.5f);
		
		else if (SELECCION.equals("Truncamiento"))
			iSeleccion = new Truncamiento(50);

		// Panel cruce
		ICruce iCruce = null;

		if (CRUCE.equals("CO"))
			iCruce = new CO();

		else if (CRUCE.equals("CX"))
			iCruce = new CX();

		else if (CRUCE.equals("OX_PP"))
			iCruce = new OX_PP();

		else if (CRUCE.equals("OX"))
			iCruce = new OX();

		else if (CRUCE.equals("PMX"))
			iCruce = new PMX();
		

		else if (CRUCE.equals("CIO"))
			iCruce=new CruceImparesOrdenados();

		else if (CRUCE.equals("ERX"))
			iCruce = new ERX();

		
		// PROBABILIDADES
		AlgoritmoEvolutivo.prob_cruce = Float.parseFloat((p1frame.panelWest.pCruce.probCruce.getText())) / 100;
		AlgoritmoEvolutivo.prob_mut = Float.parseFloat((p1frame.panelWest.pMutacion.probMut.getText())) / 100;
		ELITISMO = Float.parseFloat((p1frame.panelWest.pOtros.valElitismo.getText())) / 100;

		// Cuadro de texto con resultados
		textResultado = p1frame.panelWest.pSelectorProblema.textArea;

		AlgoritmoEvolutivo aEvolutivo = new AlgoritmoEvolutivo(TAM_POB, N_GENERACIONES, DIR_DATOS + NOMBRE_ARCHIVO);

		// AlgoritmoEvolutivo.tolerancia =
		// Float.parseFloat(p1frame.pPoblacion.toleranciaTF.getText());

		IMutacion iMutacion = null;

		if (MUTACION.equals("Mutacion por inserccion"))
			iMutacion = new MutacionPorInserccion();

		else if (MUTACION.equals("Mutacion por intercambio"))
			iMutacion = new MutacionPorIntercambio();

		else if (MUTACION.equals("Mutacion por inversion"))
			iMutacion = new MutacionPorInversion();

		else if (MUTACION.equals("Mutacion por reemplazamiento"))
			iMutacion = new MutacionPorReemplazamiento();
		
		else if (MUTACION.equals("Mutacion heuristica"))
			iMutacion = new MutacionHeuristica();
		

		// INICIALIZACION DEL ALGORITMO GENETICO

		aEvolutivo.inicializa(iSeleccion, iCruce, iMutacion);

		////////////////////////////////////////////////////////////////

		ArrayList<ACromosoma> elite = new ArrayList<ACromosoma>();

		aEvolutivo.evaluar_poblacion();

		// Inicializacion de graficas iteraciones = new double[N_GENERACIONES];
		graficaMejorAbs = new double[N_GENERACIONES];
		graficaMedia = new double[N_GENERACIONES];
		graficaMejorRelativo = new double[N_GENERACIONES];

		int i = 0;
		while (i < N_GENERACIONES) {
			iteraciones[i] = i;

			// Primero separamos los mejores
			if (ELITISMO > 0f)
				elite = aEvolutivo.separaMejores(ELITISMO);

			aEvolutivo.seleccion();
			aEvolutivo.cruce();
			aEvolutivo.mutacion();
			
			// Antes de evaluar incluimos la elite
			if (ELITISMO > 0f) {
				aEvolutivo.incluyeElite(elite);
				elite.clear();
			}
			
			aEvolutivo.evaluar_poblacion();
			
			// Graficas
			graficaMejorAbs[i] = aEvolutivo.mejor_abs.get_aptitud();
			graficaMedia[i] = aEvolutivo.get_aptitud_media();
			graficaMejorRelativo[i] = aEvolutivo.getEl_mejor().get_aptitud();
			i++;
			
			
		}

		// Graficas
		float mejorValor = aEvolutivo.mejor_abs.get_aptitud();

		String resultado = "";

		resultado += "Resultado: " + mejorValor + "\n" + ((CromosomaHospitales) aEvolutivo.mejor_abs).get_fenotipo() 
				+"\n" + "N.Mutaciones: " +aEvolutivo.nMutaciones + "\n" + "N.Cruces: " + aEvolutivo.nCruces;

		textResultado.setText(resultado);
		//System.out.println(resultado);
		resultado = "";
		

		creaGrafica();
		
		}
	

}
