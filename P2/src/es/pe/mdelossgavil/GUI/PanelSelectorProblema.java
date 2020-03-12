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

	public JComboBox problemaBox;
	public JTextArea textArea;
	
	/**
	 * Create the panel.
	 */
	public PanelSelectorProblema() {
		setLayout(new GridLayout(2, 2, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Problema");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		problemaBox= new JComboBox();
		problemaBox.setModel(new DefaultComboBoxModel(new String[] {"Funci\u00F3n 1", "Funci\u00F3n 2: H\u00F6lder Table", "Funci\u00F3n 3: Schubert", "Funci\u00F3n 4: Michalewicz", "Michalewicz ( Reales )"}));
		problemaBox.setSelectedIndex(0);
		add(problemaBox);
		
		Main.PROBLEMA = "F1"; 

		
		JButton btnEjecutar = new JButton("Ejecutar");
		add(btnEjecutar);
		
		textArea = new JTextArea();
		add(textArea);
		
		problemaBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox probB = (JComboBox )(e.getSource());
								
				String problema = (String)probB.getSelectedItem();
				if(problema.equals("Función 1"))
					problema = "F1";
				else if(problema.equals("Función 2: Hölder Table"))
					problema = "F2";
				else if(problema.equals("Función 3: Schubert"))
					problema = "F3";
				else if(problema.equals("Función 4: Michalewicz"))
					problema = "F4";
				else if(problema.equals("Michalewicz ( Reales )"))
					problema = "P2";

				Main.PROBLEMA = problema; 
				
			}
		});
		
		btnEjecutar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("boton pulsado");
				//Main.INICIAR_ALGORITMO = true;
				//Main.iniciaAlgoritmo();
				//Main.creaGrafica();
			}
		});

	}

}
