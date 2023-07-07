package uniandes.cupi2.nonogramas.interfaz;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.nonogramas.mundo.Tablero;

/**
 * Ventana principal de la aplicación.
 */
public class InterfazNonograma extends JFrame{
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Clase principal del mundo
	 */
	private Tablero tablero;
	
	/**
	 * Panel con la imagen del titulo
	 */
	private PanelImagenTitulo panelImagenTitulo;
	
	/**
	 * Panel con la imagen del costado derecho
	 */
	private PanelImagenDerecha panelImagenDerecha;
	
	/**
	 * Panel con la imagen del costado izquierdo.
	 */
	private PanelImagenIzquierda panelImagenIzquierda;
	
	/**
	 * Panel con las opciones del costado inferior
	 */
	private PanelOpciones panelOpciones;
	
	/**
	 * Panel con el tablero donde se realizará el juego.
	 */
	private PanelTablero panelTablero;
	
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
	
	/**
	 * Constructor de la interfaz
	 */
	public InterfazNonograma() {
		
		tablero = new Tablero(this);
		
		getContentPane().setLayout(new BorderLayout());
		setSize(840,710);
		setResizable(false);
		setTitle("Nonogramas");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelImagenTitulo = new PanelImagenTitulo();
		add(panelImagenTitulo, BorderLayout.NORTH);
		
		panelImagenDerecha = new PanelImagenDerecha();
		add(panelImagenDerecha, BorderLayout.EAST);
		
		panelImagenIzquierda = new PanelImagenIzquierda();
		add(panelImagenIzquierda, BorderLayout.WEST);
		
		panelOpciones = new PanelOpciones(this);
		add(panelOpciones, BorderLayout.SOUTH);
		
		panelTablero = new PanelTablero(this);
		add(panelTablero, BorderLayout.CENTER);
	}
	
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	/**
	 * Consulta si hay un archivo seleccionado
	 * @return True si hay un archivo .properties seleccionado, false de lo contrario.
	 */
	public boolean hayUnArchivoSeleccionadoTablero() {
		return panelTablero.hayUnArchivoSeleccionado();
	}
	
	/**
	 * Devuelve las respuestas del juego en curso en forma de matriz por medio
	 * de numeros "0" y "1", "1" para casillas que deberian estar seleccionadas y 
	 * "0" para las casillas que deberian estar vacias.
	 * @return Matriz 5x5 con las respuestas del tablero dadas en "0" y "1".
	 */
	public String[][] darRespuestasTablero() {
		return tablero.darListaRespuestas(panelTablero.darRespuestasTablero());
	}
	
	/**
	 * Devuelve el nombre del juego en curso
	 * @return Nombre del juego en curso
	 */
	public String darNombreProblema() {
		return panelTablero.darNombreProblema();
	}
	
	/**
	 * Devuelve la matriz del jugador dada en numeros "0" y "1", "0" para
	 * las casillas que estan en blanco y "1" para las casillas que
	 * estan seleccionadas.
	 * @return Matriz 5x5 del jugador dada en "0" y "1".
	 */
	public String[][] darMatrizJugador(){
		return tablero.crearMatrizComparar(panelTablero.darMatrizJugador());
	}
	
	/**
	 * Calcula el numero de casillas correctas por fila.
	 */
	public void calcularCorrectasPorFila() {
		tablero.calcularCorrectasFila();
	}
	
	/**
	 * Calcula el numero de casillas correctas por columna.
	 */
	public void calcularCorrectasPorColumna() {
		tablero.calcularCorrectasColumna();
	}
	
	/**
	 * Llena las pistas de las columnas del juego actual en el tablero.
	 * @param lColumn Lista de 5 posiciones (0-4) con las pistas de las
	 * columnas del juego actual.
	 * @param matriz Matriz de botones (JButton) del Jugador.
	 */
	public void llenarPistasColumnas(String[] lColumn, JButton[][] matriz) {
		tablero.llenarPistasColumnas(lColumn, matriz);
	}
	
	/**
	 * Llena las pistas de las filas del juego actual en el tablero.
	 * @param lFilas Lista de 5 posiciones (0-4) con las pistas de las filas
	 * del juego actual.
	 * @param matriz Matriz de botones (JButton) del jugador.
	 */
	public void llenarPistasFilas(String[] lFilas, JButton[][] matriz) {
		tablero.llenarPistasFilas(lFilas, matriz);
	}
	
	/**
	 * Compara las matrices de respuestas y del jugador, si son iguales
	 * envia un mensaje notificando al usuario que ganó el juego.
	 */
	public void compararMatrices() {
		tablero.compararMatrices(darRespuestasTablero(),darMatrizJugador());
	}
	
    /**
     * Método para la extensión 1.
     */
	public void reqFuncOpcion1() {
		
		String devolver = tablero.metodo1();
		JOptionPane.showMessageDialog(this, devolver, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
	}
	
    /**
     * Método para la extensión 2.
     */
	public void reqFuncOpcion2() {
		
		String devolver = tablero.metodo2();
		JOptionPane.showMessageDialog(this, devolver, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
	}
	
    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz.
     * @param args. Argumentos del llamado. No se requiere ninguno.
     */
	public static void main(String[] args) {
		
		InterfazNonograma ventana = new InterfazNonograma();
		ventana.setVisible(true);
	}
}
