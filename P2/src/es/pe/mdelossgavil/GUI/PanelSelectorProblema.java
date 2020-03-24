package es.pe.mdelossgavil.GUI;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import es.pe.mdelossgavil.Main;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class PanelSelectorProblema extends JPanel {
	public JTextArea textArea;
	
	/**
	 * Create the panel.
	 */
	public PanelSelectorProblema() {
		setLayout(new GridLayout(1, 2, 5, 5));
		
		Main.PROBLEMA = "F1"; 

		
		JButton btnEjecutar = new JButton("Ejecutar");
		add(btnEjecutar);
		
		textArea = new JTextArea();
		add(textArea);
		
		btnEjecutar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("boton pulsado");
				//Main.INICIAR_ALGORITMO = true;
				Main.iniciaAlgoritmo();
				//Main.creaGrafica();
			}
		});

	}

}
