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
	private DatosPanel panelPaciente;
	private DatosPanel panelMedico;
	private DatosPanel panelConsultorio;
	private GenerarInforme panelGenerar;
	private OpcionesPanel opcionesPanel;
	
	public Marco() {
		super("Receta médica");
		
		setLayout(new BorderLayout());
		
		opcionesPanel = new OpcionesPanel();
		panelPaciente = new DatosPanel("Nombre Paciente: ", "Dirección: ", "Nº Documento: ", "paciente.png", "Datos Paciente");
		panelMedico = new DatosPanel("Nombre Médico: ", "Especialidad: ", "Colegiado nº : ", "medico.png", "Datos Medico");
		panelConsultorio = new DatosPanel("Consultorio: ", "Dirección: ", "Teléfono: ", "consultorio.png", "Datos Consultorio");
		panelGenerar = new GenerarInforme();
		opcionesPanel.setPacientePanel(panelPaciente);
		opcionesPanel.setMedicoPanel(panelMedico);
		opcionesPanel.setConsultorioPanel(panelConsultorio);
		opcionesPanel.setGenerarPanel(panelGenerar);
		
		centro = new JPanel();
		centro.setLayout(new FlowLayout());
		centro.add(panelPaciente);
		centro.add(panelMedico);
		centro.add(panelConsultorio);
		centro.add(panelGenerar);
		
		add(opcionesPanel, BorderLayout.WEST);
		add(centro, BorderLayout.CENTER);
		setSize(1200, 670);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	}
	
}
