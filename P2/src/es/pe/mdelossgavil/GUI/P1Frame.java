package es.pe.mdelossgavil.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.pe.mdelossgavil.Graficas.Grafica;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class P1Frame extends JFrame {


	public PanelWest panelWest;
	
	public Grafica grafica;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					P1Frame frame = new P1Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public P1Frame() {
		super();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 600);
		setLayout(new BorderLayout());
		
		panelWest = new PanelWest();
		add(panelWest, BorderLayout.WEST);
		
		//PanelGrafica panelGrafica = new PanelGrafica();
		grafica = new Grafica(600, 600);
		add(grafica.get_panel(),BorderLayout.CENTER);
		
		
		/*pPoblacion = new PanelPoblacion();
		contentPane.add(pPoblacion);
		
		pSeleccion = new PanelSeleccion();
		contentPane.add(pSeleccion);
		
		pCruce = new PanelCruce();
		contentPane.add(pCruce);
		
		pMutacion = new PanelMutacion();
		contentPane.add(pMutacion);
		
		pOtros = new PanelOtros();
		contentPane.add(pOtros);
		
		 pSelectorProblema = new PanelSelectorProblema();
		contentPane.add(pSelectorProblema );*/
		
	
		//add(contentPane,BorderLayout.WEST);
		//add(contentPane,BorderLayout.EAST);
	}

}
