package es.pe.mdelossgavil.GUI;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;

import es.pe.mdelossgavil.Main;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelSeleccion extends JPanel {

	public JComboBox seleccionBox;
	private DefaultListCellRenderer listRenderer;
	public JTextField probCruce;
	/**
	 * Create the panel.
	 */
	public PanelSeleccion() {
		listRenderer = new DefaultListCellRenderer();
	     listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
	      
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Selecci\u00F3n y cruce", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		setLayout(new GridLayout(2, 2, 0, 0));
		
		
		JLabel lblNewLabel = new JLabel("Selecci\u00F3n");
		add(lblNewLabel);
		
		seleccionBox = new JComboBox();
		seleccionBox.setModel(new DefaultComboBoxModel(new String[] {"Ruleta", "Estoc\u00E1stico Universal", "Torneos", "Ranking", "Truncamiento"}));
		seleccionBox.setSelectedIndex(0);
		seleccionBox.setRenderer(listRenderer);
		add(seleccionBox);
		
		JLabel lblNewLabel_1 = new JLabel("Probabilidad de cruce %");
		add(lblNewLabel_1);
		
		probCruce = new JTextField();
		probCruce.setText("60");
		probCruce.setHorizontalAlignment(SwingConstants.CENTER);
		probCruce.setColumns(10);
		add(probCruce);
		
		Main.SELECCION = "Ruleta";

		
		seleccionBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox metodoSeleccionBox = (JComboBox )(e.getSource());
				String metodoSeleccion = (String)metodoSeleccionBox.getSelectedItem();
				Main.SELECCION = metodoSeleccion;
			}
		});
		

	}

}
