package aplicacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Marco extends JFrame{

	// MARCO TALLER LAYOUT
	private JPanel centro;
	private GenerarInformeCP codigoPostalPanel;
	private GenerarInformeCParametros codigoPostalParametrosPanel;
	private GenerarGrafica panelGrafica;
	private OpcionesPanel opcionesPanel;
	
	public Marco() {
		super("Clientes");
		
		setLayout(new BorderLayout());
		
		opcionesPanel = new OpcionesPanel();
		codigoPostalPanel = new GenerarInformeCP();
		codigoPostalParametrosPanel = new GenerarInformeCParametros();
		panelGrafica = new GenerarGrafica();
		opcionesPanel.setCodigoPostalPanel(codigoPostalPanel);
		opcionesPanel.setCodigoPostalParametros(codigoPostalParametrosPanel);
		opcionesPanel.setGrafica(panelGrafica);
		
		centro = new JPanel();
		centro.setLayout(new FlowLayout());
		centro.add(codigoPostalPanel);
		centro.add(codigoPostalParametrosPanel);
		centro.add(panelGrafica);
		
		add(opcionesPanel, BorderLayout.WEST);
		add(centro, BorderLayout.CENTER);
		setSize(1200, 670);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	}
	
}
