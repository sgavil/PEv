package es.pe.mdelossgavil.Poblacion;

import java.util.ArrayList;

public abstract class ACromosoma <T> {
	
	//Codificación del cromosoma
	protected ArrayList<TGen> genes=new ArrayList<TGen>();
	
	//La imagen de la codificación
	protected float fenotipo;
	
	//Valor de fitness del individuo
	protected float aptitud;
	
	//Cómo de bueno es el individuo respecto a los demás
	protected float puntuacion;
	
	//Puntuación acumulada
	protected float punt_acum;
	
	protected int longitud;
	
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
	 * @return el valor en decimal de la cadena de booleanos para poder calcular el fenotipo
	 */
	protected int bin2dec()
	{
		/*Creamos el array binario*/
		int ret[]=new int [genes.size()];
		
		for(int i=0;i<genes.size();i++)
		{
			//ret[i]=(Character)genes.get(i).getValor();
		}
		
		/*Juntamos la cadena en un solo numero*/
		int result = 0;
		for(int i = 0; i < ret.length; i++) result += Math.pow(10,i) * ret[ret.length - i - 1];
		
		/*y ahora lo pasamos a decimal*/
		return Integer.parseInt(Integer.toString(result));
	}
	
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
	 * @return the fenotipo
	 */
	public float get_fenotipo() {
		return fenotipo;
	}

	/**
	 * @param fenotipo the fenotipo to set
	 */
	public void set_fenotipo(float fenotipo) {
		this.fenotipo = fenotipo;
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
	public void set_aptitud(float aptitud) {
		this.aptitud = aptitud;
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
	public void set_puntuacion(float puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * @return the punt_acum
	 */
	public float get_punt_acum() {
		return punt_acum;
	}

	/**
	 * @param punt_acum the punt_acum to set
	 */
	public void set_punt_acum(float punt_acum) {
		this.punt_acum = punt_acum;
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

	 
	
	
	

	
}
