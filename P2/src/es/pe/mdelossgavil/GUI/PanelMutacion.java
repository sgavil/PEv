package es.pe.mdelossgavil.GUI;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import es.pe.mdelossgavil.Main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PanelMutacion extends JPanel {
	public JTextField probMut;
	

	/**
	 * Create the panel.
	 */
	public PanelMutacion() {
		setBorder(new TitledBorder(null, "Mutaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setLayout(new GridLayout(1, 3, 0, 0));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mutacion por inserccion", "Mutacion por intercambio", "Mutacion por inversion", "Mutacion por reemplazamiento", "Mutacion heuristica"}));
		comboBox.setSelectedIndex(0);
		add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Probabilidad de mutaci\u00F3n %");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		
		probMut = new JTextField();
		probMut.setHorizontalAlignment(SwingConstants.CENTER);
		probMut.setText("5");
		add(probMut);
		probMut.setColumns(10);
		
		Main.MUTACION = "Mutacion por inserccion";
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cruceb = (JComboBox )(e.getSource());
				String metodoMutacion = (String)cruceb.getSelectedItem();
				Main.MUTACION = metodoMutacion;
				
			}
		});



	}

}
