package es.pe.mdelossgavil.Poblacion;

import java.util.ArrayList;

public abstract class Cromosoma {
	
	//Codificacion del cromosoma
	private ArrayList<Gen> genes;
	
	//La imagen de la codificacion
	private double fenotipo;
	
	//Valor de fitness del individuo
	private double aptitud;
	
	//Como de bueno es el individuo respecto a los demas
	private double puntuacion;
	
	//Puntuacian acumulada
	private double punt_acum;
	private int longitud;
	
	//Devuelve el valor de fitness
	public abstract double evaluar();
	
	//Inicializa el cromosoma
	public abstract void inicializaCromosoma();
	
	
	
}
