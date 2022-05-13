package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class dbConnection {
	
	static private Connection conn = null;
	
	static private String user = "root";
	static private String password = "admin";

	
	public static Connection openConn()
	{

	try {

		String url = "jdbc:mysql://localhost/javamarket?user=" + user + "&password=" + password;
		conn = DriverManager.getConnection(url);

		}
	catch (SQLException e) 
	{
		System.out.println(
				"Error en la conexión, verifique, su usuario y password o el nombre de la base a la que intenta conectarse");
		e.printStackTrace();
	}
	finally 
	{
		return conn;
	}
	}
	public static void closeConn(Connection conn)
	{
		   try{
		        conn.close();
		    }catch(SQLException ex){

		    }
	}

	}


	
