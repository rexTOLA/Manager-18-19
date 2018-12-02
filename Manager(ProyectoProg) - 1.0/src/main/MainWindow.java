package main;

import javax.swing.*;

import database.*;
import paths.*;
import windowTypes.*;

import java.awt.*;
import java.io.*;
import java.sql.ResultSet;
import java.util.List;

/**
 * Clase para la ventana principal con todos los componentes necesarios
 * Paneles para los botones
 * Paneles para las listas de programas y carpetas
 * 
 * Todo ello para visualizar lo que el usuario necesita ver
 * @param args
 */

public class MainWindow extends JFrame {
	static DefaultListModel listModelApp = new DefaultListModel();
	JList listaApp = new JList(listModelApp);
	static DefaultListModel listModelRuta = new DefaultListModel();
	JList listaRuta = new JList(listModelRuta);

	private static DataBase DB = new DataBase();
	private static List<Programa> programas;

	public MainWindow() {
		//Creacion de la ventana
		javax.swing.border.Border compound, raisedbevel, loweredbevel;
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);	//lineas de separaci�n
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(475, 850);
		setResizable(false);
		setLocationRelativeTo(null);

		//Componentes y Contenedores
		JButton bAniadirAPP = new JButton("Añadir Programa");		//Boton para a�adir APP
		JButton bEjecutarApp = new JButton("Ejecutar");				//Boton para ejecutar App
		JButton bEliminarApp = new JButton("Eliminar");				//Boton para eliminar App
		JButton bAniadirRuta = new JButton("Añadir Ruta");			//Boton para a�adir Ruta
		JButton bEjecutarRuta = new JButton("Ejecutar");			//Boton para ejecutar Ruta
		JButton bEliminarRuta = new JButton("Eliminar");			//Boton para eliminar Ruta

		JPanel pBotonera1 = new JPanel();							//Panel para el boton
		JPanel pBotonera2 = new JPanel();
		JPanel pListaApp = new JPanel(new BorderLayout());				//Panel para la lista de programas
		JPanel pListaRuta = new JPanel(new BorderLayout());

		pListaApp.setPreferredSize(new Dimension(475, 415));			//Dimensiones del panel
		pListaApp.setBorder(compound);
		pListaRuta.setPreferredSize(new Dimension(475, 415));			//Dimensiones del panel
		pListaRuta.setBorder(compound);

		JScrollPane listaScrollerApp = new JScrollPane(listaApp);
		JScrollPane listaScrollerRuta = new JScrollPane(listaRuta);

		//Layouts
		getContentPane().setLayout(new BorderLayout());

		//Asignacion de los componentes y contenedores
		getContentPane().add(pListaApp, BorderLayout.NORTH);
		getContentPane().add(pListaRuta, BorderLayout.SOUTH);
		pBotonera1.add(bAniadirAPP);
		pBotonera1.add(bEjecutarApp);
		pBotonera1.add(bEliminarApp);
		pBotonera2.add(bAniadirRuta);
		pBotonera2.add(bEjecutarRuta);
		pBotonera2.add(bEliminarRuta);
		pListaApp.add(pBotonera1, BorderLayout.NORTH);
		pListaRuta.add(pBotonera2, BorderLayout.NORTH);
		pListaApp.add(listaScrollerApp);
		pListaRuta.add(listaScrollerRuta);

