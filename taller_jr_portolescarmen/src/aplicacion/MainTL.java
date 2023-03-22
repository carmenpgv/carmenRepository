package aplicacion;

import javax.swing.SwingUtilities;

public class MainTL {

	public static void main(String[] args) {
		// MAIN TALLER LAYOUT

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Marco marco = new Marco();
				marco.setVisible(true);

			}

		}

		);

	}

}
