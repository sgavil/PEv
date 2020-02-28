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

public class PanelCruce extends JPanel {
	public JTextField probCruce;
	public JComboBox cruceBox;

	/**
	 * Create the panel.
	 */
	public PanelCruce() {
		setBorder(new TitledBorder(null, "Cruce", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setLayout(new GridLayout(1, 3, 15, 0));
		
		 cruceBox = new JComboBox();
		cruceBox.setModel(new DefaultComboBoxModel(new String[] {"Monopunto", "Uniforme", "Discreto Uniforme", "Aritm\u00E9tico", "BLX-Alpha"}));
		cruceBox.setSelectedIndex(0);
		add(cruceBox);
		
		Main.CRUCE = "Monopunto";

		
		cruceBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cruceb = (JComboBox )(e.getSource());
				String metodoCruce = (String)cruceb.getSelectedItem();
				Main.CRUCE = metodoCruce;
				
			}
		});

		JLabel probCruceLabel = new JLabel("Probabilidad de cruce %");
		probCruceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(probCruceLabel);
		
		probCruce = new JTextField();
		probCruce.setText("0");
		probCruce.setHorizontalAlignment(SwingConstants.CENTER);
		add(probCruce);
		probCruce.setColumns(10);

	}

}
