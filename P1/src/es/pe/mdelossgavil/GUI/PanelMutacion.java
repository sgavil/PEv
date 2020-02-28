package es.pe.mdelossgavil.GUI;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class PanelMutacion extends JPanel {
	public JTextField probMut;

	/**
	 * Create the panel.
	 */
	public PanelMutacion() {
		setBorder(new TitledBorder(null, "Mutaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Probabilidad de mutaci\u00F3n %");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		probMut = new JTextField();
		probMut.setHorizontalAlignment(SwingConstants.CENTER);
		probMut.setText("0");
		add(probMut);
		probMut.setColumns(10);

	}

}
