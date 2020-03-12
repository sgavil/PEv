package es.pe.mdelossgavil.Poblacion;

import java.text.DecimalFormat;
import java.util.ArrayList;

import es.pe.mdelossgavil.AlgoritmoEvolutivo;
import es.pe.mdelossgavil.Utils;

public class CromosomaP2 extends ACromosoma{
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		// Variables del problema concreto
		// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		static final float Min = 0;
		static final float Max = (float) Math.PI;
		
		static final int N=5;
		
		ArrayList<Float> x;

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
		
		public ArrayList<Float> getFenotipos(){
			evaluar();
			return this.x;
			}
		/**
		 * @return el fenotipo del cromosoma dentro del dominio del problema
		 */
		public float fenotipo(int gen) {
			return (Float) ((TGen) genes.get(gen)).getGenotipo().get(0);
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

		/**
		 * devuelve el fitness del cromosoma que se usara posteriormente para calcular
		 * su piustuacion
		 */
		@Override
		public float evaluar() {
			actualiza_codificacion();
			
			x = new ArrayList<Float>();
			
			//ArrayList<Float> x_=new ArrayList<Float>();
			for(int i=0;i<N;i++)
			{
				x.add(fenotipo(i));
			}
			//this.x=x_;
			return funcion(x);
		}

		
		/**
		 * Inicializa todos los genes con valores aleatorios Al ser el un problema donde
		 * trabajamos con cadenas binarias, usaremos el tipo Bolean
		 */
		@Override
		public void inicializa_cromosoma() {

			/*Longitud de los genes*/
			ArrayList<Integer> longitudGenes=new ArrayList<Integer>();
			
			for(int i=0;i<N;i++)
			{
				longitudGenes.add(1);
				genes.add(new TGen<Float>());
			}
			
			longitud=0;
			for(int i=0;i<longitudGenes.size();i++)longitud+=longitudGenes.get(i);
			
			int j=0;
			int sumatorio=longitudGenes.get(j)-1;
			/* Inicializamos el cromosoma */
			for (int i = 0; i < longitud; i++) {
				while(i>sumatorio)
				{
					sumatorio+=longitudGenes.get(j);
					j++;
				}
				((TGen<Float>) genes.get(j)).getGenotipo().add(Utils.float_between_range(Min, Max));
			}

			setCodificacion();
		}
		@Override
		public ACromosoma clone() {
			// TODO Auto-generated method stub
			return new CromosomaP2(this);
		}

}