package es.pe.mdelossgavil.Poblacion;

import java.util.ArrayList;
import java.util.Random;

public class CromosomaArboles extends ACromosoma {

	public static String terminales[];
	public static final String terminales6[] = { "A0", "A1", "D0", "D1", "D2", "D3" };
	public static final String funciones[] = { "AND", "OR", "NOT", "IF" };
	private static int N_ENTRADAS;

	private Arbol arbol;
	private String fenotipo;

	private static ArrayList<ArrayList<Boolean>> tabla = new ArrayList<ArrayList<Boolean>>();

	// Function to print the output
	private static void mostrarArray() {
		for (int i = 0; i < tabla.size(); i++) {
			for (int j = 0; j < tabla.get(i).size(); j++) {
				System.out.print(tabla.get(i).get(j) + " ");
			}
			System.out.println();
		}

	}

	// Function to generate all binary strings
	private static void generarNumerosBinarios(int n, ArrayList<Boolean> arr, int i) {
		if (i == n) {
			tabla.add((ArrayList<Boolean>) arr.clone());
			return;
		}
		arr.set(i, false);
		generarNumerosBinarios(n, arr, i + 1);
		arr.set(i, true);
		generarNumerosBinarios(n, arr, i + 1);
	}

	private static int getIndice(boolean entrada1, boolean entrada2) {
		int total = 0;
		if (entrada1)
			total += 2;
		if (entrada2)
			total += 1;
		return total;
	}

	private static void generarSalida(ArrayList<ArrayList<Boolean>> tabla) {
		for (int i = 0; i < tabla.size(); i++) {
			int indice = getIndice(tabla.get(i).get(0), tabla.get(i).get(1));
			if (tabla.get(i).get(indice + 2))
				tabla.get(i).add(true);
			else
				tabla.get(i).add(false);
		}
	}

	public static void createTable(int n) {
		N_ENTRADAS = n;
		ArrayList<Boolean> arr = new ArrayList<Boolean>(n);
		for (int i = 0; i < n; i++) {
			arr.add(false);
		}
		generarNumerosBinarios(n, arr, 0);
		generarSalida(tabla);
		mostrarArray();

	}

	public CromosomaArboles(int profundidad, int tipoCreacion, boolean useIf, int tipoMultiplexor) {

		arbol = new Arbol(profundidad, useIf);
		arbol.inicializacionCompleta(0, 0);

		/*
		 * switch(tipoCreacion){ case 0: arbol.inicializacionCreciente(0); break; case
		 * 1: arbol.inicializacionCompleta(0,0); break; case 2: int ini = new
		 * Random().nextInt(2); if(ini == 0) arbol.inicializacionCreciente(0); else
		 * arbol.inicializacionCompleta(0,0); break; }
		 */
	}

	public CromosomaArboles(ACromosoma ac) {
		CromosomaArboles ca = (CromosomaArboles) ac;

		arbol = ca.arbol;
		aptitud = ca.aptitud;
		puntuacion = ca.puntuacion;
		punt_acum = ca.punt_acum;
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
		int fitness = 0;
		for (int i = 0; i < tabla.size(); i++) 
		{
			if(evaluaRecursivo(tabla.get(i), arbol) == 
					tabla.get(i).get(tabla.get(i).size()-1))
				fitness++;
			
		}
		return fitness;
		
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

	private int devuelvePosTerminal(Arbol a) {
		if(N_ENTRADAS == 6)
		{
			for (int i = 0; i < terminales6.length; i++) 
			{
				if(terminales6[i].equals(a.getValor()))
					return i;
			}
			return -1;
		}
		else
			return -1;
	}

	private boolean evaluaRecursivo(ArrayList<Boolean>caso,Arbol arbol) {
		boolean evaluacion = false;
		// Si es un Terminal
		if (arbol.isEsHoja()) 
		{
			// Devolvemos su valor que le corresponde en el caso de prueba
			evaluacion = caso.get(devuelvePosTerminal(arbol));
		}
		// Si es una Funcion
		else 
		{
			// Si es la funcion NOT
			if (arbol.getValor().equals("NOT")) {
				// Devolvemos el valor negado que le corresponde al hijo de la
				// izquierda
				evaluacion = !evaluaRecursivo(caso, arbol.getHijos().get(0));
			} else
			// Si es la funcion OR
			if (arbol.getValor().equals("OR")) {
				// Devolvemos la OR de sus dos hijos
				evaluacion = evaluaRecursivo(caso,arbol.getHijos().get(0))
						|| evaluaRecursivo(caso,arbol.getHijos().get(1));
			} else
			// Si es la funcion AND
			if (arbol.getValor().equals("AND")) {
				// Devolvemos la AND de sus dos hijos
				evaluacion = evaluaRecursivo(caso,arbol.getHijos().get(0))
						&& evaluaRecursivo(caso,arbol.getHijos().get(1));
			} else
			// Si es un IF
			if (arbol.getValor().equals("IF")) {
				// Si el primer hijo es true
				if (evaluaRecursivo(caso,arbol.getHijos().get(0))) {

					// Evaluamos el segundo hijo
					evaluacion = evaluaRecursivo(caso,arbol.getHijos().get(1));

				} else {
					// Y si no evaluamos el tercer hijo
					evaluacion = evaluaRecursivo(caso,arbol.getHijos().get(2));

				}

			}
		}
		return evaluacion;
	}
}
