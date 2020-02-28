package es.pe.mdelossgavil.Seleccion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaF1;
import es.pe.mdelossgavil.Poblacion.CromosomaF2;
import es.pe.mdelossgavil.Poblacion.CromosomaF3;
import es.pe.mdelossgavil.Poblacion.CromosomaF4;
import es.pe.mdelossgavil.Poblacion.CromosomaP2;
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
			else if (tipoProblema.equals("F2"))
				c = new CromosomaF2();
			else if (tipoProblema.equals("F3"))
				c =  new CromosomaF3();
			else if (tipoProblema.equals("F4"))
				c = new CromosomaF4();
			else if (tipoProblema.equals("P2"))
				c = new CromosomaP2();
			else c = null;
			
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

			if (tipoProblema.equals("F1"))
				nueva_pob.add(new CromosomaF1(c));
			else if (tipoProblema.equals("F2"))
				nueva_pob.add(new CromosomaF2(c));
			else if (tipoProblema.equals("F3"))
				nueva_pob.add(new CromosomaF3(c));
			else if (tipoProblema.equals("F4"))
				nueva_pob.add(new CromosomaF4(c));
			else if (tipoProblema.equals("P2"))
				nueva_pob.add(new CromosomaP2(c));
			
			

		}
		return nueva_pob;

	}

}
