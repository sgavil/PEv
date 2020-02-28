package es.pe.mdelossgavil.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class P1Frame extends JFrame {

	private JPanel contentPane;
	
	public PanelSeleccion pSeleccion;
	
	public PanelPoblacion pPoblacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					P1Frame frame = new P1Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public P1Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 1, 0, 0));
		setSize(500,600);
		pPoblacion = new PanelPoblacion();
		contentPane.add(pPoblacion);
		
		pSeleccion = new PanelSeleccion();
		contentPane.add(pSeleccion);
		
		PanelCruce panelCruce = new PanelCruce();
		contentPane.add(panelCruce);
		
		PanelMutacion panelMutacion = new PanelMutacion();
		contentPane.add(panelMutacion);
		
		PanelOtros panelOtros = new PanelOtros();
		contentPane.add(panelOtros);
		
		PanelSelectorProblema panelSelectorProblema = new PanelSelectorProblema();
		contentPane.add(panelSelectorProblema);
	}

}
