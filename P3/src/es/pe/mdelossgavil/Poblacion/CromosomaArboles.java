package es.pe.mdelossgavil.Poblacion;

import java.util.Random;

public class CromosomaArboles extends ACromosoma{
	
	public static String terminales[];
	public static final String terminales6[] = { "A0", "A1", "D0", "D1", "D2", "D3" };
	public static final String funciones[] = { "AND", "OR", "NOT", "IF" };
	private Arbol arbol;
	private double fitness;
	private double fitness_bruto; //Aptitud antes de transformarla
	private double punt;
	private double puntAcum;
	private String fenotipo;
	
	public CromosomaArboles(int profundidad, int tipoCreacion, boolean useIf, int tipoMultiplexor) {
		arbol = new Arbol(profundidad, useIf);
		switch(tipoCreacion){
		case 0:
				arbol.inicializacionCreciente(0);
				break;
		case 1:
				arbol.inicializacionCompleta(0,0);
				break;
		case 2:
			int ini = new Random().nextInt(2);
			if(ini == 0) arbol.inicializacionCreciente(0);
			else arbol.inicializacionCompleta(0,0);
			break;
		}
	}
}
