package es.pe.mdelossgavil.Seleccion;

import java.util.ArrayList;
import java.util.Collections;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaComparator;
import es.pe.mdelossgavil.Poblacion.CromosomaHospitales;
import es.pe.mdelossgavil.Seleccion.ISeleccion;

public class Truncamiento implements ISeleccion {

	int proporcion;
	int elementos;
	int vueltas;

	public Truncamiento(int p) {

		this.proporcion = p;

	}

	@Override
	public void hacer_seleccion(ArrayList<ACromosoma> poblacion, String tipoProblema) {

		ArrayList<ACromosoma> nueva_pob = new ArrayList<ACromosoma>();

		Collections.sort(poblacion, new CromosomaComparator());

		if (proporcion == 50) {
			vueltas = (int) (1 / 0.5);

		} else {
			vueltas = (int) (1 / 0.1);
		}

		for (int i = 0; i < vueltas; i++) {
			for (int j = 0; j < proporcion; j++) {
				nueva_pob.add(new CromosomaHospitales(poblacion.get(j)));
			}
		}

		Collections.copy(poblacion, nueva_pob);
	}

}