		//actionListeners
		bAniadirAPP.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bAniadirAPPActionPerformed(evt);
			}
		});

		bAniadirRuta.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bAniadirRutaActionPerformed(evt);

			}
		});

		bEjecutarApp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bEjecutarAppactionPerformed(evt, listaApp);
			}
		});

		bEliminarApp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bEliminarActionPerformedApp(evt);
			}
		});

		bEjecutarRuta.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bEjecutarRutaactionPerformed(evt, listaRuta);
			}
		});

		bEliminarRuta.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bEliminarActionPerformedRuta(evt);
			}
		});
		//Main
	}

	private void bAniadirAPPActionPerformed(java.awt.event.ActionEvent evt){
		WindowApps vr = new WindowApps(listModelApp);
		vr.main(null);
		vr.setVisible(true);

	}

	private void bAniadirRutaActionPerformed(java.awt.event.ActionEvent evt){
		WindowFolders vp = new WindowFolders(listModelRuta);
		vp.main(null);
		vp.setVisible(true);

	}

	private void bEjecutarAppactionPerformed(java.awt.event.ActionEvent evt, JList listaApp){
		Runtime r = Runtime.getRuntime();
		Process p = null;
		//		String ruta = ((Programa) listaApp.getSelectedValue()).getPath();
		//		System.out.println(ruta);
		listaApp.getModel();
		int indexApp = listaApp.getSelectedIndex();
		listModelApp.get(indexApp);

		try{
			ResultSet rs = DB.getStatement().executeQuery("SELECT * FROM PROGRAMAS WHERE NOMBRE LIKE 'APP - %'");
			while(rs.next()){
				// Leer el resultset
				System.out.println(rs.getString("NOMBRE"));

				String nombre, path;

				nombre = rs.getString("NOMBRE");
				path = rs.getString("DIRECCION");


				p = r.exec(path);				
				System.out.println(p);
			}
		}catch (Exception e) {
			System.out.println("Error al ejecutar");

		}
	}

	private void bEjecutarRutaactionPerformed(java.awt.event.ActionEvent evt, JList listaRuta){
		Runtime r = Runtime.getRuntime();
		Process p = null;
		//		String ruta = ((Programa) listaRuta.getSelectedValue()).getPath();
		//		System.out.println(ruta);

		try{
			ResultSet rs = DB.getStatement().executeQuery("SELECT * FROM PROGRAMAS WHERE NOMBRE LIKE 'CARPETA - %'");
			while(rs.next()){
				// Leer el resultset
				System.out.println(rs.getString("NOMBRE"));

				String nombre, path;

				nombre = rs.getString("NOMBRE");
				path = rs.getString("DIRECCION");


				p = r.exec(path);				
				System.out.println(p);
			}
		}catch (Exception e) {
			System.out.println("Error al ejecutar");
		}
	}

	private void bEliminarActionPerformedApp(java.awt.event.ActionEvent evt){
		listaApp.getModel(); 
		int indexApp = listaApp.getSelectedIndex();
		listModelApp.remove(indexApp);

		//String sql = " DELETE FROM PROGRAMAS WHERE NOMBRE = '"+((Programa) listaApp.getSelectedValue()).getNombre()+"');"; 

		try {
			//DB.getStatement().executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void bEliminarActionPerformedRuta(java.awt.event.ActionEvent evt){
		listaRuta.getModel(); 
		int indexRuta = listaRuta.getSelectedIndex();
		listModelRuta.remove(indexRuta);

		//String sql = " DELETE FROM PROGRAMAS WHERE NOMBRE = '"+((Programa) listaRuta.getSelectedValue()).getNombre()+"');";

		try {
			//DB.getStatement().executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void cerrarBD(java.awt.event.WindowEvent e) {
		DB.close();
		System.exit(0);
	}

	private static void aniadirPrograma() {
		try {
			ResultSet rsApp = DB.getStatement().executeQuery("SELECT * FROM PROGRAMAS WHERE NOMBRE LIKE 'APP - %'");
			while(rsApp.next()){
				// Leer el resultset
				System.out.println(rsApp.getString("NOMBRE"));

				String nombre, path;

				nombre = rsApp.getString("NOMBRE");
				path = rsApp.getString("DIRECCION");

				listModelApp.addElement(nombre);
			}

			ResultSet rsRuta = DB.getStatement().executeQuery("SELECT * FROM PROGRAMAS WHERE NOMBRE LIKE 'CARPETA - %'");
			while(rsRuta.next()){
				// Leer el resultset
				System.out.println(rsRuta.getString("NOMBRE"));

				String nombre, path;

				nombre = rsRuta.getString("NOMBRE");
				path = rsRuta.getString("DIRECCION");

				listModelRuta.addElement(nombre);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new MainWindow();
		f.setVisible(true);
		try {
			DB.initBD("C:\\Users\\Ander\\Documents\\DataBase Prog III\\AdminAppDataBase.bd");
			//DB.crearTablaBD();
			MainWindow.aniadirPrograma();
			//DB.close();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
