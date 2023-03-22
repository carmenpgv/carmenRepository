package aplicacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OpcionesPanel extends JPanel implements ActionListener{


	private GenerarInformeCP codigoPostalPanel;
	private GenerarInformeCParametros codigoPostalParametrosPanel;
	private GenerarGrafica graficaPanel;
	private JPanel panelCentral;
	JPanel imagenPanel;
	JPanel botones;
	JButton cp;
	JButton cpParametros;
	JButton grafica;
	JButton salir;
	JButton boton = new JButton();
	
	public OpcionesPanel() {
		
		//Panel imagen
		imagenPanel = new JPanel();
		
		ImageIcon imagen = new ImageIcon("receta.png");
		JLabel label = new JLabel(imagen);

		imagenPanel.setLayout(new BorderLayout());
		imagenPanel.add(label, BorderLayout.NORTH);
		imagenPanel.setBackground(Color.black);
		
		//botones
		cp = new JButton();
		setButtonProperties(cp, "paciente.png", "Código Postal", "cp");

		cpParametros = new JButton();
		setButtonProperties(cpParametros, "medico.png", "Código Postal parámetro", "cparametros");

		grafica = new JButton();
		setButtonProperties(grafica, "consultorio.png", "Gráfica ciudades", "grafica");

		salir = new JButton();
		setButtonProperties(salir, "salir.png", "Salir", "salir");

		botones = new JPanel();
		botones.setLayout(new GridBagLayout());
		GridBagConstraints s = new GridBagConstraints();
		s.fill = GridBagConstraints.BOTH;

		s.gridx=0;
		s.gridy=0;
		botones.add(imagenPanel, s);
		
		s.gridx=0;
		s.gridy=1;
		botones.add(cp, s);
		
		s.gridx=0;
		s.gridy=2;
		botones.add(cpParametros, s);
		
		s.gridx=0;
		s.gridy=3;
		botones.add(grafica, s);
		
		s.gridx=0;
		s.gridy=5;
		botones.add(salir, s);
		

		setLayout(new BorderLayout());
		add(botones, BorderLayout.CENTER);

		
		
	}
	public void setCodigoPostalPanel(GenerarInformeCP panel) {
		this.codigoPostalPanel = panel;
	}
	public void setCodigoPostalParametros(GenerarInformeCParametros panel) {
		this.codigoPostalParametrosPanel = panel;
	}
	public void setGrafica(GenerarGrafica panel) {
		this.graficaPanel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		cp.setBackground(new Color(47,47,47));
		cpParametros.setBackground(new Color(47,47,47));
		grafica.setBackground(new Color(47,47,47));
		codigoPostalPanel.setVisible(false);
		codigoPostalParametrosPanel.setVisible(false);
		graficaPanel.setVisible(false);
		switch (e.getActionCommand()) {
		case "cp":
			cp.setBackground(new Color(44,61,79));
			codigoPostalPanel.setVisible(true);
			break;
		case "cparametros":
			cpParametros.setBackground(new Color(44,61,79));
			codigoPostalParametrosPanel.setVisible(true);
			break;
		case "grafica":
			grafica.setBackground(new Color(44,61,79));
			graficaPanel.setVisible(true);
			break;
		case "salir":
			salir.setBackground(new Color(44,61,79));
			System.exit(0);
			break;
		}
		
	}
	
	private void setButtonProperties(JButton boton, String imagen, String texto, String command) {
		boton.setBackground(new Color(47,47,47));
		boton.setLayout(new BorderLayout());
		JLabel icono = new JLabel(new ImageIcon(imagen));
		icono.setPreferredSize(new Dimension(70,70));
		boton.add(icono, BorderLayout.WEST);
		JLabel titulo = new JLabel(texto);
		titulo.setFont(new Font("sansSerif", Font.PLAIN, 25));
		titulo.setForeground(Color.white);
		boton.add(titulo, BorderLayout.CENTER);
		boton.setSize(350, 70);
		boton.setBorderPainted(false);
		boton.setActionCommand(command);
		boton.addActionListener(this);
	}

	
	
	
	
	
}
