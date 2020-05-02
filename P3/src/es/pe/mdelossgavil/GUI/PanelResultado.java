package es.pe.mdelossgavil.GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class PanelResultado extends JPanel {

	/**
	 * Create the panel.
	 */
	public JTextArea resultText;
	
	public PanelResultado() {
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);  //give your JPanel a BorderLayout
		resultText = new JTextArea(); 
		resultText.setRows(5);
		JScrollPane scroll = new JScrollPane(resultText); //place the JTextArea in a scroll pane
		add(scroll, BorderLayout.CENTER);

	}

}
