package es.pe.mdelossgavil.Poblacion;

import java.util.ArrayList;
import java.util.Random;

public class Arbol {

	// Atributos del arbol
	private String valor;
	private ArrayList<Arbol> hijos = new ArrayList<Arbol>();
	private int numHijos;
	private int numNodos;
	private int max_prof;
	private int profundidad;
	private boolean useIF;
	private boolean esHoja;
	private boolean esRaiz;
	private String fenotipo;
	/* //// CONSTRUCTORA //// */

	// Crea una hoja
	public Arbol(String v) {
		valor = v;
		esHoja = true;
	}

	public Arbol(int p, boolean isIf) {
		max_prof = p;
		useIF = isIf;
	}

	/* ///// FUNCIONES ///// */

	// Devuelve el arbol en forma de array
	public ArrayList<String> toArray() {
		ArrayList<String> array = new ArrayList<String>();
		toArrayAux(array, this);
		return array;
	}

	// Insertar un valor en el arbol (nodo simple)
	public Arbol insert(String v, int index) {
		Arbol a = new Arbol(v);
		if (index == -1) {
			hijos.add(a);
			numHijos = hijos.size();
		} else
			hijos.set(index, a);
		return a;
	}

	// Insertar un arbol en otro arbol.
	public void insert(Arbol a, int index) {
		if (index == -1) {
			hijos.add(a);
			numHijos = hijos.size();
		} else
			hijos.set(index, a);
	}

	// Devuelve el arbol en una profundidad concreta
	public Arbol at(int index) {
		return at(this, 0, index);
	}

	private Arbol at(Arbol a, int pos, int index) {
		Arbol s = null;
		if (pos >= index)
			s = a;
		else if (a.getNumHijos() > 0) {
			for (int i = 0; i < a.getNumHijos(); i++)
				if (s == null)
					s = at(a.getHijos().get(i), pos + i + 1, index);
		}
		return s;
	}

	public int inicializacionCompleta(int p, int nodos) {
		int n = nodos;
		int nHijos = 2;
		if (p < max_prof) {
			setProfundidad(p);
			Random rnd = new Random();
			int func = 0;
			if (useIF) {
				func = rnd.nextInt(CromosomaArboles.funciones.length);
			} else
				func = rnd.nextInt(CromosomaArboles.funciones.length - 1);

			this.valor = CromosomaArboles.funciones[func];
			this.setEsRaiz(true);
			if (valor.equals("IF"))
				nHijos = 3;
			if (valor.equals("NOT"))
				nHijos = 1;
			for (int i = 0; i < nHijos; i++) {
				Arbol hijo = new Arbol(max_prof, useIF);
				// hijo.setPadre(this);
				esRaiz = true;
				n++;
				n = hijo.inicializacionCompleta(p + 1, n);
				hijos.add(hijo);
				numHijos++;
			}
		} else {
			setProfundidad(p);
			Random rnd = new Random();
			int terminal;
			this.setEsHoja(true);
			terminal = rnd.nextInt(CromosomaArboles.terminales6.length);
			valor = CromosomaArboles.terminales6[terminal];
			esHoja = true;
			numHijos++;
		}
		setNumNodos(n);
		return n;
	}

	private void toArrayAux(ArrayList<String> array, Arbol a) {
		array.add(a.valor);
		for (int i = 0; i < a.hijos.size(); i++) {
			toArrayAux(array, a.hijos.get(i));
		}
	}

	/*
	 * private int creaHijos(int p, int nodos) { int n = nodos; int nHijos = 2;
	 * 
	 * if(valor.equals("IF")) nHijos = 3; if(valor.equals("NOT")) nHijos = 1;
	 * 
	 * for(int i = 0; i < nHijos; i++){ Arbol hijo = new Arbol(max_prof, useIF);
	 * //hijo.setPadre(this); n++; n = hijo.inicializacionCrecienteAux(p+1, n);
	 * hijos.add(hijo); numHijos++; } return n; }
	 */

	/**
	 * Devuelve los nodos hoja del �rbol
	 * 
	 * @param hijos Hijos del �rbol a analizar
	 * @param nodos Array donde se guardan los terminales
	 */
	public void getTerminales(ArrayList<Arbol> hijos, ArrayList<Arbol> nodos) {
		for (int i = 0; i < hijos.size(); i++) {
			if (hijos.get(i).isEsHoja()) {
				//nodos.add(hijos.get(i).copia());
				nodos.add(hijos.get(i));
			} else {
				getTerminales(hijos.get(i).getHijos(), nodos);
			}
		}
	}

