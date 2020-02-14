package es.pe.mdelossgavil.Poblacion;

import java.util.ArrayList;

public abstract class Cromosoma {
	
	//Codificacion del cromosoma
	private ArrayList<Gen> genes;
	
	//La imagen de la codificacion
	private float fenotipo;
	
	//Valor de fitness del individuo
	private float aptitud;
	
	//Como de bueno es el individuo respecto a los demas
	private float puntuacion;
	
	//Puntuacian acumulada
	private float punt_acum;
	private int longitud;
	
	//Devuelve el valor de fitness
	public abstract float evaluar();
	
	//Inicializa el cromosoma
	public abstract void inicializaCromosoma();

	/*GETTERS y SETTERS*/
	public float getAptitud() {
		return aptitud;
	}

	public void setAptitud(float aptitud) {
		this.aptitud = aptitud;
	}
	

	
}
