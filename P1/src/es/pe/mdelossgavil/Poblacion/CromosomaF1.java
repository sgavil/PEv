package es.pe.mdelossgavil.Poblacion;

public class CromosomaF1 extends ACromosoma{
	
	
	public float fenotipo() {
		return 0.0f;
	}

	
	/**
	 * @param x1 primer parametro de la funcion	
	 * @param x2 segundo parametro de la funcion
	 * @return la funcion de evaluacion que en este caso = funcion de fitness
	 */
	public float funcion(float x1,float x2) 
	{
		return (float) (21.5f + x1 * Math.sin(4*Math.PI*x1)+ x2 * Math.sin(20 * Math.PI*x2));
	}
	
	
	/**
	 * devuelve el fitness del cromosoma que se usara posteriormente 
	 * para calcular su piustuacion
	 */
	@Override
	public float evaluar() {
		float x1,x2;
		x1 = fenotipo();
		x2 = fenotipo();
		return funcion(x1,x2);
	}

	/**
	 *Inicializa todos los genes con valores aleatorios
	 *Al ser el un problema donde trabajamos con cadenas binarias,
	 *usaremos el tipo Bolean
	 */
	@Override
	public void inicializa_cromosoma() {
		for(int i=0;i<longitud;i++)
		{
			genes.add(new TGen<Boolean>());
			genes.get(i).setValor(Math.random() < 0.5);
		}
	}	

}
