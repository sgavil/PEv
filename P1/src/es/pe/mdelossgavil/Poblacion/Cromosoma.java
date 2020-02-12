package es.pe.mdelossgavil.Poblacion;

import java.util.ArrayList;

public abstract class Cromosoma {
	private ArrayList<Gen> genes;
	private double fenotipo;
	private double aptitud;
	private double puntuacion;
	private double punt_acum;
	private int longitud;
	
	public abstract double evaluar();
	public abstract void inicializaCromosoma();
	
	
	
}
