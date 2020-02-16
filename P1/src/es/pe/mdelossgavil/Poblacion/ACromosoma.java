package es.pe.mdelossgavil.Poblacion;

import java.util.ArrayList;

public abstract class ACromosoma {
	
	//Codificación del cromosoma
	private ArrayList<TGen> genes;
	
	//La imagen de la codificación
	private float fenotipo;
	
	//Valor de fitness del individuo
	private float aptitud;
	
	//Cómo de bueno es el individuo respecto a los demás
	private float puntuacion;
	
	//Puntuación acumulada
	private float punt_acum;
	
	private int longitud;
	
	//Devuelve el valor de fitness
	public abstract float evaluar();
	
	//Inicializa el cromosoma
	public abstract void inicializa_cromosoma();

	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //    					Getters y Setters 
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	/**
	 * @return the genes
	 */
	public ArrayList<TGen> get_genes() {
		return genes;
	}

	/**
	 * @param genes the genes to set
	 */
	public void set_genes(ArrayList<TGen> genes) {
		this.genes = genes;
	}

	/**
	 * @return the fenotipo
	 */
	public float get_fenotipo() {
		return fenotipo;
	}

	/**
	 * @param fenotipo the fenotipo to set
	 */
	public void set_fenotipo(float fenotipo) {
		this.fenotipo = fenotipo;
	}

	/**
	 * @return the aptitud
	 */
	public float get_aptitud() {
		return aptitud;
	}

	/**
	 * @param aptitud the aptitud to set
	 */
	public void set_aptitud(float aptitud) {
		this.aptitud = aptitud;
	}

	/**
	 * @return the puntuacion
	 */
	public float get_puntuacion() {
		return puntuacion;
	}

	/**
	 * @param puntuacion the puntuacion to set
	 */
	public void set_puntuacion(float puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * @return the punt_acum
	 */
	public float get_punt_acum() {
		return punt_acum;
	}

	/**
	 * @param punt_acum the punt_acum to set
	 */
	public void set_punt_acum(float punt_acum) {
		this.punt_acum = punt_acum;
	}

	/**
	 * @return the longitud
	 */
	public int get_longitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void set_longitud(int longitud) {
		this.longitud = longitud;
	}

	 
	
	
	

	
}
