package es.pe.mdelossgavil;

import java.util.ArrayList;

import es.pe.mdelossgavil.Cruce.ICruce;
import es.pe.mdelossgavil.Mutacion.IMutacion;
import es.pe.mdelossgavil.Poblacion.*;
import es.pe.mdelossgavil.Seleccion.*;

public class AlgoritmoGenetico {

	public AlgoritmoGenetico(int tam_poblacion,int generaciones) {
		tam_pob = tam_poblacion;
		num_max_gen = generaciones;
		
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
	private float prob_cruce= 0.6f;
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
	 * Asigna las formas de selección,cruce, mutación además del tipo de cromosoma, en función del problema.
	 * Inicializa una población con dicho tipo de cromosoma
	 * 
	 * @param seleccion tipo de seleccion
	 * @param cruce tipo de cruce
	 * @param mutacion tipo de mutacion
	 * @param tipo_cromosoma tipo de cromosoma
	 */
	public void inicializa(ISeleccion seleccion, ICruce cruce,IMutacion mutacion,String tipo_cromosoma) {
		/* Guardamos los operadores */
		metodo_seleccion = seleccion;
		metodo_cruce = cruce;
		metodo_mutacion = mutacion;
		
		//Inicializamos la población
		inicializa_poblacion(tipo_cromosoma);
		
	}
	

	/**
	 * Evalua la población y elige al mejor individuo
	 */
	public void evaluar_poblacion() 
	{
		float punt_acum = 0;
		float aptitud_mejor = 0;
		float suma_aptitud = 0;

		ACromosoma individuoActual;

		/*Primero hacemos un for para tener la suma de las aptitudes y la mejor
		 * aptitud de todas
		 */
		for (int i = 0; i < tam_pob; i++) {
			individuoActual = poblacion.get(i);
			suma_aptitud += individuoActual.get_aptitud();
			
			//Si este individuo es el mejor que hemos encontrado hasta el momento, lo actualizamos
			if (individuoActual.get_aptitud() > aptitud_mejor) {
				pos_mejor = i;
				aptitud_mejor = individuoActual.get_aptitud();
				el_mejor = individuoActual;
			}
		}
		
		for (int i = 0; i < tam_pob; i++)
		{
			individuoActual = poblacion.get(i);
			//Calculamos la puntuación del individuo y su puntuación acumulada
			float puntuacion = individuoActual.get_aptitud() / suma_aptitud;
			individuoActual.set_puntuacion(puntuacion);
			individuoActual.set_punt_acum(puntuacion + punt_acum);
			
			//Actualizamos la puntuación acumulada general
			punt_acum += puntuacion;
		}
		
		if(el_mejor.get_aptitud() > mejor_abs.get_aptitud()) {
			mejor_abs = el_mejor;
		}

	}
	
	public float get_aptitud_media() {
		int total = 0;
		for (int i = 0; i < poblacion.size(); i++) {
			total+=poblacion.get(i).get_aptitud();
		}
		return total / poblacion.size();
	}
	
	/**
	 * Proceso de selección
	 */
	public void seleccion() {
		metodo_seleccion.hacer_seleccion(poblacion);
	}
	
	/**
	 * Proceso de cruce
	 */
	public void cruce() {
		metodo_cruce.reproduccion(poblacion,"F1",prob_cruce);
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
	
	private void inicializa_poblacion(String tipo_cromosoma) {
		if(tipo_cromosoma.equals("F1"))
		{
			TPoblacion<CromosomaF1> pob = new TPoblacion<CromosomaF1>();
			poblacion = pob.inicializa_poblacion(tam_pob, CromosomaF1.class);
			mejor_abs = new CromosomaF1();
			mejor_abs.set_aptitud(0);
			return;
		}
		else {
			//TODO Crear otras inicializaciones de poblacion
		}
	}
}
