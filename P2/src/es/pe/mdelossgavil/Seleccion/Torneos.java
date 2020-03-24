package es.pe.mdelossgavil.Seleccion;

import java.util.ArrayList;
import java.util.Collections;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaHospitales;
import es.pe.mdelossgavil.Seleccion.ISeleccion;

/**
 * Cada elemento de la muestra se toma eligiendo el mejor de los individuos de
 * un conjunto de k elementos(2 ó 3) tomados al azar de la población base.
 */
public class Torneos implements ISeleccion {

	int k;
	boolean maximizar;

	public Torneos(int k, boolean maximizar) {
		this.k = k;
		this.maximizar = maximizar;

	}

	@Override
	public void hacer_seleccion(ArrayList<ACromosoma> poblacion, String tipoProblema) {

		ArrayList<ACromosoma> nueva_pob = new ArrayList<ACromosoma>();

		for (int i = 0; i < poblacion.size(); i++) {

			ACromosoma c =  new CromosomaHospitales();
			
			if (maximizar)
				c.set_aptitud(Float.MIN_VALUE);

			else
				c.set_aptitud(Float.MAX_VALUE);

			for (int j = 0; j < k; j++) {
				int rnd = (int) (Math.random() * (poblacion.size() - 1) + 1);
				ACromosoma individuo = poblacion.get(rnd);
				if (maximizar && individuo.get_aptitud() > c.get_aptitud()) {
					c = individuo;
				} else if (!maximizar && individuo.get_aptitud() < c.get_aptitud()) {
					c = individuo;
				}

			}

			nueva_pob.add(new CromosomaHospitales(c));
		
		}
		Collections.copy(poblacion, nueva_pob);
		
		

	}

}
