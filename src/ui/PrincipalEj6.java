package ui;

import java.util.*;
import entities.Product;
import logic.productData;



public class PrincipalEj6 {

	public static void main(String[] args) {
		
		
		
		final Scanner lector = new Scanner(System.in);
		
		boolean salir = false;
		int opcion;
		
		try {
		while(!salir) 
			{
				
				System.out.println("1-Listar");
				System.out.println("2-Buscar");
				System.out.println("3-Nuevo");
				System.out.println("4-Borrar");
				System.out.println("5-Actualizar");
				System.out.println("6-Salir");
				
				System.out.print("Ingresa un numero: ");
				opcion = Integer.parseInt(lector.nextLine());
				System.out.println();
				
				switch(opcion)
				{
				case 1: productData.recuperarTodos();
						break;
				case 2: 
				{
					System.out.print("Ingrese el id del producto a recuperar: ");
					int id = Integer.parseInt(lector.nextLine());
					
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
					break;
				}
				case 3:
				{	
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
					break;
				}
				case 4: 
					{
						Product prod = new Product();
						System.out.print("Ingrese la id del producto a eliminar: ");
						prod.setId(Integer.parseInt(lector.nextLine()));
						productData.borrarProducto(prod);
						break;
					}
				case 5: 
					Product prod = new Product();
					System.out.print("Ingrese la id del producto a modificar: ");
					prod.setId(Integer.parseInt(lector.nextLine()));
					productData.cargaDatos(prod);
					productData.actualizaProducto(prod);
					break;
				case 6: salir = true;
				lector.close();
						break;
				default: System.out.println("Las opciones son entre 1 y 6");
				}
				
				
			}
		}catch(InputMismatchException e) {
			System.out.println("Debe ser un numero");
			lector.next();
		}
		
	
		

		//3)New
		/*System.out.println("Ingrese la informacion del nuevo producto: ");
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
		 */
	}

}
