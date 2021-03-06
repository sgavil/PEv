package es.pe.mdelossgavil.Poblacion;

import java.lang.Object;
import java.util.ArrayList;

import es.pe.mdelossgavil.AlgoritmoGenetico;

public class CromosomaF2 extends ACromosoma {

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Variables del problema concreto
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	static final float Min = -10.0f;
	static final float Max = 10.0f;

	// La imagen de la codificación

	public CromosomaF2(ACromosoma aCromosoma) {
		this.puntuacion = aCromosoma.puntuacion;
		this.punt_acum = aCromosoma.punt_acum;
		this.codificacion = aCromosoma.codificacion;
		this.longitud = aCromosoma.longitud;
		this.aptitud = aCromosoma.aptitud;

		this.genes = aCromosoma.genes;
	}
	public CromosomaF2() {
		
	}

	/**
	 * @return el fenotipo x1 del cromosoma dentro del dominio del problema
	 */
	public float fenotipo_x1() {
		int genSize = ((TGen<Boolean>) genes.get(0)).getGenotipo().size();
		return (float) (Min + bin2dec(0, (genSize)) * ((Max - Min) / ((Math.pow(2, genSize) - 1))));
	}

	public float fenotipo_x2() {
		int genSize = ((TGen<Boolean>) genes.get(1)).getGenotipo().size();
		return (float) (Min + bin2dec(((TGen<Boolean>) genes.get(0)).getGenotipo().size(),
				((TGen<Boolean>) genes.get(1)).getGenotipo().size()) * ((Max - Min) / ((Math.pow(2, genSize) - 1))));
	}

	/**
	 * @param x1 primer parametro de la funcion	
	 * @param x2 segundo parametro de la funcion
	 * @return la funcion de evaluacion que en este caso = funcion de fitness
	 */
	public float funcion(float x1,float x2) 
	{
		float fact1 = (float)(Math.sin(x1)*Math.cos(x2));
		float fact2 = (float)(Math.exp(Math.abs(1 - Math.sqrt(Math.pow(x1, 2)+Math.pow(x2, 2))/Math.PI)));

		float y = -Math.abs(fact1*fact2);

		return y;
	}

	/**
	 * devuelve el fitness del cromosoma que se usara posteriormente para calcular
	 * su piustuacion
	 */
	@Override
	public float evaluar() {
		actualiza_codificacion();
		
		float x1, x2;
		x1 = fenotipo_x1();
		x2 = fenotipo_x2();
		return funcion(x1, x2);
	}

	/**
	 * @return el valor en decimal de la cadena de booleanos para poder calcular el
	 *         fenotipo
	 */
	@Override
	protected int bin2dec(int comienzo, int lgen) {
		/* Creamos el array binario */

		int ret[] = new int[lgen];

		for (int i = 0; i < lgen; i++) {
			if ((Boolean) getCodificacion().get(i + comienzo))
				ret[i] = 1;
			else
				ret[i] = 0;

		}

		/* Juntamos la cadena en un solo numero */
		int result = 0;
		for (int i = 0; i < ret.length; i++)
			result += Math.pow(10, i) * ret[ret.length - i - 1];

		return Integer.parseInt(Integer.toString(result), 2);
	}

	/**
	 * Inicializa todos los genes con valores aleatorios Al ser el un problema donde
	 * trabajamos con cadenas binarias, usaremos el tipo Bolean
	 */
	@Override
	public void inicializa_cromosoma() {

		/* La longitud del cromosoma sera igual a la longitud de X1 y X2 */
		int longitudX1 = calcularLongitud(AlgoritmoGenetico.tolerancia, Max, Min);
		int longitudX2 = calcularLongitud(AlgoritmoGenetico.tolerancia, Max, Min);

		longitud = longitudX1 + longitudX2;

		genes.add(new TGen<Boolean>());
		genes.add(new TGen<Boolean>());

		/* Inicializamos el cromosoma */
		for (int i = 0; i < longitud; i++) {
			if (i < longitudX1)
				((TGen<Boolean>) genes.get(0)).getGenotipo().add(Math.random() < 0.5);
			else
				((TGen<Boolean>) genes.get(1)).getGenotipo().add(Math.random() < 0.5);
		}

		setCodificacion();
	}
	@Override
	public ACromosoma clone() {
		// TODO Auto-generated method stub
		return new CromosomaF2(this);
	}

}
