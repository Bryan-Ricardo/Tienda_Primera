import java.util.Scanner;

public class Cuenta {
    Scanner usuario = new Scanner(System.in);
    private String nombre;
    private String password;
    Cuenta(String usuario, String password){
        this.nombre = usuario;
        this.password = password;
        System.out.println("Bienvenido " + this.usuario+ "estas en tu perfil");
        System.out.println("¿Que quieres realizar?");
        System.out.println("1-Ver los productos");
        System.out.println("2-Ver tu carrito de compras");//En el carrito vendra la facturacion
        System.out.println("3-Configurar tu cuenta");
        int eleccion = this.usuario.nextInt();
        if (eleccion == 1){
            Productos productos = new Productos(this.nombre,this.password);
        }else if (eleccion ==2){

        }else if (eleccion == 3){

        }
    }
}
