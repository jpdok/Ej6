package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import data.dbConnection;

import java.util.*;


import entities.Product;

public class productData {
	
	static Connection conn = null;

	public static void recuperarTodos() {
		
		LinkedList<Product> productos = new LinkedList<>();
		try {
			// crear una conexión
			conn = dbConnection.openConn();
		       //DriverManager.getConnection("jdbc:mysql://localhost/javamarket","root","admin");
			 //DriverManager.getConnection("jdbc:mysql://localhost/javaTest?user=java&password=himitsu");

			// ejecutar la query
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery("select * from product");

            // mapear de resultset a objeto
            while(rs.next()) {
            	Product p=new Product();

                p.setId(rs.getInt("idProduct"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setShippingIncluded(rs.getBoolean("shippingIncluded"));

                productos.add(p);

            }
            //cerrar recursos
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}

		    dbConnection.closeConn(conn);
		    
		    // mostrar info
		    System.out.println("Listado Completo");
		    System.out.println(productos);
		    System.out.println();System.out.println();

		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public static Product recuperarProducto(Product prod) {
		Product p = prod;
		PreparedStatement stmt=null;
		Connection conn=null;
		try {
			conn = dbConnection.openConn();


            stmt = conn.prepareStatement("select * from product where idProduct=?");
            
            stmt.setInt(1, p.getId());

          
            ResultSet rs= stmt.executeQuery();


            if(rs.next()) {
                p.setId(rs.getInt("idProduct"));
                p.setStock(rs.getInt("stock"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setShippingIncluded(rs.getBoolean("shippingIncluded"));
            }

            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}

		    conn.close();
		    
		    return p;

		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return p;
	}

	public static void insertarNuevo(String name,String description,double price,int stock,boolean includedShipping) {
		
		Product p = new Product(name,description,price,stock,includedShipping);
		
		try {
			// crear una conexión
			conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/javamarket","root","admin");

			// definir la query
            PreparedStatement pstmt = conn.prepareStatement(
            		"insert into product(name,description,price,stock,shippingIncluded) values (?,?,?,?,?)"
            		,PreparedStatement.RETURN_GENERATED_KEYS
            		);
            
            
            pstmt.setString(1, p.getName());
            pstmt.setString(2, p.getDescription());
            pstmt.setDouble(3, p.getPrice());
            pstmt.setInt(4, p.getStock());
            pstmt.setBoolean(5, p.getShippingIncluded());
            
            pstmt.executeUpdate();
            
            ResultSet keyResultSet=pstmt.getGeneratedKeys();

            if(keyResultSet!=null && keyResultSet.next()) {
                    int id=keyResultSet.getInt(1);
                    System.out.println("ID: "+id);
                    p.setId(id);
            }


            if(keyResultSet!=null){keyResultSet.close();}
            if(pstmt!=null){pstmt.close();}

		    conn.close();
		    
		    // mostrar objeto
		    System.out.println("Nuevo Producto:");
		    System.out.println(p);
		    System.out.println();System.out.println();

		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public static void borrarProducto(Product prod) {
		Product p = prod;
		PreparedStatement stmt=null;
		Connection conn=null;
		try {
			conn = dbConnection.openConn();

			stmt = conn.prepareStatement("delete from product where idProduct=?");
            
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();
            
            if(stmt!=null) {stmt.close();}

			dbConnection.closeConn(conn);

		} 
		catch (SQLException ex) {
		    // Manejo de errores
		    ex.printStackTrace();
		} 

		}
	
	public static void actualizaProducto(Product prod) {
		PreparedStatement stmt =null;
		Connection conn = null;
		
		try {
			conn=dbConnection.openConn();
			stmt = conn.prepareStatement(
					"update product "+
					"set name = ?, description = ?, price = ?, stock = ?, shippingIncluded = ? "+
					"where idProduct = ?");
			stmt.setString(1, prod.getName());
			stmt.setString(2, prod.getDescription());
			stmt.setDouble(3, prod.getPrice());
			stmt.setInt(4, prod.getStock());
			stmt.setBoolean(5, prod.getShippingIncluded());
			stmt.setInt(6, prod.getId());
			
			stmt.executeUpdate();
			
			if(stmt!=null)stmt.close();
			dbConnection.closeConn(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void cargaDatos(Product prod)
	{	
		Scanner lector = new Scanner(System.in);
		System.out.println("Ingrese los datos del producto: ");
		System.out.print("Name: ");
		String name = lector.nextLine();
		prod.setName(name);
		System.out.print("Description: ");
		String description = lector.nextLine();
		prod.setDescription(description);
		System.out.print("Price: ");
		double price = Double.parseDouble(lector.nextLine());
		prod.setPrice(price);
		System.out.print("Stock: ");
		int stock = Integer.parseInt(lector.nextLine());
		prod.setStock(stock);
		System.out.print("includedShipping (True/False): ");
		boolean shippingIncluded = Boolean.parseBoolean(lector.nextLine());
		prod.setShippingIncluded(shippingIncluded);
		lector.close();
	}
}


