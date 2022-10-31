package conectaBD;

import java.sql.*;

public class ModificarBBDD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
try {
			
			//1. Crear la conexion
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/crmbd", "root", "");
			
			//2.Crear objeto de la clase statement		
			Statement miStatement = miConexion.createStatement();
			
			//INSERTAR
			String instruccionSQL = "INSERT INTO PRODUCTOS(seccion,nombrearticulo,precio,importado,fecha,paisorigen,foto) VALUES ('celulares','xiaomi redmi 11 C pro',1100000,1,'2022-10-28','china','null')";
			
			//ACTUALIZAR
			String actualizarSQL = "UPDATE PRODUCTOS SET precio = 800000 WHERE id=8";
			
			//Eliminar
			//String eliminarSQL = "DELETE FROM PRODUCTOS WHERE id=8";
			
			//miStatement.executeUpdate(instruccionSQL);
			miStatement.executeUpdate(actualizarSQL);
			//miStatement.executeUpdate(eliminarSQL);
			
			//System.out.println("Datos insertados correctamente");
			System.out.println("Datos actualizados correctamente");
			//System.out.println("Datos eliminados correctamente");
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println("NO CONECTA!");
			
			e.printStackTrace();
		}

	}

}
