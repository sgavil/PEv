package es.pe.mdelossgavil.Poblacion;

import java.util.Comparator;

public class CromosomaComparator implements Comparator<ACromosoma>{

	@Override
	public int compare(ACromosoma a, ACromosoma b) {
        return a.get_aptitud() < b.get_aptitud() ? -1 : a.get_aptitud() == b.get_aptitud() ? 0 : 1;

	}

}
