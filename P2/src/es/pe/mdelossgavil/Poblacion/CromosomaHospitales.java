package es.pe.mdelossgavil.Poblacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

public class CromosomaHospitales extends ACromosoma {

	public static int[][] flujos;
	public static int [][] distancias;
	public static int N;

	public CromosomaHospitales() {

		
		
	}


	@Override
	public float evaluar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void inicializa_cromosoma() {

		// Creamos el array del individuo

		longitud = CromosomaHospitales.N;
		
		for (int i = 0; i < longitud; i++) 
		{
			genes.add(new TGen<Integer>());
			genes.set(i, i);
		}

		Collections.shuffle(genes);
		
		

	}

	@Override
	public ACromosoma clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
