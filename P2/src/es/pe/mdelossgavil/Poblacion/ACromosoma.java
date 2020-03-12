package es.pe.mdelossgavil.Poblacion;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class ACromosoma <T> implements Cloneable{
	
	//Codificación del cromosoma
	protected ArrayList<TGen> genes=new ArrayList<TGen>();
	
	//Valor de fitness del individuo
	protected float aptitud;
	
	//Cómo de bueno es el individuo respecto a los demás
	protected float puntuacion;
	
	//Puntuación acumulada
	protected float punt_acum;
	
	protected int longitud;
	
	protected int bin2dec(int comienzo,int lgen) {return 0;};
	
	ArrayList<T> codificacion=new ArrayList<T>();
	
	protected void setCodificacion()
	{
		for(int i=0;i<genes.size();i++)
		{
			for(int j=0;j<genes.get(i).getGenotipo().size();j++)
			{
				codificacion.add((T) genes.get(i).getGenotipo().get(j));
			}
		}
	}
	
	public void actualiza_codificacion() {
		int k = 0;
		for(int i=0;i<genes.size();i++)
		{
			for(int j=0;j<genes.get(i).getGenotipo().size();j++)
			{
				codificacion.set(k, (T)genes.get(i).getGenotipo().get(j));
				k++;
			}
		}
	}
	public ArrayList<T> getCodificacion() {
		return codificacion;
	}

	//Devuelve el valor de fitness
	public abstract float evaluar();
	
	//Inicializa el cromosoma
	public abstract void inicializa_cromosoma();
	
	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //    			Metdos comunes a todos los cromosomas
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	
	
	/**
	 * @return longitud del cromosoma
	 */
	int calcularLongitud(float precision, float xMax, float xMin)
	{
		return (int)Math.log(1+((xMax-xMin)/precision));
	}

	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //    					Getters y Setters 
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	/**
	 * @return the genes
	 */
	public ArrayList<TGen> get_genes() {
		return genes;
	}

	/**
	 * @param genes the genes to set
	 */
	public void set_genes(ArrayList<TGen> genes) {
		this.genes = genes;
	}


	/**
	 * @return the aptitud
	 */
	public float get_aptitud() {
		return aptitud;
	}

	/**
	 * @param aptitud the aptitud to set
	 */
	public void set_aptitud(float aptitud_) {
		aptitud = aptitud_;
	}

	/**
	 * @return the puntuacion
	 */
	public float get_puntuacion() {
		return puntuacion;
	}

	/**
	 * @param puntuacion the puntuacion to set
	 */
	public void set_puntuacion(float puntuacion_) {
		puntuacion = puntuacion_;
	}

	/**
	 * @return the punt_acum
	 */
	public float get_punt_acum() {
		return this.punt_acum;
	}

	/**
	 * @param punt_acum the punt_acum to set
	 */
	public void set_punt_acum(float punt_acum_) {
		punt_acum = punt_acum_;
	}

	/**
	 * @return the longitud
	 */
	public int get_longitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void set_longitud(int longitud) {
		this.longitud = longitud;
	}

	public abstract ACromosoma clone();

	
}
