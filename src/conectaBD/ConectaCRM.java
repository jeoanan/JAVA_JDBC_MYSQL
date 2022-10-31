 package conectaBD;
 
 import java.sql.*;

public class ConectaCRM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			//1. Crear la conexion
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/crmbd", "root", "");
			
			//2.Crear objeto de la clase statement
			
			Statement miStatement = miConexion.createStatement();
			
			//3. ejecutar la instrucion SQL
			
			ResultSet miResulset = miStatement.executeQuery("SELECT * FROM productos");
			
			//4. Recorrer el resulret
			
			while(miResulset.next()) {
				System.out.println(miResulset.getString("id") + " " + miResulset.getString("nombrearticulo") + " " + miResulset.getString("precio"));
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println("NO CONECTA!");
			
			e.printStackTrace();
		}

	}

}
