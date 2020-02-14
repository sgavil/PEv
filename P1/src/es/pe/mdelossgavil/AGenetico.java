package es.pe.mdelossgavil;

import java.util.ArrayList;

import es.pe.mdelossgavil.Cruce.Cruce;
import es.pe.mdelossgavil.Poblacion.Cromosoma;
import es.pe.mdelossgavil.Poblacion.CromosomaF1;
import es.pe.mdelossgavil.Seleccion.*;

public class AGenetico {
	
	/*Atributos de la poblacion*/
	ArrayList<Cromosoma> poblacion;
	int tam_pob;
	int	num_max_gen;
	Cromosoma elMejor;
	int posMejor;
	float prob_cruce;
	float prob_mut;
	float tolerancia;
	
	/*Operadores*/
	Seleccion seleccion_;
	Cruce cruce_;
	
	
	
	//Crea una poblacion inicial de cromosomas ademas de escoger una seleccion,cruce y mutacion
	private void inicializa(Seleccion seleccion,Cruce cruce) {
		/*Guardamos los operadores*/
		seleccion_=seleccion;
		cruce_=cruce;
		poblacion=new ArrayList<Cromosoma>();
		
		/*Inicializamos la poblacion de cromosomas*/
		for(int i=0;i<tam_pob;i++)
		{
			poblacion.add(new CromosomaF1());
			poblacion.get(i).inicializaCromosoma();
			poblacion.get(i).setAptitud(poblacion.get(i).evaluar());
		}
		
	}
	
	//Evalua los individuos y coge los mejroes
	private void evaluarPoblacion(){
		float punt_acum=0;
		float aptitud_mejor=0;
		float suma_aptitud=0;
		
		for(int i=0;i<tam_pob;i++)
		{
			suma_aptitud+=poblacion.get(i).getAptitud();
			if(poblacion.get(i).getAptitud()>aptitud_mejor)
			{
				posMejor=i;
				aptitud_mejor=poblacion.get(i).getAptitud();
			}
			
		}
		
	}
}
