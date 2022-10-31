package conectaBD;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Aplicacion_Consulta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame miMarco = new MarcoAplicacion();
		
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		miMarco.setVisible(true);

	}

}

class MarcoAplicacion extends JFrame{
	
	public MarcoAplicacion() {
		
		setTitle("CONSULTA BD");
		
		setBounds(500,300,400,400);
		
		setLayout(new BorderLayout());
		
		JPanel menus = new JPanel();
		
		menus.setLayout(new FlowLayout());
		
		secciones = new JComboBox();
		
		secciones.setEditable(false);
		
		secciones.addItem("Todos");
		
		paises = new JComboBox();
		
		paises.setEditable(false);
		
		paises.addItem("Todos");
		
		resultado = new JTextArea(4,50);
		
		resultado.setEditable(false);
		
		add(resultado);
		
		menus.add(secciones);
		menus.add(paises);
		
		add(menus,BorderLayout.NORTH);
		add(resultado,BorderLayout.CENTER);
		
		JButton botonConsulta = new JButton("CONSULTA");
		
		botonConsulta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ejecutaConsulta(); 
				
			}
		});
		
		add(botonConsulta,BorderLayout.SOUTH);
		
		//---------------------CONEXION CON BD------------------------------//
		
		try {
			
			//1. Crear la conexion
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/crmbd", "root", "");
			
			//2.Crear objeto de la clase statement
			Statement miStatement = miConexion.createStatement();
			
			
			
			for(int i=0;i<2;i++) {
				String miConsulta;
				ResultSet miResulset;
				String [] variableConsulta = {"seccion","paisorigen"};
				
				
				miConsulta = "SELECT DISTINCTROW "+ variableConsulta[i] +" FROM PRODUCTOS";
				
				miResulset = miStatement.executeQuery(miConsulta);
				
				while(miResulset.next()) {
					if(i==0) {
						secciones.addItem(miResulset.getString(1));
					}else {
						paises.addItem(miResulset.getString(1));
					}
					
				}
				
				miResulset.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println("NO CONECTA!");
			
			e.printStackTrace();
		}
		
	}
	
	 private void ejecutaConsulta() {
		 
		 resultado.setText("");
		 
		 String consulta = "SELECT nombrearticulo,seccion,precio,paisorigen FROM PRODUCTOS";
		 
		 ResultSet rs = null;
		 
		 try {
			String seccion =(String) secciones.getSelectedItem();
			String pais=(String)paises.getSelectedItem();
			String [] condicionConsulta= {" WHERE ","seccion=?", "paisorigen=?"};
				
			if(!seccion.equals("Todos") || !pais.equals("Todos")) {
				if(!seccion.equals("Todos") && pais.equals("Todos")) {
					consulta=consulta+condicionConsulta[0]+condicionConsulta[1];
				}else if(seccion.equals("Todos") && !pais.equals("Todos")) {
					consulta=consulta+condicionConsulta[0]+condicionConsulta[2];
				}else {
					consulta=consulta+condicionConsulta[0]+condicionConsulta[1]+" AND "+condicionConsulta[2];
				}
			}
				
			enviaConsulta=miConexion.prepareStatement(consulta);
			
			if(!seccion.equals("Todos") || !pais.equals("Todos")) {
				if(!seccion.equals("Todos") && pais.equals("Todos")) {
					enviaConsulta.setString(1, seccion);
				}else if(seccion.equals("Todos") && !pais.equals("Todos")) {
					enviaConsulta.setString(1, pais);
				}else {
					enviaConsulta.setString(1, seccion);
					enviaConsulta.setString(2, pais);
				}
			}
							
			rs=enviaConsulta.executeQuery();
			
			while(rs.next()) {
				for(int i=1;i<5;i++) {
					resultado.append(rs.getString(i));
					if(i!=4) {
						resultado.append(", ");
					}
					
				}
				resultado.append("\n");
				
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	 }
	
	private Connection miConexion;
	private JComboBox secciones,paises;
	private JTextArea resultado;
	private PreparedStatement enviaConsulta;
	private final String consultaAmbos = "SELECT nombrearticulo,seccion,precio,paisorigen FROM PRODUCTOS WHERE seccion=? AND paisorigen=?";
	private final String consultaTodos = "SELECT nombrearticulo,seccion,precio,paisorigen FROM PRODUCTOS";
}
