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
	// Atributos de la población
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	// Población de individuos
	private ArrayList<ACromosoma> poblacion;

	// Tamaño de la población
	private int tam_pob;

	// Número máximo de generaciones
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
	public static float prob_cruce = 0.6f;
	public static float prob_mut = 0.05f;

	int[][] flujos;
	int[][] distancias;
	int N;

	float fmax;

	public int nCruces = 0;
	public int nMutaciones = 0;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Selección, Cruce y Mutación
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	private ISeleccion metodo_seleccion;
	private ICruce metodo_cruce;
	private IMutacion metodo_mutacion;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Métodos públicos del algoritmo genético
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	/**
	 * Asigna las formas de selección,cruce, mutación además del tipo de cromosoma,
	 * en función del problema. Inicializa una población con dicho tipo de cromosoma
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

		// Inicializamos la población
		inicializa_poblacion();

	}

	// Leemos los archivos con las matrices de flujos y distancias
	private void lee_datos() {

		try {
			File myObj = new File(fileName);
			Scanner myReader = new Scanner(myObj);

			N = Integer.parseInt(myReader.nextLine().trim());

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
	 * Evalua la población y elige al mejor individuo
	 */
	public void evaluar_poblacion() {

		funcion_revisar_adaptacion_minimiza();

		// tengo el fmax

		float punt_acum = 0;
		float aptitud_mejor;

		float suma_aptitud = 0;

		ACromosoma individuoActual;

		aptitud_mejor = Float.MAX_VALUE;

		/*
		 * Primero hacemos un for para tener la suma de las aptitudes y la mejor aptitud
		 * de todas
		 */
		for (int i = 0; i < tam_pob; i++) {
			individuoActual = poblacion.get(i);

			suma_aptitud += (fmax - individuoActual.get_aptitud());

			if (individuoActual.get_aptitud() < aptitud_mejor) {
				pos_mejor = i;
				aptitud_mejor = individuoActual.get_aptitud();
				el_mejor = individuoActual;
			}

		}

		for (int j = 0; j < tam_pob; j++) {

			// Calculamos la puntuación del individuo y su puntuación acumulada
			float puntuacion;

			puntuacion = (fmax - poblacion.get(j).get_aptitud()) / suma_aptitud;

			poblacion.get(j).set_puntuacion(puntuacion);
			poblacion.get(j).set_punt_acum(puntuacion + punt_acum);

			// Actualizamos la puntuación acumulada general
			punt_acum += puntuacion;

		}

		if (maximizar) {
			if (el_mejor.get_aptitud() > mejor_abs.get_aptitud()) {
				mejor_abs = el_mejor;
			}
		} else {

			if (el_mejor.get_aptitud() < mejor_abs.get_aptitud()) {
				mejor_abs = el_mejor;
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
	 * Proceso de selección
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
				nCruces++;
			}
		}

		// El numero de seleccionados se hace par
		if (num_sele_cruce % 2 == 1)
			num_sele_cruce--;

		for (int i = 0; i < num_sele_cruce; i += 2) {

			hijo1 = new CromosomaHospitales();
			hijo2 = new CromosomaHospitales();

			hijo1.inicializa_cromosoma();
			hijo2.inicializa_cromosoma();

			for (int j = 0; j < hijo1.getCodificacion().size(); j++) {
				hijo1.getCodificacion().set(j, 100000);
				hijo2.getCodificacion().set(j, 100000);
			}

			ACromosoma padre1 = poblacion.get(seleccionCruce[i]);
			ACromosoma padre2 = poblacion.get(seleccionCruce[i + 1]);

			metodo_cruce.reproduccion(padre1, padre2, hijo1, hijo2);

			poblacion.set(seleccionCruce[i], hijo1.clone());
			poblacion.set(seleccionCruce[i + 1], hijo2.clone());

		}
	}

	/**
	 * Proceso de mutación
	 */
	public void mutacion() {
		// Recorremos la poblacion
		for (int i = 0; i < poblacion.size(); i++) {
			float rndProb = (float) Math.random();
			if (rndProb < prob_mut) {
				// Mutamos el individuo
				metodo_mutacion.mutar(poblacion.get(i));
				// Lo evaluamos con la nueva codificacion
				poblacion.get(i).set_aptitud(poblacion.get(i).evaluar());
				nMutaciones++;
			}
		}

	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Métodos privados del algoritmo genético
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	private void inicializa_poblacion() {

		CromosomaHospitales.flujos = this.flujos;
		CromosomaHospitales.distancias = this.distancias;
		CromosomaHospitales.N = this.N;

		TPoblacion<CromosomaHospitales> pob = new TPoblacion<CromosomaHospitales>();
		poblacion = pob.inicializa_poblacion(tam_pob, CromosomaHospitales.class);
		mejor_abs = new CromosomaHospitales();
		mejor_abs.inicializa_cromosoma();
		mejor_abs.set_aptitud(Float.MAX_VALUE);
		maximizar = false;

	}

	public ArrayList<ACromosoma> separaMejores(float porcElitismo) {

		int tamElite = (int) (poblacion.size() * porcElitismo);

		// Primero ordenamos la seleccion
		Collections.sort(poblacion, new CromosomaComparator());
		// La lista de los elite que devolveremos
		ArrayList<ACromosoma> elite = new ArrayList<ACromosoma>();

		// Metemos los tamElite mejores
		for (int i = 0; i < tamElite; i++) {
			elite.add(new CromosomaHospitales(poblacion.get(i)));
		}
		return elite;
	}

	public void incluyeElite(ArrayList<ACromosoma> elite) {

		// Primero ordenamos la seleccion
		Collections.sort(poblacion, new CromosomaComparator());
		// Metemos los tamElite mejores

		for (int i = 0; i < elite.size(); i++) {
			// Al llegar al elemento lo guardamos en nuestra selección de población
			poblacion.set(poblacion.size() - 1 - i, new CromosomaHospitales(elite.get(i)));

		}

	}

	public void funcion_revisar_adaptacion_minimiza() {
		fmax = Float.MIN_VALUE;

		// un valor por debajo de cualquiera que pueda
		// tomar la función objetivo
		for (int i = 0; i < poblacion.size(); i++) {
			if (poblacion.get(i).get_aptitud() > fmax) {
				fmax = poblacion.get(i).get_aptitud();
			}
		}

		fmax *= 1.05;

	}

}
