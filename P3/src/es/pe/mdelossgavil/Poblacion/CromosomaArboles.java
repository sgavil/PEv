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
		arbol.inicializacionCompleta(0,0);
		
		/*switch(tipoCreacion){
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
		}*/
	}

	public CromosomaArboles(ACromosoma ac) {
		CromosomaArboles ca = (CromosomaArboles)ac;
	
		arbol = ca.arbol;
		fitness = ca.fitness;
		fitness_bruto = ca.fitness_bruto; //Aptitud antes de transformarla
		punt = ca.punt;
		puntAcum = ca.puntAcum;
		fenotipo = ca.fenotipo;
	}

	
	public CromosomaArboles() {
		// TODO Auto-generated constructor stub
	}

	public Arbol getArbol() {
		// TODO Auto-generated method stub
		return arbol;
	}

	@Override
	public float evaluar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void inicializa_cromosoma() {
		
		
	}

	@Override
	public ACromosoma clone() {
		// TODO Auto-generated method stub
		return new CromosomaArboles(this);
	}

	public CromosomaArboles copia() {
		return new CromosomaArboles(this);
		
	}

	public String get_fenotipo() {
		// TODO Auto-generated method stub
		return arbol.fenotipo();
	}
}
