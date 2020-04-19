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

import es.pe.mdelossgavil.Main;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;

public class PanelPoblacion extends JPanel {
	public JSpinner pobSpinner;
	public JSpinner genSpinner;
	private JLabel lblNewLabel;
	private JComboBox comboBox;

	
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
		
		lblNewLabel = new JLabel("Archivo ");
		add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ajuste", "datos12", "datos15", "datos30"}));
		comboBox.setSelectedIndex(0);
		add(comboBox);
		
comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cruceb = (JComboBox )(e.getSource());
				String nombreArchivo = (String)cruceb.getSelectedItem();
				Main.NOMBRE_ARCHIVO = nombreArchivo;
				
			}
		});

		
	}

}
