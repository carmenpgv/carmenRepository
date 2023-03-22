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


	private DatosPanel datosPanelPaciente;
	private DatosPanel datosPanelMedico;
	private DatosPanel datosPanelConsultorio;
	private GenerarInforme panelGenerarInforme;
	private JPanel panelCentral;
	JPanel imagenPanel;
	JPanel botones;
	JButton paciente;
	JButton medico;
	JButton consultorio;
	JButton generar;
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
		paciente = new JButton();
		setButtonProperties(paciente, "paciente.png", "Datos Paciente", "paciente");

		medico = new JButton();
		setButtonProperties(medico, "medico.png", "Datos Médico", "medico");

		consultorio = new JButton();
		setButtonProperties(consultorio, "consultorio.png", "Datos Consultorio", "consultorio");

		generar = new JButton();
		setButtonProperties(generar, "generar.png", "Generar Recetario", "generar");

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
		botones.add(paciente, s);
		
		s.gridx=0;
		s.gridy=2;
		botones.add(medico, s);
		
		s.gridx=0;
		s.gridy=3;
		botones.add(consultorio, s);
		
		s.gridx=0;
		s.gridy=4;
		botones.add(generar, s);
		
		s.gridx=0;
		s.gridy=5;
		botones.add(salir, s);
		

		setLayout(new BorderLayout());
		add(botones, BorderLayout.CENTER);

		
		
	}
	public void setPacientePanel(DatosPanel panel) {
		this.datosPanelPaciente = panel;
	}
	public void setMedicoPanel(DatosPanel panel) {
		this.datosPanelMedico = panel;
	}
	public void setConsultorioPanel(DatosPanel panel) {
		this.datosPanelConsultorio = panel;
	}
	public void setGenerarPanel(GenerarInforme panel) {
		this.panelGenerarInforme = panel;
		panelGenerarInforme.setPacientePanel(datosPanelPaciente);
		panelGenerarInforme.setMedicoPanel(datosPanelMedico);
		panelGenerarInforme.setConsultorioPanel(datosPanelConsultorio);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		paciente.setBackground(new Color(47,47,47));
		medico.setBackground(new Color(47,47,47));
		consultorio.setBackground(new Color(47,47,47));
		generar.setBackground(new Color(47,47,47));
		datosPanelPaciente.setVisible(false);
		datosPanelMedico.setVisible(false);
		datosPanelConsultorio.setVisible(false);
		panelGenerarInforme.setVisible(false);
		switch (e.getActionCommand()) {
		case "paciente":
			paciente.setBackground(new Color(44,61,79));
			datosPanelPaciente.setVisible(true);
			break;
		case "medico":
			medico.setBackground(new Color(44,61,79));
			datosPanelMedico.setVisible(true);
			break;
		case "consultorio":
			consultorio.setBackground(new Color(44,61,79));
			datosPanelConsultorio.setVisible(true);
			break;
		case "generar":
			generar.setBackground(new Color(44,61,79));
			panelGenerarInforme.setVisible(true);
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
