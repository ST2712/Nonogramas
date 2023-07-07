package uniandes.cupi2.nonogramas.interfaz;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Panel con la imagen del costado norte de la interfaz.
 */
public class PanelImagenTitulo extends JPanel{
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1L;
	
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
	
	/**
	 * Constructor del panel.
	 */
	public PanelImagenTitulo() {
		
		ImageIcon icono = new ImageIcon("data/imagenes/titulo.jpg");
		setSize(icono.getIconHeight(), icono.getIconWidth());
		
		JLabel imagen = new JLabel("");
		imagen.setIcon(icono);
		add(imagen);
		
		setBackground(Color.WHITE);
		imagen.setBorder(new LineBorder(Color.GRAY));
	}
}
