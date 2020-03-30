package es.pe.mdelossgavil.Mutacion;

import java.util.ArrayList;
import java.util.Random;

import org.omg.CORBA.portable.IndirectionException;

import es.pe.mdelossgavil.Poblacion.ACromosoma;
import es.pe.mdelossgavil.Poblacion.TGen;

/**
 * Clase que realiza la mutación de genes de tipo Boolean, es decir, mutación
 * bit a bit
 *
 */
public class MutacionPorIntercambio implements IMutacion {

	public MutacionPorIntercambio() {

	}

	@Override
	public void mutar(ACromosoma individuo) {

		int primero, segundo;
		int lCrom = individuo.get_longitud();
		// escogemos dos puntos al azar
		Random r = new Random();

		primero = r.nextInt(lCrom);
		segundo = r.nextInt(lCrom);
		while (primero == segundo)
			segundo = r.nextInt(lCrom);

		aplicarMutacion(individuo, primero, segundo);

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

	private <T> void aplicarMutacion(ACromosoma invididuo, int primero, int segundo) {
		T aux = (T) invididuo.getCodificacion().get(primero);
		invididuo.getCodificacion().set(primero, invididuo.getCodificacion().get(segundo));
		invididuo.getCodificacion().set(segundo, aux);

	}

	private void actualizarGen(ACromosoma cromosoma, int tam, int comienzo, int gen) {
		for (int i = 0; i < tam; i++) {
			((TGen) cromosoma.get_genes().get(gen)).getGenotipo().set(i, cromosoma.getCodificacion().get(i + comienzo));
		}
	}

}
