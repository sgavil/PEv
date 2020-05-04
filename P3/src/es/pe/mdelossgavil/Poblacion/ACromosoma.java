package es.pe.mdelossgavil.Poblacion;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class ACromosoma <T> implements Cloneable{
	

	
	//Valor de fitness del individuo
	protected float aptitud;
	
	//Aptitud que usaremos unicamente para la seleccion sin modificar asi, la aptitud real de invididuo
	protected float aptitudAux;
	
	//Cómo de bueno es el individuo respecto a los demás
	protected float puntuacion;
	
	//Puntuación acumulada
	protected float punt_acum;
				

	//Devuelve el valor de fitness
	public abstract float evaluar();
	
	//Inicializa el cromosoma
	public abstract void inicializa_cromosoma();
	
	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //    			Metdos comunes a todos los cromosomas
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	


	/**
	 * @return the aptitud
	 */
	public float get_aptitudAux() {
		return aptitudAux;
	}

	/**
	 * @param aptitud the aptitud to set
	 */
	public void set_aptitudAux(float aptitud_) {
		aptitudAux = aptitud_;
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
	public void set_aptitud(float aptitud_) {
		aptitud = aptitud_;
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
	public void set_puntuacion(float puntuacion_) {
		puntuacion = puntuacion_;
	}

	/**
	 * @return the punt_acum
	 */
	public float get_punt_acum() {
		return this.punt_acum;
	}

	/**
	 * @param punt_acum the punt_acum to set
	 */
	public void set_punt_acum(float punt_acum_) {
		punt_acum = punt_acum_;
	}


	public abstract ACromosoma clone();

}
