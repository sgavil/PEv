package es.pe.mdelossgavil.Poblacion;

public class CromosomaF1 extends ACromosoma{
	
	
	public float fenotipo() {
		return 0.0f;
	}

	
	public float funcion(float x1,float x2) 
	{
		return (float) (21.5f + x1 * Math.sin(4*Math.PI*x1)+ x2 * Math.sin(20 * Math.PI*x2));
	}
	
	@Override
	public float evaluar() {
		float x1,x2;
		x1 = fenotipo();
		x2 = fenotipo();
		
		return funcion(x1,x2);
	}

	@Override
	public void inicializa_cromosoma() {
		// TODO Auto-generated method stub
		
	}
	
	
}
