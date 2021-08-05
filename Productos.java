import java.sql.Array;
import java.sql.ResultSet;
import java.util.Scanner;

public class Productos {
    private Scanner usuario = new Scanner(System.in);
    private ConexionPostgresql conexionPostgresql;
    private ResultSet rs = null;

    private String nombre = "";
    private String password = "";
    private int longitudBD = 0;

    Productos(String usuario, String password){
        try {
            //llenare la longitud de la base de datos
            this.longitudBD = this.asignarLongitudBD();
            this.categorias(usuario,password);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void categorias(String nombre, String password) throws Exception{
        this.nombre = nombre;
        this.password = password;
        //EMpiezo creando unas series de preguntas para el usuario
        System.out.println("Bienvenido al almacen, ¿En que categoria esta el producto que buscas?");
        System.out.println("1-Alimentos\n2-Utencilios Higienicos\n3-Utensilios Varios\n4-Bebidas");
        int eleccion = this.usuario.nextInt();
        if (eleccion == 1) {
            this.alimentos(eleccion);
        } else if (eleccion == 2) {
            this.utenciliosHigienicos(eleccion);
        } else if (eleccion == 3) {
            this.utensiliosVarios(eleccion);
        } else if (eleccion == 4) {
            this.bebidas(eleccion);
        }
    }

    private int asignarLongitudBD() throws Exception{
        //Creo la sentencia sql y la envio para obtener la cantidad de productos
        this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen;");
        this.rs = this.conexionPostgresql.getRs();
        int longitud = 0;
        while (this.rs.next()){
            longitud++;
        }
        return longitud;
    }

    private void alimentos(int eleccion)throws Exception{
        //AL
        //Creo la sentencia sql y la envio para obtener la cantidad de productos
        this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='AL' ;");
        this.rs = this.conexionPostgresql.getRs();
        int longitud = 0;
        //Obtengo la longitud
        while (this.rs.next()){
            longitud++;
            //nombresProductos[idProducto] = this.rs.getString("nombreProducto");
            //preciosProductos[idProducto] = this.rs.getInt("precio");
        }
        int idProductos[] =new int[longitud];
        //Obtengo los Id
        for (int i = 0; i <=this.longitudBD; i++) {
            //Realizo el proceso de obtener los id
            this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='AL' AND idProducto =" + (i+1) +";");
            this.rs = this.conexionPostgresql.getRs();
            int id = 0;
            while (this.rs.next()){
                id = rs.getInt("idProducto");
            }
            if (id!=0) {
                this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='AL' AND idProducto =" + id +";");
                this.rs = this.conexionPostgresql.getRs();
                while (this.rs.next()) {
                    idProductos[i] = rs.getInt("idProducto");
                }
            }
        }
        //Obtengo el nombre y el precio
        String nombresProductos[] = new String[longitud];
        int preciosProductos[] = new int[longitud];
        for (int i = 0; i < longitud; i++){ ;
                //Creo la sentencia sql y la envio para obtener la cantidad de productos
                this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='AL' AND  idProducto = "+ idProductos[i] + ";");
                this.rs = this.conexionPostgresql.getRs();
                while (this.rs.next()) {
                    idProductos[i] = rs.getInt("idProducto");
                    nombresProductos[i] = rs.getString("nombreProducto");
                    preciosProductos[i] = rs.getInt("precio");
                }
                //Muestro los Productos
                System.out.println("Id: " + idProductos[i] + "\nNombre: " + nombresProductos[i] + "\nPrecio: $" + preciosProductos[i] + "\n/////////////");
        }
        //Realizo las acciones correspondientes
        this.acciones(eleccion);
    }
    private void utenciliosHigienicos(int eleccion) throws Exception{
        //UH
        //Creo la sentencia sql y la envio para obtener la cantidad de productos
        this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='UH';");
        this.rs = this.conexionPostgresql.getRs();
        int longitud = 0;
        //Obtengo la longitud
        while (this.rs.next()){
            longitud++;
            //nombresProductos[idProducto] = this.rs.getString("nombreProducto");
            //preciosProductos[idProducto] = this.rs.getInt("precio");
        }
        int idProductos[] =new int[longitud];
        //Obtengo los Id
        int espacio = 0;
        for (int i = 0; i <=this.longitudBD; i++) {
            //Realizo el proceso de obtener los id
            this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='UH' AND idProducto =" + (i+1) +";");
            this.rs = this.conexionPostgresql.getRs();
            int id = 0;
            while (this.rs.next()){
                id = rs.getInt("idProducto");
            }
            if (id!=0) {
                this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='UH' AND idProducto =" + id +";");
                this.rs = this.conexionPostgresql.getRs();
                while (this.rs.next()) {
                    idProductos[espacio] = rs.getInt("idProducto");
                }
                espacio++;
            }
        }
        //Obtengo el nombre y el precio
        String nombresProductos[] = new String[longitud];
        int preciosProductos[] = new int[longitud];
        for (int i = 0; i < longitud; i++){ ;
            //Creo la sentencia sql y la envio para obtener la cantidad de productos
            this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='UH' AND  idProducto = "+ idProductos[i] + ";");
            this.rs = this.conexionPostgresql.getRs();
            while (this.rs.next()) {
                idProductos[i] = rs.getInt("idProducto");
                nombresProductos[i] = rs.getString("nombreProducto");
                preciosProductos[i] = rs.getInt("precio");
            }
            //Muestro los Productos
            System.out.println("Id: " + idProductos[i] + "\nNombre: " + nombresProductos[i] + "\nPrecio: $" + preciosProductos[i] + "\n/////////////");
        }
        //Realizo las acciones correspondientes
        this.acciones(eleccion);
    }
    private void utensiliosVarios(int eleccion)throws Exception{
        //UV
        //Creo la sentencia sql y la envio para obtener la cantidad de productos
        this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='UV';");
        this.rs = this.conexionPostgresql.getRs();
        int longitud = 0;
        //Obtengo la longitud
        while (this.rs.next()){
            longitud++;
            //nombresProductos[idProducto] = this.rs.getString("nombreProducto");
            //preciosProductos[idProducto] = this.rs.getInt("precio");
        }
        int idProductos[] =new int[longitud];
        //Obtengo los Id
        int espacio = 0;
        for (int i = 0; i <=this.longitudBD; i++) {
            //Realizo el proceso de obtener los id
            this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='UV' AND idProducto =" + (i+1) +";");
            this.rs = this.conexionPostgresql.getRs();
            int id = 0;
            while (this.rs.next()){
                id = rs.getInt("idProducto");
            }
            if (id!=0) {
                this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='UV' AND idProducto =" + id +";");
                this.rs = this.conexionPostgresql.getRs();
                while (this.rs.next()) {
                    idProductos[espacio] = rs.getInt("idProducto");
                }
                espacio++;
            }
        }
        //Obtengo el nombre y el precio
        String nombresProductos[] = new String[longitud];
        int preciosProductos[] = new int[longitud];
        for (int i = 0; i < longitud; i++){ ;
            //Creo la sentencia sql y la envio para obtener la cantidad de productos
            this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='UV' AND  idProducto = "+ idProductos[i] + ";");
            this.rs = this.conexionPostgresql.getRs();
            while (this.rs.next()) {
                idProductos[i] = rs.getInt("idProducto");
                nombresProductos[i] = rs.getString("nombreProducto");
                preciosProductos[i] = rs.getInt("precio");
            }
            //Muestro los Productos
            System.out.println("Id: " + idProductos[i] + "\nNombre: " + nombresProductos[i] + "\nPrecio: $" + preciosProductos[i] + "\n/////////////");
        }
        //Realizo las acciones correspondientes
        this.acciones(eleccion);
    }

    private void bebidas(int eleccion) throws Exception{
        //B
        //Creo la sentencia sql y la envio para obtener la cantidad de productos
        this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='B';");
        this.rs = this.conexionPostgresql.getRs();
        int longitud = 0;
        //Obtengo la longitud
        while (this.rs.next()){
            longitud++;
            //nombresProductos[idProducto] = this.rs.getString("nombreProducto");
            //preciosProductos[idProducto] = this.rs.getInt("precio");
        }
        int idProductos[] =new int[longitud];
        //Obtengo los Id
        int espacio = 0;
        for (int i = 0; i <=this.longitudBD; i++) {
            //Realizo el proceso de obtener los id
            this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='B' AND idProducto =" + (i+1) +";");
            this.rs = this.conexionPostgresql.getRs();
            int id = 0;
            while (this.rs.next()){
                id = rs.getInt("idProducto");
            }
            if (id!=0) {
                this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='B' AND idProducto =" + id +";");
                this.rs = this.conexionPostgresql.getRs();
                while (this.rs.next()) {
                    idProductos[espacio] = rs.getInt("idProducto");
                }
                espacio++;
            }
        }
        //Obtengo el nombre y el precio
        String nombresProductos[] = new String[longitud];
        int preciosProductos[] = new int[longitud];
        for (int i = 0; i < longitud; i++){ ;
            //Creo la sentencia sql y la envio para obtener la cantidad de productos
            this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE tipo='B' AND  idProducto = "+ idProductos[i] + ";");
            this.rs = this.conexionPostgresql.getRs();
            while (this.rs.next()) {
                idProductos[i] = rs.getInt("idProducto");
                nombresProductos[i] = rs.getString("nombreProducto");
                preciosProductos[i] = rs.getInt("precio");
            }
            //Muestro los Productos
            System.out.println("Id: " + idProductos[i] + "\nNombre: " + nombresProductos[i] + "\nPrecio: $" + preciosProductos[i] + "\n/////////////");
        }
        //Realizo las acciones correspondientes
        this.acciones(eleccion);
    }

    private void acciones(int categoria)throws Exception{
        System.out.println("¿Que quieres hacer ahora?");
        System.out.println("1-Volver a ver los productos");
        System.out.println("2-Volver a ver las categorias de los productos");
        System.out.println("3-Ir a mi cuenta ");
        System.out.println("4-Ingresar el id de un producto para agregarlo al carrito");
        int accion = this.usuario.nextInt();
        switch (accion){
            case 1:
                if (categoria == 1){
                    this.alimentos(categoria);
                }else if (categoria == 2){
                    this.utenciliosHigienicos(categoria);
                }else if (categoria == 3){
                    this.utensiliosVarios(categoria);
                }else if (categoria == 4){
                    this.bebidas(categoria);
                };
                break;
            case 2:
                this.categorias(this.nombre,this.password);
                break;
            case 3:
                Cuenta cuenta = new Cuenta(this.nombre,this.password);
                break;
            case 4:
                //Aqui vendra le instancia hacia una funcion que agregara el producto al carrito y mas
        }
    }
}
