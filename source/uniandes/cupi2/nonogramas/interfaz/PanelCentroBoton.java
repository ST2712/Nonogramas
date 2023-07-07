package uniandes.cupi2.nonogramas.interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel que contiene el tablero de juego y los botones de opción del mismo.
 */
public class PanelCentroBoton extends JPanel implements ActionListener{
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constante que determina la cantidad de filas de la matriz del tablero.
	 */
	private static final int numFilas = 7;
	
	/**
	 * Constante que determina la cantidad de columnas de la matriz del tablero.
	 */
	private static final int numColumnas = 7;
	
	/**
	 * Ventana principal de la aplicación.
	 */
	private InterfazNonograma principal;
	
	/**
	 * Matriz de botones del tablero de juego.
	 */
	private JButton matriz[][];
	
	/**
	 * Array de botones del tablero de juego.
	 */
	private ArrayList<JButton> arrayBotones;
	
	/**
	 * Ruta de la imagen a usar para los botones.
	 */
	private String rutaImagen;
	
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
	
	/**
	 * Constructor del panel.
	 */
	public PanelCentroBoton(InterfazNonograma pPrincipal) {
		
		principal = pPrincipal;
		matriz = new JButton[numFilas][numColumnas];
		arrayBotones = new ArrayList<>();
		rutaImagen = "data/imagenes/casilla_blanco.jpg";
		ImageIcon icono = new ImageIcon(rutaImagen);
		icono = new ImageIcon(icono.getImage().getScaledInstance(56, 50, Image.SCALE_DEFAULT));
		
		setLayout(new GridLayout(7,7));
		setBorder(new TitledBorder("Tablero juego"));
		
		int numBotones = 0;
		for (int i = 0; i < numFilas; i++) {
			
			for (int j = 0; j < numColumnas; j++) {
				JButton boton = new JButton();
				boton.setActionCommand(String.valueOf(numBotones));
				boton.addActionListener(this);
				boton.setIcon(icono);
				boton.setBackground(Color.LIGHT_GRAY);
				boton.setEnabled(false);
				matriz[i][j] = boton;
				arrayBotones.add(boton);
				add(boton);
				
				numBotones++;
			}
			
		}
		
	}
	
	/**
	 * Devuelve la matriz de botones del tablero de juego.
	 * @return La matriz de botones del tablero de juego.
	 */
	public JButton[][] darMatriz() {
		return matriz;
	}
	
	/**
	 * Habilita los botones para que el usuario pueda comenzar a jugar
	 * creando una matriz clickeable de 5x5.
	 */
	public void habilitarBotones() {

		for (int i = 2; i < 7; i++) {
			
			for (int j = 2; j < 7; j++) {
				matriz[i][j].setEnabled(true);
				establecerCasillaBlanca(matriz[i][j]);
				matriz[i][j].setBackground(Color.WHITE);
			}
		}
	}
	
	/**
	 * Estbalece al boton especificado por parametro un fondo blanco, además de
	 * insertar la imagen de casilla_blanco.jpg
	 * @param boton Boton al que se le quiere establecer el fondo e imagen en blanco.
	 */
	public void establecerCasillaBlanca(JButton boton) {
		
		rutaImagen = "data/imagenes/casilla_blanco.jpg";
		ImageIcon icono = new ImageIcon(rutaImagen);
		icono = new ImageIcon(icono.getImage().getScaledInstance(56, 50, Image.SCALE_DEFAULT));
		boton.setIcon(icono);
		boton.setBackground(Color.WHITE);
	}
	
	/**
	 * Establece al boton especificado por parametro un fondo negro, además de 
	 * insetar la imagen de casilla_rellena.png
	 * @param boton Boton al que se le quiere establecer el fondo negro y la imagen
	 * de cupi2.
	 */
	public void establecerCasillaRellena(JButton boton) {
		
		rutaImagen = "data/imagenes/casilla_rellena.png";
		ImageIcon icono = new ImageIcon(rutaImagen);
		icono = new ImageIcon(icono.getImage().getScaledInstance(56, 50, Image.SCALE_DEFAULT));
		boton.setIcon(icono);
		boton.setBackground(Color.BLACK);
	}
	
	/**
	 * Cambia la imagen del boton especificado por parametro, si se encuentra
	 * con la imagen de casilla blanca, cambia a casilla rellena y viceversa.
	 * @param boton Boton al que se le quiere cambiar la imagen.
	 */
	public void cambiarImagenBoton(JButton boton) {
		
		if(boton.getBackground() == Color.WHITE) {
			establecerCasillaRellena(boton);
		}
		else if(boton.getBackground() == Color.BLACK) {
			establecerCasillaBlanca(boton);
		}
	}
	
	/**
	 * Metodo que "refresca" o devuelve a su estado inicial los botones
	 * del tablero de juego
	 */
	public void fBtnReiniciar() {
		
		for (int i = 2; i < 7; i++) {
			
			for (int j = 2; j < 7; j++) {
				JButton boton = matriz[i][j];
				establecerCasillaBlanca(boton);
			}
		}
	}
	
    /**
     * Manejo de los eventos de los botones.
     * @param e Evento asociado al click en un botón. e != null.
     */
	public void actionPerformed(ActionEvent e) {

		boolean terminoBotones = false;
		for (int i = 0; i < arrayBotones.size() && !terminoBotones; i++) {
			if(String.valueOf(i).equals(e.getActionCommand())) {
				cambiarImagenBoton(arrayBotones.get(i));
				principal.compararMatrices();
				terminoBotones = true;
			}
		}
	}
}
