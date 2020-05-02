package es.pe.mdelossgavil.Graficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import org.math.plot.*;

public class Grafica extends JPanel{
		
	private Plot2DPanel plot;
	private JPanel panel;
	
	public Grafica(int width,int height) {
		//this.width = width;
		//this.height = height;
		panel = new JPanel(new GridLayout());
		panel.setSize(width,height);
		
		plot = new Plot2DPanel();
		panel.add(plot);
		plot.addLegend("SOUTH");
		plot.setLegendOrientation("NORTH");
		plot.setAxisLabel(0, "Generaciones");

	}

	public void inicializa_grafica() {
		plot.removeAllPlots();
		
	}

	public void agregar_linea(String titulo,double[]x,double[]y) {
		plot.addLinePlot(titulo, x, y);

	}
	
	public void pinta_grafica() {
		//frame.setVisible(true);

	}
	
	public JPanel get_panel() {
		return panel;
	}
	



}
