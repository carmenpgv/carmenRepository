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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

public class GenerarInforme extends JPanel implements ActionListener {
	private DatosPanel datosPanelPaciente;
	private DatosPanel datosPanelMedico;
	private DatosPanel datosPanelConsultorio;
	private JPanel encabezado;
	private JPanel cuerpo;
	private ImageIcon imagen;
	private JLabel titulo;

	private JLabel lblGenerar;
	private JButton informe;
	private JLabel flecha;

	public GenerarInforme() {
		setLayout(new BorderLayout());
		encabezado = new JPanel();
		encabezado.setBackground(new Color(234, 234, 234));
		encabezado.setLayout(new BorderLayout());
		JLabel icono = new JLabel(new ImageIcon("generar.png"));
		icono.setPreferredSize(new Dimension(70, 70));
		encabezado.add(icono, BorderLayout.WEST);
		JLabel titulo = new JLabel("Generar Receta");
		titulo.setFont(new Font("sansSerif", Font.PLAIN, 25));
		titulo.setForeground(Color.black);
		encabezado.add(titulo, BorderLayout.CENTER);
		encabezado.setSize(850, 70);

		cuerpo = new JPanel();
		cuerpo.setLayout(new GridBagLayout());

		GridBagConstraints s = new GridBagConstraints();
		s.fill = GridBagConstraints.BOTH;

		lblGenerar = new JLabel("Generar Informe ");
		lblGenerar.setFont(new Font("sansSerif", Font.PLAIN, 25));
		s.gridx = 0;
		s.gridy = 0;
		cuerpo.add(this.lblGenerar, s);

		flecha = new JLabel(new ImageIcon("flecha.png"));
		s.gridx = 1;
		s.gridy = 0;
		cuerpo.add(this.flecha, s);

		informe = new JButton(new ImageIcon("informe.png"));
		informe.setBackground(new Color(234, 234, 234));
		informe.addActionListener(this);
		s.gridx = 2;
		s.gridy = 0;
		cuerpo.add(this.informe, s);

		add(encabezado, BorderLayout.NORTH);
		add(cuerpo, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(850, 550));
		this.setVisible(false);
	}

	private void generarReceta() {

		String medico = datosPanelMedico.getTxt1();
		String especialidad = datosPanelMedico.getTxt2();
		int nColegiado = 0;
		try {
			nColegiado = Integer.valueOf(datosPanelMedico.getTxt3());
		} catch (NumberFormatException nfe) {
			nfe.getStackTrace();
		}
		String paciente = datosPanelPaciente.getTxt1();
		String dPaciente = datosPanelPaciente.getTxt2();
		int ssPac = 0;
		try {
			ssPac = Integer.valueOf(datosPanelPaciente.getTxt3());
		} catch (NumberFormatException nfe) {
			nfe.getStackTrace();
		}
		String consultorio = datosPanelConsultorio.getTxt1();
		String dConsultorio = datosPanelConsultorio.getTxt2();
		String telefonoConsultorio = datosPanelConsultorio.getTxt3();

		JasperPrint informeLleno = ReportGenerator.generarDatos(medico, especialidad, nColegiado, paciente, dPaciente,
				ssPac, consultorio, dConsultorio, telefonoConsultorio);
		// exportar a pdf el archivo
		try {
			JasperExportManager.exportReportToPdfFile(informeLleno, "receta.pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		generarReceta();
	}
}
