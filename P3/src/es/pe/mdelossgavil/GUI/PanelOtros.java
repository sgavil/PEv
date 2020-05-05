package es.pe.mdelossgavil.GUI;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class PanelOtros extends JPanel {
	public JTextField valElitismo;

	/**
	 * Create the panel.
	 */
	public PanelOtros() {
		setBorder(new TitledBorder(null, "Otros", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setLayout(new GridLayout(1, 2, 0, 0));
		
		
		JLabel lblNewLabel = new JLabel("Elitismo %");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		valElitismo = new JTextField();
		valElitismo.setHorizontalAlignment(SwingConstants.CENTER);
		valElitismo.setText("5");
		add(valElitismo);
		valElitismo.setColumns(10);

	}

}
