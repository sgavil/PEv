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
	public JSpinner profspinner;
	private JLabel lblNewLabel_1;
	public JComboBox comboBox;

	
	/**
	 * Create the panel.
	 */
	public PanelPoblacion() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Atributos de la poblaci\u00F3n",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setLayout(new GridLayout(4, 2, 0, 0));

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
		genSpinner.setModel(new SpinnerNumberModel(new Integer(200), new Integer(0), null, new Integer(1)));
		add(genSpinner);
		
		lblNewLabel = new JLabel("Profundidad m\u00E1xima");
		add(lblNewLabel);
		
		profspinner = new JSpinner();
		profspinner.setModel(new SpinnerNumberModel(new Integer(4), null, null, new Integer(1)));
		add(profspinner);
		
		lblNewLabel_1 = new JLabel("Entradas multiplexor");
		add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"6 entradas", "11 entradas"}));
		comboBox.setSelectedIndex(0);
		comboBox.setToolTipText("");
		add(comboBox);

		Main.entradas = 6;

		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox numeroEntradas = (JComboBox )(e.getSource());
				String metodoSeleccion = (String)numeroEntradas.getSelectedItem();
				if(metodoSeleccion.equals("6 entradas"))
					Main.entradas=6;
				else
					Main.entradas=11;
			}
		});
		
	}

}
