package es.pe.mdelossgavil;

import java.util.ArrayList;

import es.pe.mdelossgavil.Cruce.Cruce;
import es.pe.mdelossgavil.Poblacion.Cromosoma;
import es.pe.mdelossgavil.Seleccion.*;

public class AGenetico {
	
	ArrayList<Cromosoma> poblacion;
	int tam_pob;
	int	num_max_gen;
	Cromosoma elMejor;
	int posMejor;
	float prob_cruce;
	float prob_mut;
	float tolerancia;
	
	/*Operadores*/
	Seleccion seleccion;
	//Evalua los individuos y coge los mejroes
	void evaluarPoblacion(){
		
	}
	
	//Crea una poblacion inicial de cromosomas ademas de escoger una seleccion,cruce y mutacion
	void inicializa(Seleccion seleccion_) {
		seleccion=seleccion_;
		seleccion_.hacerSeleccion(poblacion);
	}
}
