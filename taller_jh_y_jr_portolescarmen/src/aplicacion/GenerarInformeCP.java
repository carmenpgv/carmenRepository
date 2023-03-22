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
import javax.swing.WindowConstants;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class GenerarInformeCP extends JPanel implements ActionListener {
	public static final String INFORME = "clientesporcp.jasper";
	private JPanel encabezado;
	private JPanel cuerpo;

	private JLabel lblGenerar;
	private JButton informe;
	private JLabel flecha;

	public GenerarInformeCP() {
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
	public static JasperPrint generarInforme() {
		try {
			// procedemos a llenar y configurar el JR
			JasperPrint informeLleno = JasperFillManager.fillReport(INFORME, null, Conexion.getMySQLConexion());
			// Obtenemos la vista previa del informe
			return informeLleno; 
			
		}catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JasperPrint informeCP = generarInforme();
		JasperViewer viewer = new JasperViewer(informeCP, false);
		viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		viewer.setVisible(true);
		try {
			JasperExportManager.exportReportToPdfFile(informeCP, "Clientes_Portoles.pdf");
		}catch(JRException ex) {
			ex.printStackTrace();
		}
	}
}
