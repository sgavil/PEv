package es.pe.mdelossgavil.Poblacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

import es.pe.mdelossgavil.Utils;

public class CromosomaHospitales extends ACromosoma {

	public static int[][] flujos;
	public static int [][] distancias;
	public static int N;


	public CromosomaHospitales() {

		
		
	}

	public CromosomaHospitales(ACromosoma aCromosoma) {
		this.puntuacion = aCromosoma.puntuacion;
		this.punt_acum = aCromosoma.punt_acum;
		this.codificacion = aCromosoma.codificacion;
		this.longitud = aCromosoma.longitud;
		this.aptitud = aCromosoma.aptitud;
		this.genes = aCromosoma.genes;
	}
	

	@Override
	public float evaluar() 
	{
		actualiza_codificacion();
		
		int total = 0;
		
		for (int i = 0; i < CromosomaHospitales.N; i++) {
			for (int j = 0; j < CromosomaHospitales.N; j++) {
				total+= CromosomaHospitales.distancias[i][j] * 
						CromosomaHospitales.flujos[((TGen<Integer>) genes.get(i)).getGenotipo().get(0)]
								[((TGen<Integer>) genes.get(j)).getGenotipo().get(0)];
			}
		}
		
		return total;
	}
	
	public String get_fenotipo() {
		String s = "";
		for (int i = 0; i < genes.size(); i++) {
			s+= Integer.toString(((TGen<Integer>) genes.get(i)).getGenotipo().get(0)) + " ";
		}
		return s;
	}

	@Override
	public void inicializa_cromosoma() {

		// Creamos el array del individuo

		longitud = CromosomaHospitales.N;
		
		for (int i = 0; i < longitud; i++) 
		{
			genes.add(new TGen<Integer>());
			((TGen<Integer>)genes.get(i)).getGenotipo().add(i);
		}

		Collections.shuffle(genes);
		
		setCodificacion();

	}

	@Override
	public ACromosoma clone() {
		// TODO Auto-generated method stub
		return new CromosomaHospitales(this);
	}

	@Override
	public void copia() {
		// TODO Auto-generated method stub
		return;
	}

}
