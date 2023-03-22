package aplicacion;

import java.util.HashMap;

import javax.swing.WindowConstants;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportGenerator {
	public static final String RECETA = "01_jr_carmen_portoles4.jasper";
	
	
	//crear método pasamos un parámetro
	
	public static JasperPrint generarDatos(String medico, String especialidad, int nColegiado, String paciente, 
			String dPaciente, int ssPaciente, String consultorio, String dConsultorio, String nConsulta) {
		//HashMap que contendrá el parámetro(s), en este caso ha de ser de tipo string
		
		HashMap<String,Object> parametros = new HashMap<>();
		
		parametros.put("nombreDra", medico);
		parametros.put("especialidad", especialidad);
		parametros.put("nColegiado", nColegiado);
		parametros.put("nombrePac", paciente);
		parametros.put("direccionPac", dPaciente);
		parametros.put("ssPac", ssPaciente);
		parametros.put("nombreCons", consultorio);
		parametros.put("direccionCons", dConsultorio);
		parametros.put("numeroCons", nConsulta);
		
		try {
			//proceso de llenado del informe
			JasperPrint informeLleno = JasperFillManager.fillReport(RECETA,  parametros, new JREmptyDataSource());
			JasperViewer viewer = new JasperViewer(informeLleno,false);
			viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			viewer.setVisible(true);
			return informeLleno;
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
 }
