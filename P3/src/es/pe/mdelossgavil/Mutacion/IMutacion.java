package es.pe.mdelossgavil.Mutacion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;

public interface IMutacion {
	/**
	 * Recorremos cada gen generando un número aleatorio, si el número es menor a la probabilidad
	 * se cambia el gen por su complementario, si no, se deja el gen como está
	 */
	public void mutar(ACromosoma individuo);
}
