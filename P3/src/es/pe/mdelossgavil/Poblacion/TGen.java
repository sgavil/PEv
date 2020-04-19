package es.pe.mdelossgavil.Poblacion;

import java.util.ArrayList;
public class TGen<T> {
	
	private ArrayList<T> genotipo = new ArrayList<T>();

	public ArrayList<T> getGenotipo() {
		return genotipo;
	}

	public void setGenotipo(ArrayList<T> genotipo) {
		this.genotipo = genotipo;
	}
	
	public void setAlelo(T valor, int posicion) {
		this.genotipo.set(posicion, valor);
	}
	 

}