	public int insertTerminal(ArrayList<Arbol> list_hijos, Arbol terminal, int index, int pos) {
		int p = pos;
		for (int i = 0; i < list_hijos.size() && p != -1; i++) {
			if (list_hijos.get(i).isEsHoja() && (p == index)) {
				// terminal.padre = list_hijos.get(i).padre;
				list_hijos.set(i, terminal.copia());
				p = -1;
			} else if (list_hijos.get(i).esHoja && (p != index)) {
				p++;
			} else {
				p = insertTerminal(list_hijos.get(i).hijos, terminal, index, p);
			}
		}
		return p;
	}

	public int insertFuncion(ArrayList<Arbol> list_hijos, Arbol terminal, int index, int pos) {
		int p = pos;
		for (int i = 0; i < list_hijos.size() && p != -1; i++) {
			if (list_hijos.get(i).esRaiz && (p == index)) {
				// terminal.padre = list_hijos.get(i).padre;
				list_hijos.set(i, terminal.copia());
				p = -1;
			} else if (list_hijos.get(i).esRaiz && (p != index)) {
				p++;
				p = insertFuncion(list_hijos.get(i).hijos, terminal, index, p);
			}
		}
		return p;
	}

	/**
	 * Devuelve los nodos internos del �rbol
	 * 
	 * @param hijos Hijos del �rbol a analizar
	 * @param nodos Array donde se guardan las funciones
	 */
	public void getFunciones(ArrayList<Arbol> hijos, ArrayList<Arbol> nodos) {
		for (int i = 0; i < hijos.size(); i++) 
		{
			if (hijos.get(i).isEsRaiz()) {
				//nodos.add(hijos.get(i).copia());
				nodos.add(hijos.get(i));
				getFunciones(hijos.get(i).hijos, nodos);
			}
		}
	}

	public Arbol copia() {
		Arbol copia = new Arbol(this.max_prof, this.useIF);

		copia.setEsHoja(this.esHoja);
		copia.setEsRaiz(this.esRaiz);
		copia.setNHijos(this.numHijos);
		copia.setNumNodos(this.numNodos);
		copia.max_prof = this.max_prof;
		
		copia.setProfundidad(this.profundidad);
		copia.setValor(this.valor);
		ArrayList<Arbol> aux = new ArrayList<Arbol>();
		aux = copiaHijos();
		copia.setHijos(aux);

		return copia;
	}

	private ArrayList<Arbol> copiaHijos() {
		ArrayList<Arbol> array = new ArrayList<Arbol>();

		for (int i = 0; i < this.hijos.size(); i++) {
			array.add(this.hijos.get(i).copia());
		}
		return array;
	}

	public int obtieneNodos(Arbol nodo, int n) {
		if (nodo.esHoja)
			return n;
		if (nodo.valor.equals("IF")) {
			n = obtieneNodos(nodo.hijos.get(0), n + 1);
			n = obtieneNodos(nodo.hijos.get(1), n + 1);
			n = obtieneNodos(nodo.hijos.get(2), n + 1);
		} else if (nodo.valor.equals("AND") || nodo.valor.equals("OR")) {
			n = obtieneNodos(nodo.hijos.get(0), n + 1);
			n = obtieneNodos(nodo.hijos.get(1), n + 1);
		} else {
			n = obtieneNodos(nodo.hijos.get(0), n + 1);
		}
		return n;
	}

	/* //// SETTERS Y GETTERS /// */

	public void setHijos(ArrayList<Arbol> aux) {
		hijos = (ArrayList<Arbol>) aux.clone();
	}

	public void setValor(String valor2) {
		valor = valor2;
		;
	}

	public void setNumNodos(int n) {
		numNodos = n;
	}

	public void setEsHoja(boolean b) {
		esHoja = b;
	}

	public void setProfundidad(int p) {
		profundidad = p;
	}

	public ArrayList<Arbol> getHijos() {
		// TODO Auto-generated method stub
		return hijos;
	}

	public int getNumHijos() {
		// TODO Auto-generated method stub
		return hijos.size();
	}

	public void setEsRaiz(boolean b) {
		esRaiz = b;
	}

	public boolean isEsHoja() {
		return esHoja;
	}

	public boolean isEsRaiz() {
		return esRaiz;
	}

	public String fenotipo() {
		fenotipo = "";
		fenotipo+="(";
		recorreArbol(this);
		fenotipo+=")";
		return fenotipo;
	}

	public String getValor() {
		return valor;
	}
	public void setNHijos(int nHijos) {
		this.numHijos = nHijos;
	}
	private void recorreArbol(Arbol a) {
		fenotipo+=a.valor + " ";	
		if(!a.esHoja)
			fenotipo+="(";
		for (int i = 0; i < a.hijos.size(); i++) {
			profundidad++;
			recorreArbol(a.getHijos().get(i));
		}
		if(!a.esHoja)
			fenotipo+=")";
	}
	public int getNumNodos() {
		return numNodos;
	}
}