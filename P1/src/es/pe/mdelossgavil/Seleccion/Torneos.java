package es.pe.mdelossgavil.Seleccion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaF1;
import es.pe.mdelossgavil.Poblacion.CromosomaF2;
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
	public ArrayList<ACromosoma> hacer_seleccion(ArrayList<ACromosoma> poblacion, String tipoProblema) {

		ArrayList<ACromosoma> nueva_pob = new ArrayList<ACromosoma>();

		for (int i = 0; i < poblacion.size(); i++) {

			ACromosoma c;

			if (tipoProblema.equals("F1"))
				c = new CromosomaF1();
			else
				c = new CromosomaF2();

			if (maximizar)
				c.set_aptitud(Float.MIN_VALUE);

			else
				c.set_aptitud(Float.MAX_VALUE);

			for (int j = 0; j < k; j++) {
				int rnd = (int) (Math.random() * (poblacion.size() - 1) + 1);
				System.out.println(rnd);
				ACromosoma individuo = poblacion.get(rnd);
				if (maximizar && individuo.get_aptitud() > c.get_aptitud()) {
					c = individuo;
				} else if (!maximizar && individuo.get_aptitud() < c.get_aptitud()) {
					c = individuo;
				}

			}

			if (tipoProblema.equals("F1"))
				nueva_pob.add(new CromosomaF1(c));
			else if (tipoProblema.equals("F2"))
				nueva_pob.add(new CromosomaF2(c));

		}
		return nueva_pob;

	}

}
