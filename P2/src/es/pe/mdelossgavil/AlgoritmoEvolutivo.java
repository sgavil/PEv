package es.pe.mdelossgavil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import es.pe.mdelossgavil.Cruce.ICruce;
import es.pe.mdelossgavil.Mutacion.IMutacion;
import es.pe.mdelossgavil.Poblacion.*;
import es.pe.mdelossgavil.Seleccion.*;

public class AlgoritmoEvolutivo {

	public AlgoritmoEvolutivo(int tam_poblacion, int generaciones, String fileName) {
		tam_pob = tam_poblacion;
		num_max_gen = generaciones;
		this.fileName = fileName;

	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Atributos de la poblaci�n
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	// Poblaci�n de individuos
	private ArrayList<ACromosoma> poblacion;

	// Tama�o de la poblaci�n
	private int tam_pob;

	// N�mero m�ximo de generaciones
	private int num_max_gen;

	public boolean maximizar;

	// Indicador del problema actual
	String problemaActual = "";

	// Mejor individuo
	private ACromosoma el_mejor;

	public ACromosoma mejor_abs;

	private String fileName;

	public ACromosoma getEl_mejor() {
		return el_mejor;
	}

	public void setEl_mejor(ACromosoma el_mejor) {
		this.el_mejor = el_mejor;
	}

	private int pos_mejor;
	public static float prob_cruce;
	public static float prob_mut;
	public static float tolerancia;

	int[][] flujos;
	int[][] distancias;
	int N;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Selecci�n, Cruce y Mutaci�n
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	private ISeleccion metodo_seleccion;
	private ICruce metodo_cruce;
	private IMutacion metodo_mutacion;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// M�todos p�blicos del algoritmo gen�tico
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	/**
	 * Asigna las formas de selecci�n,cruce, mutaci�n adem�s del tipo de cromosoma,
	 * en funci�n del problema. Inicializa una poblaci�n con dicho tipo de cromosoma
	 * 
	 * @param seleccion      tipo de seleccion
	 * @param cruce          tipo de cruce
	 * @param mutacion       tipo de mutacion
	 * @param tipo_cromosoma tipo de cromosoma
	 */
	public void inicializa(ISeleccion seleccion, ICruce cruce, IMutacion mutacion) {
		/* Guardamos los operadores */
		metodo_seleccion = seleccion;
		metodo_cruce = cruce;
		metodo_mutacion = mutacion;

		lee_datos();

		// Inicializamos la poblaci�n
		inicializa_poblacion();

	}

	// Leemos los archivos con las matrices de flujos y distancias
	private void lee_datos() {

		try {
			File myObj = new File(fileName);
			Scanner myReader = new Scanner(myObj);

			N = Integer.parseInt(myReader.nextLine());

			flujos = new int[N][N];
			distancias = new int[N][N];

			myReader.nextLine();

			while (myReader.hasNextLine()) {
				// Guardamos la matriz de distancias
				for (int i = 0; i < N; i++) {
					String[] line = myReader.nextLine().trim().split(" ");
					for (int j = 0; j < line.length; j++) {
						distancias[i][j] = Integer.parseInt(line[j]);
					}
				}

				myReader.nextLine();

				for (int i = 0; i < N; i++) {
					String[] line = myReader.nextLine().trim().split(" ");
					for (int j = 0; j < line.length; j++) {
						flujos[i][j] = Integer.parseInt(line[j]);
					}
				}
			}

			myReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("File " + fileName + " not found.");
			e.printStackTrace();
		}

	}

	/**
	 * Evalua la poblaci�n y elige al mejor individuo
	 */
	public void evaluar_poblacion() {

		
		
		float punt_acum = 0;
		float aptitud_mejor;

		float suma_aptitud = 0;

		ACromosoma individuoActual;

		if (maximizar)
			aptitud_mejor = Float.MIN_VALUE;
		else
			aptitud_mejor = Float.MAX_VALUE;

		/*
		 * Primero hacemos un for para tener la suma de las aptitudes y la mejor aptitud
		 * de todas
		 */
		for (int i = 0; i < tam_pob; i++) {
			individuoActual = poblacion.get(i);
			
			suma_aptitud += individuoActual.get_aptitud();
		
			if (maximizar) {
				if (individuoActual.get_aptitud() > aptitud_mejor) {
					pos_mejor = i;
					aptitud_mejor = individuoActual.get_aptitud();
					el_mejor = individuoActual;
				}

			} else {
				if (individuoActual.get_aptitud() < aptitud_mejor) {
					pos_mejor = i;
					aptitud_mejor = individuoActual.get_aptitud();
					el_mejor = individuoActual;
				}
			}

		}

		for (int j = 0; j < tam_pob; j++) {
				
			
			
			// Calculamos la puntuaci�n del individuo y su puntuaci�n acumulada
			float puntuacion;
		
			puntuacion = poblacion.get(j).get_aptitud() / suma_aptitud;

			poblacion.get(j).set_puntuacion( puntuacion);
			poblacion.get(j).set_punt_acum(puntuacion + punt_acum);

			// Actualizamos la puntuaci�n acumulada general
			punt_acum += puntuacion;

		}

		if (maximizar) {
			if (el_mejor.get_aptitud() > mejor_abs.get_aptitud()) {
				mejor_abs = el_mejor;
			}
		} else {

			if (el_mejor.get_aptitud() < mejor_abs.get_aptitud()) {
				mejor_abs = el_mejor;
				System.out.println(mejor_abs.get_aptitud());
			}
		}

	}

	public float get_aptitud_media() {
		float total = 0;
		for (int i = 0; i < poblacion.size(); i++) {
			total += poblacion.get(i).get_aptitud();
		}
		return total / poblacion.size();
	}

	/**
	 * Proceso de selecci�n
	 */
	public void seleccion() {
		metodo_seleccion.hacer_seleccion(poblacion, problemaActual);
	}

	/**
	 * Proceso de cruce
	 */
	public void cruce() {

		int tam_pob = poblacion.size();
		int seleccionCruce[] = new int[tam_pob];

		// Contador seleccionados
		int num_sele_cruce = 0;
		int punto_cruce;
		float prob;

		ACromosoma hijo1 = null, hijo2 = null;

		for (int i = 0; i < tam_pob; i++) {
			// Creamos una prob aleatoria entre [0,1)
			prob = (float) Math.random();

			// En caso de que sea menos a la prob de Cruce
			// se escoge a ese invididuo para cruzarse
			if (prob < prob_cruce) {
				seleccionCruce[num_sele_cruce] = i;
				num_sele_cruce++;
			}
		}

		// El numero de seleccionados se hace par
		if (num_sele_cruce % 2 == 1)
			num_sele_cruce--;

		for (int i = 0; i < num_sele_cruce; i += 2) {

			/* Cogemos el tipo de Cromosoma del problema */
			/*
			 * if (problemaActual == "F1") { hijo1 = new CromosomaF1(); hijo2 = new
			 * CromosomaF1(); } else if (problemaActual == "F2") { hijo1 = new
			 * CromosomaF2(); hijo2 = new CromosomaF2(); } else if (problemaActual == "F3")
			 * { hijo1 = new CromosomaF3(); hijo2 = new CromosomaF3(); } else if
			 * (problemaActual == "F4") { hijo1 = new CromosomaF4(); hijo2 = new
			 * CromosomaF4(); } else if (problemaActual == "P2") { hijo1 = new
			 * CromosomaP2(); hijo2 = new CromosomaP2(); }
			 */

			hijo1 = new CromosomaHospitales();
			hijo2 = new CromosomaHospitales();

			hijo1.inicializa_cromosoma();
			hijo2.inicializa_cromosoma();

			ACromosoma padre1 = poblacion.get(seleccionCruce[i]);
			ACromosoma padre2 = poblacion.get(seleccionCruce[i + 1]);

			metodo_cruce.reproduccion(padre1, padre2, hijo1, hijo2);

			poblacion.set(seleccionCruce[i], hijo1.clone());
			poblacion.set(seleccionCruce[i + 1], hijo2.clone());

		}
	}

	/**
	 * Proceso de mutaci�n
	 */
	public void mutacion() {
		metodo_mutacion.mutar(poblacion, prob_mut);
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// M�todos privados del algoritmo gen�tico
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	private void inicializa_poblacion() {

		CromosomaHospitales.flujos = this.flujos;
		CromosomaHospitales.distancias = this.distancias;
		CromosomaHospitales.N = this.N;

		TPoblacion<CromosomaHospitales> pob = new TPoblacion<CromosomaHospitales>();
		poblacion = pob.inicializa_poblacion(tam_pob, CromosomaHospitales.class);
		mejor_abs = new CromosomaHospitales();
		mejor_abs.inicializa_cromosoma();
		mejor_abs.set_aptitud(Float.MIN_VALUE);
		maximizar = true;

	}

	public ArrayList<ACromosoma> separaMejores(float porcElitismo) {

		int tamElite = (int) (poblacion.size() * porcElitismo);

		// Primero ordenamos la seleccion
		Collections.sort(poblacion, new CromosomaComparator());
		// La lista de los elite que devolveremos
		ArrayList<ACromosoma> elite = new ArrayList<ACromosoma>();

		// Metemos los tamElite mejores
		for (int i = 0; i < tamElite; i++) {
			if (!maximizar) {
				// Al llegar al elemento lo guardamos en nuestra selecci�n de poblaci�n
				if (problemaActual.equals("F1"))
					elite.add(new CromosomaF1(poblacion.get(i)));
				else if (problemaActual.equals("F2"))
					elite.add(new CromosomaF2(poblacion.get(i)));
				else if (problemaActual.equals("F3"))
					elite.add(new CromosomaF3(poblacion.get(i)));
				else if (problemaActual.equals("F4"))
					elite.add(new CromosomaF4(poblacion.get(i)));
				else if (problemaActual.equals("P2"))
					elite.add(new CromosomaP2(poblacion.get(i)));
			} else {
				// Al llegar al elemento lo guardamos en nuestra selecci�n de poblaci�n
				if (problemaActual.equals("F1"))
					elite.add(new CromosomaF1(poblacion.get(poblacion.size() - 1 - i)));
				else if (problemaActual.equals("F2"))
					elite.add(new CromosomaF2(poblacion.get(poblacion.size() - 1 - i)));
				else if (problemaActual.equals("F3"))
					elite.add(new CromosomaF3(poblacion.get(poblacion.size() - 1 - i)));
				else if (problemaActual.equals("F4"))
					elite.add(new CromosomaF4(poblacion.get(poblacion.size() - 1 - i)));
				else if (problemaActual.equals("P2"))
					elite.add(new CromosomaP2(poblacion.get(poblacion.size() - 1 - i)));
			}
		}

		return elite;
	}

	public void incluyeElite(ArrayList<ACromosoma> elite) {

		// Primero ordenamos la seleccion
		Collections.sort(poblacion, new CromosomaComparator());
		// Metemos los tamElite mejores

		if (!maximizar) {
			for (int i = 0; i < elite.size(); i++) {
				// Al llegar al elemento lo guardamos en nuestra selecci�n de poblaci�n
				if (problemaActual.equals("F1"))
					poblacion.set(poblacion.size() - 1 - i, new CromosomaF1(elite.get(i)));
				else if (problemaActual.equals("F2"))
					poblacion.set(poblacion.size() - 1 - i, new CromosomaF2(elite.get(i)));
				else if (problemaActual.equals("F3"))
					poblacion.set(poblacion.size() - 1 - i, new CromosomaF3(elite.get(i)));
				else if (problemaActual.equals("F4"))
					poblacion.set(poblacion.size() - 1 - i, new CromosomaF4(elite.get(i)));
				else if (problemaActual.equals("P2"))
					poblacion.set(poblacion.size() - 1 - i, new CromosomaP2(elite.get(i)));
			}
		} else {
			for (int i = 0; i < elite.size(); i++) {
				// Al llegar al elemento lo guardamos en nuestra selecci�n de poblaci�n
				if (problemaActual.equals("F1"))
					poblacion.set(i, new CromosomaF1(elite.get(i)));
				else if (problemaActual.equals("F2"))
					poblacion.set(i, new CromosomaF2(elite.get(i)));
				else if (problemaActual.equals("F3"))
					poblacion.set(i, new CromosomaF3(elite.get(i)));
				else if (problemaActual.equals("F4"))
					poblacion.set(i, new CromosomaF4(elite.get(i)));
				else if (problemaActual.equals("P2"))
					poblacion.set(i, new CromosomaP2(elite.get(i)));
			}
		}
	}

	public void funcion_revisar_adaptacion_minimiza() {
		float fmax = Float.MIN_VALUE;

		// un valor por debajo de cualquiera que pueda
		// tomar la funci�n objetivo
		for (int i = 0; i < poblacion.size(); i++) {
			if (poblacion.get(i).get_aptitud() > fmax) {
				fmax = poblacion.get(i).get_aptitud();
			}
		}

		fmax *= 1.05;

		for (int j = 0; j < poblacion.size(); j++) {
			poblacion.get(j).set_aptitud(fmax - poblacion.get(j).get_aptitud());
		}
	}

}
