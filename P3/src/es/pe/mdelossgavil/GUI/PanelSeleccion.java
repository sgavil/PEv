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

public class PanelSeleccion extends JPanel {

	public JComboBox seleccionBox;
	private DefaultListCellRenderer listRenderer;
	/**
	 * Create the panel.
	 */
	public PanelSeleccion() {
		listRenderer = new DefaultListCellRenderer();
	     listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
	      
		setBorder(new TitledBorder(null, "Selecci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		seleccionBox = new JComboBox();
		seleccionBox.setModel(new DefaultComboBoxModel(new String[] {"Ruleta", "Estoc\u00E1stico Universal", "Torneos", "Ranking", "Truncamiento"}));
		seleccionBox.setSelectedIndex(0);
		seleccionBox.setRenderer(listRenderer);
		add(seleccionBox);
		
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
