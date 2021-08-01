import java.sql.Array;
import java.sql.ResultSet;
import java.util.Scanner;

public class Productos {
    private Scanner usuario = new Scanner(System.in);
    private ConexionPostgresql conexionPostgresql;
    private ResultSet rs = null;
    Productos(String usuario, String password){
        try {


            System.out.println("Bienvenido al almacen, ¿EN que categoria esta el producto que buscas?");
            System.out.println("1-Alimentos\n2-Utencilios Higienicos\n3-Utensilios Varios\n4-Bebidas");
            int eleccion = this.usuario.nextInt();
            if (eleccion == 1) {
                this.alimentos();
                System.out.println("¿Que quieres hacer ahora?");
                System.out.println("1-Volver a ver los productos");
                System.out.println("2-Ir a mi cuenta ");
                System.out.println("3-Ingresar el id de un producto para agregarlo al carrito");
            } else if (eleccion == 2) {

            } else if (eleccion == 3) {

            } else if (eleccion == 4) {

            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void alimentos()throws Exception{
        //AL
        //Creo la sentencia sql y la envio para obtener la cantidad de productos
        this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='AL' ;");
        this.rs = this.conexionPostgresql.getRs();
        int longitud = 0;
        while (this.rs.next()){
            longitud++;
            //nombresProductos[idProducto] = this.rs.getString("nombreProducto");
            //preciosProductos[idProducto] = this.rs.getInt("precio");
        }
        int idProducto = 0;
        String nombresProductos[] = new String[longitud];
        int preciosProductos[] = new int[longitud];
        for (int i = 0; i < longitud; i++){
            idProducto++;
            //Creo la sentencia sql y la envio para obtener la cantidad de productos
            this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='AL' AND idProducto='" + idProducto + "';" );
            this.rs = this.conexionPostgresql.getRs();
            //Obtengo todos los nombres
            while (this.rs.next()){
                nombresProductos[i] = rs.getString("nombreProducto");
                preciosProductos[i] = rs.getInt("precio");
            }
            //Muestro los Productos
            System.out.println("Id: "+ idProducto+"Nombre: " + nombresProductos[i] + "\nPrecio: $" + preciosProductos[i] + "\n/////////////");
            //
        }
    }
}
