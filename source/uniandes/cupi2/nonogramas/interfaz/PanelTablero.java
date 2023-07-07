package uniandes.cupi2.nonogramas.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel en el que se desarrolla el juego.
 */
public class PanelTablero extends JPanel implements ActionListener{
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Comando opción cargar.
	 */
	private static final String CARGAR = "CARGAR";
	
	/**
	 * Comando opción reiniciar.
	 */
	private static final String REINICIAR = "REINICIAR";
	
	/**
	 * Ventana principal de la aplicación.
	 */
	private InterfazNonograma principal;
	
	/**
	 * Panel que represnta las opciones que se encuentran debajo
	 * del tablero de juego.
	 */
	private JPanel panelOpcionesCentro;
	
	/**
	 * Panel del tablero de los botones.
	 */
	private PanelCentroBoton panelCentroBoton;
	
	/**
	 * Botón cargar.
	 */
	private JButton btnCargar;
	
	/**
	 * Botón reiniciar.
	 */
	private JButton btnReiniciar;
	
	/**
	 * Archivo que elija el usuario independientemente de su extensión.
	 */
	private File archivoElegido;
	
	/**
	 * Archivo .properties que elija el usuario.
	 */
	private File archivoActual;
	
	/**
	 * Archivo del que se extraerá la información para el juego en curso.
	 */
	private Properties archivoProperties;
	
	/**
	 * Nombre del problema del juego en curso.
	 */
	private String nombreProblema;
	
	/**
	 * Lista de 5 posiciones (0-4) con las respuestas de las columnas.
	 */
	private String[] kPistasColumnas;
	
	/**
	 * Lista de 5 posiciones (0-4) con las respuestas de las filas;
	 */
	private String[] kPistasFilas;
	
	/**
	 * Lista de 5 posiciones (0-4) con las respuestas del tablero o juego en curso.
	 */
	private String[] respuestas;
	
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
	
	/**
	 * Constructor del panel.
	 */
	public PanelTablero(InterfazNonograma pPrincipal) {
		
		principal = pPrincipal;
		archivoElegido = null;
		archivoActual = null;
		archivoProperties = new Properties();
		
		nombreProblema = null;
		kPistasColumnas = new String[5];
		kPistasFilas = new String[5];
		respuestas = new String[5];
		
		setLayout(new BorderLayout());
		
		panelCentroBoton = new PanelCentroBoton(pPrincipal);
		add(panelCentroBoton,BorderLayout.CENTER);
		
		
		panelOpcionesCentro = new JPanel();
		add(panelOpcionesCentro, BorderLayout.SOUTH);
		
		panelOpcionesCentro.setLayout(new GridLayout(1,2));
		panelOpcionesCentro.setBorder(new TitledBorder("Nuevo juego"));
		
		btnCargar = new JButton("Cargar");
		btnCargar.setActionCommand(CARGAR);
		btnCargar.addActionListener(this);
		panelOpcionesCentro.add(btnCargar);
		
		btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.setActionCommand(REINICIAR);
		btnReiniciar.addActionListener(this);
		panelOpcionesCentro.add(btnReiniciar);
		
	}
	
	/**
	 * Devuelve las respuestas del tablero o juego en curso.
	 * @return Lista de respuestas del tablero o juego en curso.
	 */
	public String[] darRespuestasTablero() {
		return respuestas;
	}
	
	/**
	 * Devuelve la matriz de botones del tablero.
	 * @return Matriz de botones del tablero.
	 */
	public JButton[][] darMatrizJugador() {
		return panelCentroBoton.darMatriz();
	}
	
	/**
	 * Devuelve el nombre del problema del juego en curso.
	 * @return El nombre del problema del juego en curso.
	 */
	public String darNombreProblema() {
		return nombreProblema;
	}
	
	/**
	 * Llena las 3 listas: pistas columnas, pistas filas, respuestas tablero.
	 * @param archivo Archivo .properties del cual se va a extraer la información.
	 */
	public void llenarListasValores(Properties archivo) {
		
		int aux = 1;
		for (int i = 0; i < 5; i++) {
			
			String kPColumna = "nonograma.pistasColumna" + String.valueOf(aux);
			String kPFila = "nonograma.pistasFila" + String.valueOf(aux);
			String rFila = "nonograma.tableroFila" + String.valueOf(aux);
			
			kPistasColumnas[i] = archivo.getProperty(kPColumna);
			kPistasFilas[i] = archivo.getProperty(kPFila);
			respuestas[i] = archivo.getProperty(rFila);			aux ++;
		}
	}
	
	/**
	 * Verifica si hay un archivo .properties seleccionado.
	 * @return true en caso de haber un archivo .properties seleccionado,
	 * false de lo contrario.
	 */
	public boolean hayUnArchivoSeleccionado() {
		
		boolean devolver = false;
		if(archivoActual != null) {
			devolver = true;
		}
		return devolver;
	}
	
	/**
	 * Selecciona un archivo del proyecto.
	 */
	public void seleccionarArchivo() {
		JFileChooser fc = new JFileChooser("data");
		int respuesta = fc.showOpenDialog(principal);
		String mensaje = "";
		String titulo;
		String aComparar = "properties";
		
		if(respuesta == JFileChooser.CANCEL_OPTION) {
			mensaje = "Debe seleccionar un archivo de configuración para poder jugar.";
			titulo = "Empezar a jugar";
			JOptionPane.showMessageDialog(principal, mensaje, titulo, JOptionPane.WARNING_MESSAGE );
		}
		else if(respuesta == JFileChooser.APPROVE_OPTION) {
			archivoElegido = fc.getSelectedFile();
			String path = archivoElegido.getPath();
			
			String extension = "";
			int i = path.lastIndexOf('.');
			extension = path.substring(i+1);
			
			if(!extension.equals(aComparar)) {
				mensaje = "El archivo no tiene el formato esperado.";
				titulo = "Error al cargar el juego";
				JOptionPane.showMessageDialog(principal, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
				
				if(!hayUnArchivoSeleccionado()) {
					archivoElegido = null;
				}
			}
			else {
				archivoActual = archivoElegido;
				try {
					archivoProperties.load(new FileReader(archivoActual));
					nombreProblema = archivoProperties.getProperty("nonograma.nombreProblema");
					llenarListasValores(archivoProperties);
					principal.llenarPistasColumnas(kPistasColumnas,panelCentroBoton.darMatriz());
					principal.llenarPistasFilas(kPistasFilas,panelCentroBoton.darMatriz());
				} catch (IOException e) {
					e.printStackTrace();
				}
				panelCentroBoton.habilitarBotones();
			}
		}
	}
	
    /**
     * Manejo de los eventos de los botones.
     * @param e Evento asociado al click en un botón. e != null.
     */
	public void actionPerformed(ActionEvent e) {
		
		if(CARGAR.equals(e.getActionCommand())) {
			seleccionarArchivo();
		}
		else if(REINICIAR.equals(e.getActionCommand())) {
			
			if(archivoElegido == null) {
				String mensaje = "No hay ningún juego en curso.";
				String titulo = "Reiniciar el juego";
				JOptionPane.showMessageDialog(principal, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
			else {
				panelCentroBoton.fBtnReiniciar();
			}
		}	
	}
}
