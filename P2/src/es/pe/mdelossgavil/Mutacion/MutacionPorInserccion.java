package es.pe.mdelossgavil.Mutacion;

import java.util.ArrayList;
import java.util.Random;

//import org.omg.CORBA.portable.IndirectionException;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.TGen;

/**
 * Clase que realiza la mutación de genes de tipo Boolean, es decir, mutación
 * bit a bit
 *
 */
public class MutacionPorInserccion implements IMutacion {

	public MutacionPorInserccion() {

	}

	@Override
	public void mutar(ACromosoma individuo) {

		int lCrom = individuo.get_longitud();
		// Escogemos posicion y gen aleatorios
		Random r = new Random();
		int posicion = r.nextInt(lCrom);
		int genAInsertar = (int) individuo.getCodificacion().get(posicion);
		individuo.getCodificacion().remove(posicion);
		// Lo instertamos en una posicion aleatoria
		individuo.getCodificacion().add(r.nextInt(lCrom), genAInsertar);

		/* Actualizamos los genes */
		int comienzo = 0;
		for (int j = 0; j < individuo.get_genes().size(); j++) {
			/* Cogemos el tamanio del gen */
			TGen gen = (TGen) individuo.get_genes().get(j);
			int tam = gen.getGenotipo().size();
			/* Trasladamos el Array */
			actualizarGen(individuo, tam, comienzo, j);
			comienzo += tam;

		}

	}

	private void actualizarGen(ACromosoma cromosoma, int tam, int comienzo, int gen) {
		for (int i = 0; i < tam; i++) {
			((TGen) cromosoma.get_genes().get(gen)).getGenotipo().set(i, cromosoma.getCodificacion().get(i + comienzo));
		}
	}

}
