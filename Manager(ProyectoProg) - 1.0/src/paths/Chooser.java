package paths;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

//import paths.Chooser;


//Ventana en la que elijes que app/directorio quieres abrir
public class Chooser extends javax.swing.JFrame {

	public Chooser() {
		initComponents();
		this.setLocationRelativeTo(null);
	}

	//Variables
	private javax.swing.JButton aniadirPrograma;
	private javax.swing.JButton bAceptar;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JTextField ruta;

	//Componentes
	private void initComponents() {
		/**
		 * Inicializar ventana, creación de la ventana, Layout	
		 */
		
		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		ruta = new javax.swing.JTextField();
		aniadirPrograma = new javax.swing.JButton();
		bAceptar = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(new java.awt.Rectangle(1280, 720, 1280, 720));

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		aniadirPrograma.setText("Añadir Ruta");
		aniadirPrograma.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				aniadirProgramaActionPerformed(evt);
			}
		});
		
		bAceptar.setText("Aceptar");
        bAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAceptarActionPerformed(evt);
            }
        });

	}
	

	//actionListeners
	private void aniadirProgramaActionPerformed(java.awt.event.ActionEvent evt) {                                                
		JFileChooser jf = new JFileChooser();
		jf.showOpenDialog(this);
		File archivo = jf .getSelectedFile();
		if(archivo != null){
			ruta.setText(archivo.getAbsolutePath());
		}
	}
	
	private void bAceptarActionPerformed(java.awt.event.ActionEvent evt) {                                         
       
   }

	//Main
	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Chooser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Chooser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Chooser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Chooser.class.getName()).log(Level.SEVERE, null, ex);
                }
                new Chooser().setVisible(true);
            }
        });


	}

}
