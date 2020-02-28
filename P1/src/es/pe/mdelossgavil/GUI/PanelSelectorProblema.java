package es.pe.mdelossgavil.GUI;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class PanelSelectorProblema extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelSelectorProblema() {
		setLayout(new GridLayout(2, 2, 0, 5));
		
		JLabel lblNewLabel = new JLabel("Problema");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		JComboBox problemaBox = new JComboBox();
		problemaBox.setModel(new DefaultComboBoxModel(new String[] {" Funci\u00F3n 1", " Funci\u00F3n 2: H\u00F6lder Table", " Funci\u00F3n 3: Schubert", " Funci\u00F3n 4: Michalewicz", " Michalewicz ( Reales )", " "}));
		add(problemaBox);
		
		JButton btnNewButton = new JButton("Ejecutar");
		add(btnNewButton);

	}

}
