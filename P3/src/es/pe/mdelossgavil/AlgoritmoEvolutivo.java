package es.pe.mdelossgavil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

import es.pe.mdelossgavil.Cruce.CruceArboles;
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

	public static int PROFUNDIDAD_ARBOL = 4;
	public static String TIPO_BLOATING = "";
	public static boolean USE_IF;
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Selección, Cruce y Mutación
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	private ISeleccion metodo_seleccion;
	private CruceArboles metodo_cruce;
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
	public void inicializa(ISeleccion seleccion, CruceArboles cruce, IMutacion mutacion) {
		/* Guardamos los operadores */
		metodo_seleccion = seleccion;
		metodo_cruce = cruce;
		metodo_mutacion = mutacion;

		// Inicializamos la población
		inicializa_poblacion();

	}

	/**
	 * Evalua la población y elige al mejor individuo
	 */
	public void evaluar_poblacion() {

		float punt_acum = 0;
		int aptitud_mejor;

		float suma_aptitud = 0;

		ACromosoma individuoActual;

		aptitud_mejor = Integer.MIN_VALUE;

		///////////////// CONTROL DE BLOATING ////////////

		//En caso de que sea Tarpeian se puede hacer antes de calcular la puntuacion
		if (TIPO_BLOATING.equals("Tarpeian")) {
			bloatingTarpeian();

		} 
		//En caos de usar la bien fundamentada , usamos una apt auxiliar para crear las puntuaciones
		//Ademas,despues de calcular la aptAux de cada uno de los individuos, penalizamos cada uno de ellos
		//con la "Penalizacion bien fundamentada"
		
		if (TIPO_BLOATING.equals("Penalización bien fundamentada")) {		
			for (int i = 0; i < tam_pob; i++) {
				individuoActual = poblacion.get(i);
				
				//Actualizamos la apt auxiliar
				individuoActual.set_aptitudAux(individuoActual.get_aptitud());
				
				//La usamos para el bloating y luego calculamos la probabilidad con ello
				penBienFundamentada();
				
				suma_aptitud += individuoActual.get_aptitudAux();

				if (individuoActual.get_aptitud() > aptitud_mejor) {
					pos_mejor = i;
					aptitud_mejor = (int) individuoActual.get_aptitud();
					el_mejor = individuoActual;
				}

			}

			for (int j = 0; j < tam_pob; j++) {

				// Calculamos la puntuación del individuo y su puntuación acumulada
				float puntuacion;

				puntuacion = poblacion.get(j).get_aptitudAux() / suma_aptitud;

				poblacion.get(j).set_puntuacion(puntuacion);
				poblacion.get(j).set_punt_acum(puntuacion + punt_acum);

				// Actualizamos la puntuación acumulada general
				punt_acum += puntuacion;

			}
		}
		
		/*En caso de que sea Tarpeia, usamos la apt original*/
		else
		{
			for (int i = 0; i < tam_pob; i++) {
				individuoActual = poblacion.get(i);

				suma_aptitud += individuoActual.get_aptitud();

				if (individuoActual.get_aptitud() > aptitud_mejor) {
					pos_mejor = i;
					aptitud_mejor = (int) individuoActual.get_aptitud();
					el_mejor = individuoActual;
				}

			}

			for (int j = 0; j < tam_pob; j++) {

				// Calculamos la puntuación del individuo y su puntuación acumulada
				float puntuacion;

				puntuacion = poblacion.get(j).get_aptitud() / suma_aptitud;

				poblacion.get(j).set_puntuacion(puntuacion);
				poblacion.get(j).set_punt_acum(puntuacion + punt_acum);

				// Actualizamos la puntuación acumulada general
				punt_acum += puntuacion;

			}
			
		}

		if (el_mejor.get_aptitud() > mejor_abs.get_aptitud()) {
			mejor_abs = el_mejor;
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

			hijo1 = new CromosomaArboles();
			hijo2 = new CromosomaArboles();

			hijo1.inicializa_cromosoma();
			hijo2.inicializa_cromosoma();

			ACromosoma padre1 = poblacion.get(seleccionCruce[i]);
			ACromosoma padre2 = poblacion.get(seleccionCruce[i + 1]);

			metodo_cruce.reproduccion((CromosomaArboles) padre1, (CromosomaArboles) padre2, (CromosomaArboles) hijo1,
					(CromosomaArboles) hijo2);

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
		
		poblacion = new ArrayList<ACromosoma>();
		
		//AQUI TENEMOS QUE PONER LO DEL PANEL
		
		//En caso de que sea inicializacion completa
		
		/*
		for (int i = 0; i < tam_pob; i++) {

			CromosomaArboles cArboles = new CromosomaArboles(PROFUNDIDAD_ARBOL, USE_IF);
			cArboles.inicializacionCompleta();
			poblacion.add(cArboles);
			poblacion.get(i).set_aptitud(poblacion.get(i).evaluar());

		}
		*/
		
		//En caso de que sea inicializacion creciente
		
		/*
		for (int i = 0; i < tam_pob; i++) {

			CromosomaArboles cArboles = new CromosomaArboles(PROFUNDIDAD_ARBOL, USE_IF);
			cArboles.inicializacionCreciente();
			poblacion.add(cArboles);
			poblacion.get(i).set_aptitud(poblacion.get(i).evaluar());

		}
		*/


		//En caso de que sea Inicializacion RampedAndHalf
		Random rnd = new Random();
		int tipoInicializacion = rnd.nextInt(3);
		if (tipoInicializacion < 2) {

			for (int i = 0; i < tam_pob; i++) {

				CromosomaArboles cArboles = new CromosomaArboles(PROFUNDIDAD_ARBOL, USE_IF);
				
				if (tipoInicializacion == 0)
					cArboles.inicializacionCompleta();
				else
					cArboles.inicializacionCreciente();
				

				poblacion.add(cArboles);
				poblacion.get(i).set_aptitud(poblacion.get(i).evaluar());

			}

		}
		
		//Inicializacion Ramped and Half
		else {
			int nGrupos = PROFUNDIDAD_ARBOL - 1;
			int individuosPorGrupo = tam_pob / nGrupos;
			int profInicial = 2;

			for (int i = 0; i < nGrupos; i++) {
				for (int j = 0; j < individuosPorGrupo; j++) 
				{
					//La primera mitad de la pob se inicia con InicializacionCompleta mientras que la 
					//segunda mita de la poblacion empieza con InicializacionCreciente
					CromosomaArboles cArboles = new CromosomaArboles(profInicial, USE_IF);
					if (i <= nGrupos / 2)
						cArboles.inicializacionCompleta();
					else
						cArboles.inicializacionCreciente();
					
					cArboles.set_aptitud(cArboles.evaluar());
					poblacion.add(cArboles);
				}
				profInicial++;
			}
			
			//Si al redondear a int hemos perdido algun individio lo recuperamos aqui
			//inicializandolo con InicializacionCompleta
			if(individuosPorGrupo*nGrupos<tam_pob)
			{
				int indivRestantes = tam_pob - (individuosPorGrupo*nGrupos);
				for (int i = 0; i < indivRestantes; i++) {
					CromosomaArboles cArboles = new CromosomaArboles(PROFUNDIDAD_ARBOL, USE_IF);
				
					cArboles.inicializacionCompleta();
					cArboles.set_aptitud(cArboles.evaluar());
					poblacion.add(cArboles);

				}
			}
		}

		mejor_abs = new CromosomaArboles(PROFUNDIDAD_ARBOL, USE_IF);
		mejor_abs.set_aptitud(Integer.MIN_VALUE);	

	}

	public ArrayList<ACromosoma> separaMejores(float porcElitismo) {

		int tamElite = (int) (poblacion.size() * porcElitismo);

		ArrayList<ACromosoma> newPob = new ArrayList<ACromosoma>(poblacion);
		// Primero ordenamos la seleccion
		Collections.sort(newPob, new CromosomaComparator());
		Collections.reverse(newPob);
		// La lista de los elite que devolveremos
		ArrayList<ACromosoma> elite = new ArrayList<ACromosoma>();

		// Metemos los tamElite mejores
		for (int i = 0; i < tamElite; i++) {
			elite.add(new CromosomaArboles(newPob.get(i).clone()));
		}
		return elite;
	}

	public void incluyeElite(ArrayList<ACromosoma> elite) {

		// Primero ordenamos la seleccion
		Collections.sort(poblacion, new CromosomaComparator());
		Collections.reverse(poblacion);
		// Metemos los tamElite mejores

		for (int i = 0; i < elite.size(); i++) {
			// Al llegar al elemento lo guardamos en nuestra selección de población
			poblacion.set(poblacion.size() - 1 - i, new CromosomaArboles(elite.get(i).clone()));
		}

	}

	//Eliminamos aquellos arboles que tengas una profundidad  mayor que el doble de la media de 
	//profundidades de la poblacion
	private void bloatingTarpeian() {
		int profMedia = 0;

		for (int i = 0; i < tam_pob; i++) {
			Arbol raiz = ((CromosomaArboles) poblacion.get(i)).getArbol();
			profMedia += raiz.getAlturaArbol();
		}
		profMedia /= tam_pob;

		for (int i = 0; i < tam_pob; i++) {
			Arbol raiz = ((CromosomaArboles) poblacion.get(i)).getArbol();
			if (raiz.getAlturaArbol() > (profMedia * 2) && (float) Math.random() > 0.5f) {
				poblacion.get(i).set_aptitud(0);
			}

		}
	}

	//Le restamos una penalizacion calculada a cada individuo usando una aptitud auxiliar para que así
	//no modifiquemosla aptitud real de cada individuo
	private void penBienFundamentada() {
		
		float k; // Factor de correccion
		int sumaFitness = 0;
		int sumaTamanyo = 0;

		float mediaFitness = 0;
		float mediaTamanyo = 0;

		for (int i = 0; i < tam_pob; i++) {
			sumaFitness += poblacion.get(i).get_aptitudAux();
			sumaTamanyo = ((CromosomaArboles) (poblacion.get(i))).getArbol().getAlturaArbol();
		}

		mediaFitness = sumaFitness / tam_pob;
		mediaTamanyo = sumaTamanyo / tam_pob;

		float cov = 0.0f;
		float varianza = 0.0f;

		for (int i = 1; i <= tam_pob; i++) {
			int tamIndividuo = ((CromosomaArboles) (poblacion.get(i - 1))).getArbol().getAlturaArbol();

			cov += (tamIndividuo - mediaTamanyo) * (poblacion.get(i - 1).get_aptitudAux() - mediaFitness) / tam_pob;

			varianza += (Math.pow((tamIndividuo - mediaTamanyo), 2)) / tam_pob;
		}

		k = cov / varianza;

		for (int i = 0; i < tam_pob; i++) {
			poblacion.get(i).set_aptitudAux(poblacion.get(i).get_aptitudAux()
					- k * ((CromosomaArboles) (poblacion.get(i))).getArbol().getNumNodos());
		}
		
	}
}
