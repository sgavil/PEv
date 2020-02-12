package es.pe.mdelossgavil.Poblacion;

public class CromosomaF1 extends Cromosoma{
	
	
	public double fenotipo() {
		return 0.0;
	}

	public double funcion(double x) {
		// TODO calcular el fitness de la funcion correspondiente
		return 0.0;
	}
	
	@Override
	public double evaluar() {
		double x;
		x = fenotipo();
		return funcion(x);
	}

	@Override
	public void inicializaCromosoma() {
		// TODO Auto-generated method stub
		
	}
	
	
}
