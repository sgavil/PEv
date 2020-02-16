package es.pe.mdelossgavil.Poblacion;

public class CromosomaF1 extends ACromosoma{
	
	
	public float fenotipo() {
		return 0.0f;
	}

	public float funcion(double x) {
		// TODO calcular el fitness de la funcion correspondiente
		return 0.0f;
	}
	
	@Override
	public float evaluar() {
		double x;
		x = fenotipo();
		return funcion(x);
	}

	@Override
	public void inicializa_cromosoma() {
		// TODO Auto-generated method stub
		
	}
	
	
}
