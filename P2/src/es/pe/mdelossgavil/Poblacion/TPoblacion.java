package es.pe.mdelossgavil.Poblacion;

import java.util.ArrayList;

/**
 * Plantilla que recibe un tipo de Cromosoma e inicializa una población de este tipo
 *
 * @param <T> tipo de cromosoma
 */
public class TPoblacion<T extends ACromosoma> {
	
	public TPoblacion(){
		
	}
	
	public ArrayList<ACromosoma> inicializa_poblacion(int tam_pob,Class<T> clase_cromosoma)
	{
		ArrayList<ACromosoma> poblacion = new ArrayList<ACromosoma>();
		
		for (int i = 0; i < tam_pob; i++) 
		{
			try {
				poblacion.add(clase_cromosoma.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
			poblacion.get(i).inicializa_cromosoma(); 
			poblacion.get(i).set_aptitud(poblacion.get(i).evaluar());
		}
		
		return poblacion;
	}
}
