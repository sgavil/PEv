package es.pe.mdelossgavil.GUI;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;

public class PanelCruce extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelCruce() {
		setBorder(new TitledBorder(null, "Cruce", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setLayout(new GridLayout(1, 3, 15, 0));
		
		JComboBox cruceBox = new JComboBox();
		cruceBox.setModel(new DefaultComboBoxModel(new String[] {" Monopunto", " Uniforme", " Discreto Uniforme", " Aritm\u00E9tico", " BLX-Alpha"}));
		add(cruceBox);

		JLabel probCruceLabel = new JLabel("Probabilidad de cruce %");
		probCruceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(probCruceLabel);
		
		textField = new JTextField();
		textField.setText("0");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		add(textField);
		textField.setColumns(10);

	}

}
