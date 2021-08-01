public class Cuenta {
    private String usuario;
    private String password;
    Cuenta(String usuario, String password){
        this.usuario = usuario;
        this.password = password;
        System.out.println("Bienvenido " + this.usuario+ "estas en tu perfil");
        System.out.println("¿Que quieres realizar?");
        System.out.println("1-Ver los productos");
        System.out.println("2-Ver tu carrito de compras");//En el carrito vendra la facturacion
        System.out.println("3-Configurar tu cuenta");
    }
}
