package ui;

import data.productData;
import java.util.Scanner;
import entities.Product;


public class PrincipalEj6 {

	public static void main(String[] args) {
		
		final Scanner lector = new Scanner(System.in);
		//1)List
		//productData.recuperarTodos();
		
		//2)Search
		System.out.print("Ingrese el id del producto a recuperar: ");
		//Scanner lector = new Scanner(System.in);
		int id = Integer.parseInt(lector.nextLine());
		lector.close();
		Product pro = new Product();
		pro.setId(id);
		Product p = new Product();
		p=productData.recuperarProducto(pro);
		if(p.getName()!=null) 
			{
			System.out.println("Producto recuperado por ID: "+pro.getId());
		    System.out.println(p.toString());
		    System.out.println();System.out.println();
			}
		else
			{
			System.out.println("No se ha encontrado el producto con ID: "+pro.getId());
			}
		
		/*//3)New
		System.out.println("Ingrese la informacion del nuevo producto: ");
		System.out.print("Name: ");
		String name = lector.nextLine();
		System.out.print("Description: ");
		String description = lector.nextLine();
		System.out.print("Price: ");
		double price = Double.parseDouble(lector.nextLine());
		System.out.print("Stock: ");
		int stock = Integer.parseInt(lector.nextLine());
		System.out.print("includedShipping (True/False): ");
		boolean shippingIncluded = Boolean.parseBoolean(lector.nextLine());
		productData.insertarNuevo(name, description, price, stock, shippingIncluded);
		lector.close();*/
	}

}
