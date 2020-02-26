package es.pe.mdelossgavil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import es.pe.mdelossgavil.Cruce.ICruce;
import es.pe.mdelossgavil.Mutacion.IMutacion;
import es.pe.mdelossgavil.Poblacion.*;
import es.pe.mdelossgavil.Seleccion.*;

public class AlgoritmoGenetico {

	public AlgoritmoGenetico(int tam_poblacion, int generaciones,boolean maximizar) {
		tam_pob = tam_poblacion;
		num_max_gen = generaciones;
		this.maximizar = maximizar;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Atributos de la población
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	// Población de individuos
	private ArrayList<ACromosoma> poblacion;

	// Tamaño de la población
	private int tam_pob;

	// Número máximo de generaciones
	private int num_max_gen;

	private boolean maximizar;

	// Indicador del problema actual
	String problemaActual = "";

	// Mejor individuo
	private ACromosoma el_mejor;

	public ACromosoma mejor_abs;

	public ACromosoma getEl_mejor() {
		return el_mejor;
	}

	public void setEl_mejor(ACromosoma el_mejor) {
		this.el_mejor = el_mejor;
	}

	private int pos_mejor;
	private float prob_cruce = 0.6f;
	private float prob_mut = 0.05f;
	public static float tolerancia = 0.001f;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Selección, Cruce y Mutación
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	private ISeleccion metodo_seleccion;
	private ICruce metodo_cruce;
	private IMutacion metodo_mutacion;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Métodos públicos del algoritmo genético
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	/**
	 * Asigna las formas de selección,cruce, mutación además del tipo de cromosoma,
	 * en función del problema. Inicializa una población con dicho tipo de cromosoma
	 * 
	 * @param seleccion      tipo de seleccion
	 * @param cruce          tipo de cruce
	 * @param mutacion       tipo de mutacion
	 * @param tipo_cromosoma tipo de cromosoma
	 */
	public void inicializa(ISeleccion seleccion, ICruce cruce, IMutacion mutacion, String tipo_cromosoma) {
		/* Guardamos los operadores */
		metodo_seleccion = seleccion;
		metodo_cruce = cruce;
		metodo_mutacion = mutacion;
		problemaActual = tipo_cromosoma;

		// Inicializamos la población
		inicializa_poblacion();

	}

	/**
	 * Evalua la población y elige al mejor individuo
	 */
	public void evaluar_poblacion() 
	{
		float punt_acum = 0;
		float aptitud_mejor;

		float suma_aptitud = 0;

		ACromosoma individuoActual;
		
		if(maximizar)
		aptitud_mejor = Float.MIN_VALUE;
		else
			aptitud_mejor = Float.MAX_VALUE;


		/*
		 * Primero hacemos un for para tener la suma de las aptitudes y la mejor aptitud
		 * de todas
		 */
		for (int i = 0; i < tam_pob; i++) 
		{
			individuoActual = poblacion.get(i);
			suma_aptitud += individuoActual.get_aptitud();

			if(maximizar) {
				if (individuoActual.get_aptitud() > aptitud_mejor) 
				{
					pos_mejor = i;
					aptitud_mejor = individuoActual.get_aptitud();
					el_mejor = individuoActual;
				}
			}
			else {
				if (individuoActual.get_aptitud() < aptitud_mejor) 
				{
					pos_mejor = i;
					aptitud_mejor = individuoActual.get_aptitud();
					el_mejor = individuoActual;
				}
			}
			
		}

		for (int j = 0; j < tam_pob; j++) 
		{
			// Calculamos la puntuación del individuo y su puntuación acumulada
			float puntuacion = poblacion.get(j).get_aptitud() / suma_aptitud;

			poblacion.get(j).set_puntuacion(puntuacion);
			poblacion.get(j).set_punt_acum(puntuacion + punt_acum);

			// Actualizamos la puntuación acumulada general
			punt_acum += puntuacion;
			
		}
		if(maximizar) {
			if (el_mejor.get_aptitud() > mejor_abs.get_aptitud()) {
				mejor_abs = el_mejor;
			}
		}
		else {
			if (el_mejor.get_aptitud() < mejor_abs.get_aptitud()) {
				mejor_abs = el_mejor;
			}
		}
		
	}

	public float get_aptitud_media() {
		int total = 0;
		for (int i = 0; i < poblacion.size(); i++) {
			total += poblacion.get(i).get_aptitud();
		}
		return total / poblacion.size();
	}

	/**
	 * Proceso de selección
	 */
	public void seleccion() {
		poblacion = metodo_seleccion.hacer_seleccion(poblacion,problemaActual);
	}

	/**
	 * Proceso de cruce
	 */
	public void cruce() {
		metodo_cruce.reproduccion(poblacion, problemaActual, prob_cruce);
	}

	/**
	 * Proceso de mutación
	 */
	public void mutacion() {
		metodo_mutacion.mutar(poblacion, prob_mut);
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Métodos privados del algoritmo genético
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	private void inicializa_poblacion() {
		if (problemaActual.equals("F1")) {
			TPoblacion<CromosomaF1> pob = new TPoblacion<CromosomaF1>();
			poblacion = pob.inicializa_poblacion(tam_pob, CromosomaF1.class);
			mejor_abs = new CromosomaF1();
			mejor_abs.set_aptitud(Float.MIN_VALUE);
			maximizar = true;
			return;
		} else if (problemaActual.equals("F2")) {
			TPoblacion<CromosomaF2> pob = new TPoblacion<CromosomaF2>();
			poblacion = pob.inicializa_poblacion(tam_pob, CromosomaF2.class);
			mejor_abs = new CromosomaF2();
			mejor_abs.set_aptitud(Float.MAX_VALUE);
			maximizar = false;
			return;
		}
		else if (problemaActual.equals("F3")) {
			TPoblacion<CromosomaF3> pob = new TPoblacion<CromosomaF3>();
			poblacion = pob.inicializa_poblacion(tam_pob, CromosomaF3.class);
			mejor_abs = new CromosomaF3();
			mejor_abs.set_aptitud(Float.MAX_VALUE);
			maximizar = false;
			return;
		}
		else if (problemaActual.equals("F4")) {
			TPoblacion<CromosomaF4> pob = new TPoblacion<CromosomaF4>();
			poblacion = pob.inicializa_poblacion(tam_pob, CromosomaF4.class);
			mejor_abs = new CromosomaF4();
			mejor_abs.set_aptitud(Float.MAX_VALUE);
			maximizar = false;
			return;
		}
		else if (problemaActual.equals("P2")) {
			TPoblacion<CromosomaP2> pob = new TPoblacion<CromosomaP2>();
			poblacion = pob.inicializa_poblacion(tam_pob, CromosomaP2.class);
			mejor_abs = new CromosomaP2();
			mejor_abs.set_aptitud(Float.MAX_VALUE);
			maximizar = false;
			return;
		}
	}
	
	public ArrayList<ACromosoma> separaMejores(int tamElite)
	{
		
		//Primero ordenamos la seleccion
		Collections.sort(poblacion,new CromosomaComparator());
		//La lista de los elite que devolveremos
		ArrayList<ACromosoma> elite=new ArrayList<ACromosoma>();
		
		//Metemos los tamElite mejores
		for(int i=0;i<tamElite;i++)
		{
			if(!maximizar)
			{
				// Al llegar al elemento lo guardamos en nuestra selección de población
				if (problemaActual.equals("F1"))
					elite.add(new CromosomaF1(poblacion.get(i)));
				else if (problemaActual.equals("F2"))
					elite.add(new CromosomaF2(poblacion.get(i)));
				else if (problemaActual.equals("F3"))
					elite.add(new CromosomaF3(poblacion.get(i)));
				else if (problemaActual.equals("F4"))
					elite.add(new CromosomaF4(poblacion.get(i)));
			}
			else
			{
				// Al llegar al elemento lo guardamos en nuestra selección de población
				if (problemaActual.equals("F1"))
					elite.add(new CromosomaF1(poblacion.get(poblacion.size()-1-i)));
				else if (problemaActual.equals("F2"))
					elite.add(new CromosomaF2(poblacion.get(poblacion.size()-1-i)));
				else if (problemaActual.equals("F3"))
					elite.add(new CromosomaF3(poblacion.get(poblacion.size()-1-i)));
				else if (problemaActual.equals("F4"))
					elite.add(new CromosomaF4(poblacion.get(poblacion.size()-1-i)));
			}
		}
		
		return elite;
	}
	
	public void incluyeElite(ArrayList<ACromosoma> elite)
	{
		
		//Primero ordenamos la seleccion
		Collections.sort(poblacion,new CromosomaComparator());
		//Metemos los tamElite mejores
		
		if(!maximizar)
		{
			for(int i=0;i<elite.size();i++)
			{
				// Al llegar al elemento lo guardamos en nuestra selección de población
				if (problemaActual.equals("F1"))
					poblacion.set(poblacion.size()-1-i, new CromosomaF1(elite.get(i)));
				else if (problemaActual.equals("F2"))
					poblacion.set(poblacion.size()-1-i, new CromosomaF2(elite.get(i)));
				else if (problemaActual.equals("F3"))
					poblacion.set(poblacion.size()-1-i, new CromosomaF3(elite.get(i)));
				else if (problemaActual.equals("F4"))
					poblacion.set(poblacion.size()-1-i, new CromosomaF4(elite.get(i)));
			}
		}
		else
		{
			for(int i=0;i<elite.size();i++)
			{
				// Al llegar al elemento lo guardamos en nuestra selección de población
				if (problemaActual.equals("F1"))
					poblacion.set(i, new CromosomaF1(elite.get(i)));
				else if (problemaActual.equals("F2"))
					poblacion.set(i, new CromosomaF2(elite.get(i)));
				else if (problemaActual.equals("F3"))
					poblacion.set(i, new CromosomaF3(elite.get(i)));
				else if (problemaActual.equals("F4"))
					poblacion.set(i, new CromosomaF4(elite.get(i)));
			}
		}
	}
}
