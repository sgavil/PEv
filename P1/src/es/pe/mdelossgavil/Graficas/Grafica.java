package es.pe.mdelossgavil.Graficas;

import javax.swing.*;
import org.math.plot.*;

public class Grafica {

	
	private int width;
	private int height;
		
	private Plot2DPanel plot;
	private JFrame frame;
	
	public Grafica(int width,int height) {
		this.width = width;
		this.height = height;
		
	}

	public void inicializa_grafica() {
		// create your PlotPanel (you can use it as a JPanel)
		plot = new Plot2DPanel();

		// define the legend position
		plot.addLegend("SOUTH");

		// put the PlotPanel in a JFrame like a JPanel
		frame = new JFrame("a plot panel");
		frame.setSize(width, height);
		frame.setContentPane(plot);
	}

	public void agregar_linea(String titulo,double[]x,double[]y) {
		plot.addLinePlot(titulo, x, y);

	}
	public void pinta_grafica() {
		frame.setVisible(true);

	}

}
