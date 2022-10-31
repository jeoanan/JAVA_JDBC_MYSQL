package conectaBD;

import java.sql.*;

public class ConsultasPreparadas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			//1. Crear conexion
			Connection miConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crmbd", "root", "");
			
			//2.Preparar la consulta con prepareStatement
			PreparedStatement miConsultapreparada = miConnection.prepareStatement("SELECT nombrearticulo,seccion,paisorigen FROM PRODUCTOS WHERE seccion=? AND paisorigen=?");
			
			//3. establecer parametros de consulta
			miConsultapreparada.setString(1, "ropa deportiva");
			miConsultapreparada.setString(2, "colombia");
			
			//4. ejecutar y recorrer consulta
			ResultSet rs = miConsultapreparada.executeQuery();
			
			
			while(rs.next()) {
				System.out.println(rs.getString("seccion") + " " + rs.getString("nombrearticulo") + " " + rs.getString("paisorigen"));
			}
			
			rs.close();
			
			//Reutilizar consulta SQL
			System.out.println("");
			System.out.println("EJECUCION SEGUNDA CONSULTA");
			System.out.println("");
			
			miConsultapreparada.setString(1, "computadoras");
			miConsultapreparada.setString(2, "usa");
			
			//4. ejecutar y recorrer consulta
			rs = miConsultapreparada.executeQuery();
			
			
			while(rs.next()) {
				System.out.println(rs.getString("seccion") + " " + rs.getString("nombrearticulo") + " " + rs.getString("paisorigen"));
			}
			
			rs.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
