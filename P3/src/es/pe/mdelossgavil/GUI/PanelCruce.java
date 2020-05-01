package es.pe.mdelossgavil.GUI;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import es.pe.mdelossgavil.Main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class PanelCruce extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelCruce() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Bloating", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		setLayout(new GridLayout(0, 2, 15, 0));
		
		JLabel lblNewLabel = new JLabel("Tipo de bloating");
		add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tarpeian", "Penalizaci\u00F3n bien fundamentada"}));
		comboBox.setSelectedIndex(0);
		add(comboBox);
		
		Main.CRUCE = "CO";

	}

}
