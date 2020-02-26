package es.pe.mdelossgavil.Poblacion;

import java.text.DecimalFormat;
import java.util.ArrayList;

import es.pe.mdelossgavil.AlgoritmoGenetico;
import es.pe.mdelossgavil.Utils;

public class CromosomaP2 extends ACromosoma{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		// Variables del problema concreto
		// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		static final float Min = 0;
		static final float Max = (float) Math.PI;
		
		int n=3;

		// La imagen de la codificación

		public CromosomaP2(ACromosoma aCromosoma) {
			this.puntuacion = aCromosoma.puntuacion;
			this.punt_acum = aCromosoma.punt_acum;
			this.codificacion = aCromosoma.codificacion;
			this.longitud = aCromosoma.longitud;
			this.aptitud = aCromosoma.aptitud;
			this.genes = aCromosoma.genes;
		}
		public CromosomaP2() {
			
		}

		/**
		 * @return el fenotipo x1 del cromosoma dentro del dominio del problema
		 */
		public float fenotipo_x1() {
			//int genSize = ((TGen<Boolean>) genes.get(0)).getGenotipo().size();
			return ((TGen<Float>) genes.get(0)).getGenotipo().get(0);
		}

		public float fenotipo_x2() {
			//int genSize = ((TGen<Boolean>) genes.get(1)).getGenotipo().size();
			return ((TGen<Float>) genes.get(1)).getGenotipo().get(0);
		}

		/**
		 * @param x1 primer parametro de la funcion	
		 * @param x2 segundo parametro de la funcion
		 * @return la funcion de evaluacion que en este caso = funcion de fitness
		 */
		public float funcion(ArrayList<Float> x)
		{
			float result=0;
			
			for(int i=1;i<=x.size();i++)
			{
				float operador1=(float) Math.sin(x.get(i-1));
				float inviduoSeno=(float) (((i+1)*Math.pow(x.get(i-1), 2))/Math.PI);
				float operador2=(float)Math.pow(Math.sin(inviduoSeno),20);
				result+=(operador1*operador2);
			}
			result*=-1;	
			return result;	
		}
		
		public float power(final float base, final int power) {
		    float result = 1;
		    for( int i = 0; i < power; i++ ) {
		        result *= base;
		    }
		    return result;
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
			ArrayList<Float> x=new ArrayList<Float>();
			x.add(x1);
			x.add(x2);
			return funcion(x);
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
			int longitudX1 = 1;
			int longitudX2 = 1;

			longitud = longitudX1 + longitudX2;

			genes.add(new TGen<Float>());
			genes.add(new TGen<Float>());

			String tol = Float.toString(AlgoritmoGenetico.tolerancia);

			
			/* Inicializamos el cromosoma */
			for (int i = 0; i < longitud; i++) {
				//DecimalFormat formatter = new DecimalFormat(AlgoritmoGenetico.tolerancia);  // edited here.
				
				if (i < longitudX1) {
					
					    
				/*	double randomValue = min + Math.random( ) * diff;
					double tempRes = Math.floor(randomValue * 10);
					double finalRes = tempRes/10;
					System.out.println(formatter.format(finalRes));
					*/
					((TGen<Float>) genes.get(0)).getGenotipo().add(Utils.float_between_range(Min, Max));

				}
				else {
					((TGen<Float>) genes.get(1)).getGenotipo().add(Utils.float_between_range(Min, Max));

				}
			}

			setCodificacion();
		}

}
