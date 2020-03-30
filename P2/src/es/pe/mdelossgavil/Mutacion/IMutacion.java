package es.pe.mdelossgavil.Mutacion;

import java.util.ArrayList;

import es.pe.mdelossgavil.Poblacion.ACromosoma;

public interface IMutacion {
	/**
	 * Recorremos cada gen generando un n�mero aleatorio, si el n�mero es menor a la probabilidad
	 * se cambia el gen por su complementario, si no, se deja el gen como est�
	 */
	public void mutar(ACromosoma individuo);
}
