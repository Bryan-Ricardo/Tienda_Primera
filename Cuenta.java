import java.util.Scanner;

public class Cuenta {
    Scanner usuario = new Scanner(System.in);
    private String nombre;
    private String password;
    private int idUsuario;
    Cuenta(String usuario, String password,int idUsuario){
        this.nombre = usuario;
        this.password = password;
        this.idUsuario = idUsuario;
        System.out.println("Bienvenido " + this.usuario+ "estas en tu perfil");
        System.out.println("¿Que quieres realizar?");
        System.out.println("1-Ver los productos");
        System.out.println("2-Ver tu carrito de compras");//En el carrito vendra la facturacion
        System.out.println("3-Configurar tu cuenta");
        int eleccion = this.usuario.nextInt();
        if (eleccion == 1){
            Productos productos = new Productos(this.nombre,this.password,idUsuario);
        }else if (eleccion ==2){
            CarritoCompras carritoCompras = new CarritoCompras(this.nombre,this.password,idUsuario);
        }else if (eleccion == 3){
        }
    }
}
