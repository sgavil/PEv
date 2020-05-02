package es.pe.mdelossgavil.GUI;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import es.pe.mdelossgavil.Main;

import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Component;

public class PanelSelectorProblema extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public PanelSelectorProblema() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		
		JButton btnEjecutar = new JButton("Ejecutar");

		add(btnEjecutar);
		
		
		
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
