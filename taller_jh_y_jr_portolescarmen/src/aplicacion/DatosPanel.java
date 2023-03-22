package aplicacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DatosPanel extends JPanel{

	// PANEL TEXTAREA TALLER LAYOUT
	private JPanel encabezado;
	private JPanel cuerpo;
	private ImageIcon imagen;
	private JLabel titulo;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	
	public DatosPanel(String lbl1,String lbl2,String lbl3,String img,String tituloEncabezado) {
		
		setLayout(new BorderLayout());
		encabezado = new JPanel();
		encabezado.setBackground(new Color(234,234,234));
		encabezado.setLayout(new BorderLayout());
		JLabel icono = new JLabel(new ImageIcon(img));
		icono.setPreferredSize(new Dimension(70,70));
		encabezado.add(icono, BorderLayout.WEST);
		JLabel titulo = new JLabel(tituloEncabezado);
		titulo.setFont(new Font("sansSerif", Font.PLAIN, 25));
		titulo.setForeground(Color.black);
		encabezado.add(titulo, BorderLayout.CENTER);
		encabezado.setSize(850, 70);
		
		cuerpo = new JPanel();
		cuerpo.setLayout(new GridBagLayout());
		
		this.lbl1 = new JLabel(lbl1);
		this.lbl1.setFont(new Font("sansSerif", Font.PLAIN, 25));
		this.lbl2 = new JLabel(lbl2);
		this.lbl2.setFont(new Font("sansSerif", Font.PLAIN, 25));
		this.lbl3 = new JLabel(lbl3);
		this.lbl3.setFont(new Font("sansSerif", Font.PLAIN, 25));
		
		tf1 = new JTextField();
		tf1.setPreferredSize(new Dimension(500, 25));
		tf2 = new JTextField();
		tf2.setPreferredSize(new Dimension(500, 25));
		tf3 = new JTextField();
		tf3.setPreferredSize(new Dimension(500, 25));
		
		GridBagConstraints s = new GridBagConstraints();
		s.fill = GridBagConstraints.BOTH;

		s.gridx=0;
		s.gridy=0;
		cuerpo.add(this.lbl1, s);
		
		s.gridx=1;
		s.gridy=0;
		cuerpo.add(tf1, s);
		
		s.gridx=0;
		s.gridy=1;
		cuerpo.add(this.lbl2, s);
		
		s.gridx=1;
		s.gridy=1;
		cuerpo.add(tf2, s);
		
		s.gridx=0;
		s.gridy=2;
		cuerpo.add(this.lbl3, s);
		
		s.gridx=1;
		s.gridy=2;
		cuerpo.add(tf3, s);
		
		add(encabezado, BorderLayout.NORTH);
		add(cuerpo, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(850, 550));
		this.setVisible(false);
		
	}
	public String getTxt1() {
		return this.tf1.getText();
	}
	public String getTxt2() {
		return this.tf2.getText();
	}
	public String getTxt3() {
		return this.tf3.getText();
	}


}
