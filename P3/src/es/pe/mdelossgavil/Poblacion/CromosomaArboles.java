package es.pe.mdelossgavil.Poblacion;

import java.util.ArrayList;
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
	
	private static ArrayList<ArrayList<Integer>> tabla= new ArrayList<ArrayList<Integer>>();

	// Function to print the output 
	private static void mostrarArray()
	{ 
	    for (int i = 0; i < tabla.size(); i++)  
	    { 
	        for (int j = 0; j < tabla.get(i).size(); j++) {
				System.out.print(tabla.get(i).get(j) + " ");
			}
	        System.out.println(); 
	    } 
	 
	} 
	  
	// Function to generate all binary strings 
	private static void generarNumerosBinarios(int n, ArrayList<Integer> arr, int i) 
	{ 
	    if (i == n)  
	    { 
	       tabla.add((ArrayList<Integer>) arr.clone());
	       return; 
	    } 
	    arr.set(i, 0); 
	    generarNumerosBinarios(n, arr, i + 1);
	    arr.set(i, 1); 
	    generarNumerosBinarios(n, arr, i + 1); 
	} 
	
	private static int getIndice(int entrada1, int entrada2)
	{
		int total=0;
		if(entrada1==1)total+=2;
		if(entrada2==1)total+=1;
		return total;
	}
	
	private static void generarSalida(ArrayList<ArrayList<Integer>> tabla)
	{
		for(int i=0;i<tabla.size();i++)
		{
			int indice=getIndice(tabla.get(i).get(0),tabla.get(i).get(1));
			if(tabla.get(i).get(indice+2)==1)tabla.get(i).add(1);
			else tabla.get(i).add(0);
		}
	}
	
	public static void createTable(int n)
	{
		ArrayList<Integer> arr=new ArrayList<Integer>(n);
		for (int i = 0; i < n; i++) {
			arr.add(0);
		}
		generarNumerosBinarios(n, arr, 0);
		generarSalida(tabla);
		mostrarArray();
		
	}
	
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
