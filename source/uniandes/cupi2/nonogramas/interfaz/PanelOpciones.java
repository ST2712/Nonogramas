package uniandes.cupi2.nonogramas.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de opciones que se encuentra al costado inferior.
 */
public class PanelOpciones extends JPanel implements ActionListener{
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Comando opción correctas por fila.
	 */
	private static final String CORRECTAS_POR_FILA = "CORRECTAS_POR_FILA";
	
	/**
	 * Comando opción correctas por columna.
	 */
	private static final String CORRECTAS_POR_COLUMNA = "CORRECTAS_POR_COLUMNA";
	
	/**
	 * Comando opción 1.
	 */
    private static final String OPCION_1 = "OPCION_1";
    
    /**
     * Comando opción 2.
     */
    private static final String OPCION_2 = "OPCION_2";
	
    /**
     * Ventana principal de la aplicación.
     */
	private InterfazNonograma principal;
	
	/**
	 * Botón correctas por fila.
	 */
	private JButton btnCorrectasPorFila;
	
	/**
	 * Botón correctas por columna.
	 */
	private JButton btnCorrectasPorColumna;
	
	/**
	 * Botón opción 1.
	 */
	private JButton btnOpcion1;
	
	/**
	 * Botón opción 2.
	 */
	private JButton btnOpcion2;
	
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
	
	/**
	 * Constructor del panel.
	 */
	public PanelOpciones(InterfazNonograma pPrincipal) {
		
		principal = pPrincipal;
		
		setBorder(new TitledBorder("Opciones"));
		setLayout(new GridLayout(1, 4));
		
		btnCorrectasPorFila = new JButton("Correctas por fila");
		btnCorrectasPorFila.setActionCommand(CORRECTAS_POR_FILA);
		btnCorrectasPorFila.addActionListener(this);
		add(btnCorrectasPorFila);
		
		btnCorrectasPorColumna = new JButton("Correctas por columna");
		btnCorrectasPorColumna.setActionCommand(CORRECTAS_POR_COLUMNA);
		btnCorrectasPorColumna.addActionListener(this);
		add(btnCorrectasPorColumna);
		
        btnOpcion1 = new JButton( "Opción 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener(this);
        btnOpcion1.setEnabled(true);
        add( btnOpcion1 );

        btnOpcion2 = new JButton( "Opción 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener(this);
        add( btnOpcion2 );
		
	}
	
    /**
     * Manejo de los eventos de los botones.
     * @param e Evento asociado al click en un botón. e != null.
     */
    public void actionPerformed( ActionEvent e ) {
        
        if(CORRECTAS_POR_FILA.equals(e.getActionCommand())) {
        	
        	if(!principal.hayUnArchivoSeleccionadoTablero()) {
        		
        		String mensaje = "No hay ningún juego en curso.";
        		String titulo = "Casillas correctas por fila";
        		JOptionPane.showMessageDialog(principal, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
        	}
        	else{
        		principal.calcularCorrectasPorFila();
        	}
        }
        else if(CORRECTAS_POR_COLUMNA.equals(e.getActionCommand())) {
        	
        	if(!principal.hayUnArchivoSeleccionadoTablero()) {
        		String mensaje = "No hay ningún juego en curso.";
            	String titulo = "Casillas correctas por columna";
            	JOptionPane.showMessageDialog(principal, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
        	}
        	else {
        		principal.calcularCorrectasPorColumna();
        	}
        }
        else if(OPCION_1.equals(e.getActionCommand())) {
        	
        	principal.reqFuncOpcion1();
        }
        else if(OPCION_2.equals(e.getActionCommand())) {
        	
        	principal.reqFuncOpcion2();
        }
    }
}
