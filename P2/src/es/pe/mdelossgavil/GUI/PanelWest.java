package es.pe.mdelossgavil.GUI;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

public class PanelWest extends JPanel {

	public PanelSeleccion pSeleccion;

	public PanelPoblacion pPoblacion;

	public PanelOtros pOtros;

	public PanelCruce pCruce;

	public PanelMutacion pMutacion;

	public PanelSelectorProblema pSelectorProblema;

	
	/**
	 * Create the panel.
	 */
	public PanelWest() {
		setLayout(new GridLayout(6, 1, 0, 0));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(200,200);

		pPoblacion = new PanelPoblacion();
		add(pPoblacion);

		pSeleccion = new PanelSeleccion();
		add(pSeleccion);

		pCruce = new PanelCruce();
		add(pCruce);

		pMutacion = new PanelMutacion();
		add(pMutacion);

		pOtros = new PanelOtros();
		add(pOtros);

		pSelectorProblema = new PanelSelectorProblema();
		add(pSelectorProblema);
	}

}
