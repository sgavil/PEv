package es.pe.mdelossgavil.GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;

public class PanelPoblacion extends JPanel {
	public JSpinner pobSpinner;
	public JSpinner genSpinner;
	
	public JTextField toleranciaTF;

	
	/**
	 * Create the panel.
	 */
	public PanelPoblacion() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Atributos de la poblaci\u00F3n",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setLayout(new GridLayout(3, 2, 0, 0));

		JLabel poblacionLabel = new JLabel("Poblaci\u00F3n");
		poblacionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(poblacionLabel);

		 
		pobSpinner = new JSpinner();
		pobSpinner.setModel(new SpinnerNumberModel(new Integer(100), new Integer(0), null, new Integer(1)));
		add(pobSpinner);

		JLabel GeneracionesLabel = new JLabel("Generaciones");
		GeneracionesLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(GeneracionesLabel);

		genSpinner = new JSpinner();
		genSpinner.setModel(new SpinnerNumberModel(new Integer(100), new Integer(0), null, new Integer(1)));
		add(genSpinner);

		JLabel ToleranciaLabel = new JLabel("Tolerancia");
		ToleranciaLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(ToleranciaLabel);
		
		toleranciaTF = new JTextField();
		toleranciaTF.setText("0.001");
		toleranciaTF.setHorizontalAlignment(SwingConstants.RIGHT);
		add(toleranciaTF);
		toleranciaTF.setColumns(10);

		
	}

}
