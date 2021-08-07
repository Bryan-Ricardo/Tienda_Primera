import java.sql.ResultSet;
import java.util.Scanner;

public class CarritoCompras {

    private Scanner usuario = new Scanner(System.in);
    private ConexionPostgresql conexionPostgresql;
    private ResultSet rs = null;

    private int longitudBD = 0;

    private String nombre;
    private String password = "";
    int idUsuario = 0;

    CarritoCompras(String nombre, String password,int idUsuario,int idProducto){
        try {
            this.nombre = nombre;
            this.password = password;
            this.idUsuario = idUsuario;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    CarritoCompras(String nombre, String password,int idUsuario){
        try {
            this.nombre = nombre;
            this.password = password;
            this.idUsuario = idUsuario;
            longitudBD = this.asignarLongitudBD();
            System.out.println("¡Bienvenido a tu carrito de compras!");
            this.opciones();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void opciones()throws Exception{
        System.out.println("¿Que quieres hacer?");
        System.out.println("1-Ver los productos del carrito");
        System.out.println("2-Eliminar un producto");
        System.out.println("3-Facturar los productos");
        System.out.println("4-Regresar a tu cuenta");
        int eleccion = this.usuario.nextInt();
        if (eleccion == 1){
            //Ver los productos del carrito
            this.verProductosCarrito();
            this.opciones();
        }else if (eleccion == 2){
            //Eliminar un producto
            this.eleiminarProducto();
            this.opciones();
        }else if (eleccion == 3){
            //Facturar los productos
            this.facturar();
        }else if (eleccion == 4){
            //Regresar a tu cuenta
            Cuenta cuenta = new Cuenta(this.nombre,this.password,this.idUsuario);
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

    private void verProductosCarrito()throws Exception{
        System.out.println("Tus Productos son los siguientes" + this.nombre + "\n");
        //Creo la sentencia sql y la envio para obtener la cantidad de productos
        this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM productoSeleccionado WHERE idCarrito=" + this.idUsuario + ";");
        this.rs = this.conexionPostgresql.getRs();
        int cantidadProductos = 0;
        while (this.rs.next()){
            cantidadProductos++;
        }
        int idProductosUsuario[] = new int[cantidadProductos];
        String tipoProductosUsuario[] = new String[cantidadProductos];
        String nombreProductosUsuario[] = new String[cantidadProductos];
        int precioProductosUsuario[] = new int[cantidadProductos];
        cantidadProductos = 0;
        for (int i = 0; i <this.longitudBD ; i++) {
            //Creo la sentencia sql y la envio para obtener la cantidad de productos
            this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM productoSeleccionado WHERE idCarrito=" + this.idUsuario + " AND  idProductoSeleccionado= " +(i+1) +";");
            this.rs = this.conexionPostgresql.getRs();
            int idProductoSeleccionadoExistente = 0;
            while (this.rs.next()){
                idProductoSeleccionadoExistente = this.rs.getInt("idProducto");
            }
            if(idProductoSeleccionadoExistente!=0){
                //Creo la sentencia sql y la envio para obtener la cantidad de productos
                this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE idProducto=" + idProductoSeleccionadoExistente +";");
                this.rs = this.conexionPostgresql.getRs();
                while (this.rs.next()){
                     tipoProductosUsuario[cantidadProductos]= this.rs.getString("tipo");
                     nombreProductosUsuario[cantidadProductos] = this.rs.getString("nombreProducto");
                     precioProductosUsuario[cantidadProductos] = this.rs.getInt("precio");
                }
                //Creo la sentencia sql y la envio para obtener la cantidad de productos
                this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM productoSeleccionado WHERE idProducto=" + idProductoSeleccionadoExistente +" AND idCarrito = " + this.idUsuario + ";");
                this.rs = this.conexionPostgresql.getRs();
                while (this.rs.next()){
                    idProductosUsuario[cantidadProductos] = this.rs.getInt("idProductoSeleccionado");
                }
                System.out.println("Producto:\n{"+ "\nid:" +idProductosUsuario[cantidadProductos] +"\nTipo:"+tipoProductosUsuario[cantidadProductos] + "\nNombre del producto:" + nombreProductosUsuario[cantidadProductos]+"\nPrecio:" + precioProductosUsuario[cantidadProductos] + "\n}");
                cantidadProductos++;
            }
        }
        if (cantidadProductos == 0){
            System.out.println("No tienes productos en tu carrito");
        }
    }

    private void eleiminarProducto() throws Exception{
            System.out.println("Ingresa el id del producto que quieres eliminar");
            int idEliminar = this.usuario.nextInt();

            //Creo la sentencia sql y la envio para obtener la cantidad de productos
            //Verificare si el id esta en el carrito de compras
            this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM productoSeleccionado  WHERE idProductoSeleccionado=" + idEliminar + " AND idCarrito= " + this.idUsuario +";");
            this.rs = this.conexionPostgresql.getRs();
            int idProductoSeleccionadoExistente = 0;
            int idProducto = 0;
            while (this.rs.next()){
                idProducto = this.rs.getInt("idProducto");
                idProductoSeleccionadoExistente= this.rs.getInt("idProductoSeleccionado");
            }
            if(idProductoSeleccionadoExistente !=0){
                this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM almacen WHERE idProducto=" + idProducto + ";");
                this.rs = this.conexionPostgresql.getRs();
                String nombreProducto = "";
                while (this.rs.next()){
                    nombreProducto = this.rs.getString("nombreProducto");
                }
                System.out.println("Seguro que quieres eliminar el siguiente producto: -" + nombreProducto+ "- (S/N)");
                String eliminarProducto = this.usuario.next();
                if (eliminarProducto.equalsIgnoreCase("S")){
                    this.conexionPostgresql = new ConexionPostgresql("DELETE FROM productoSeleccionado WHERE idProductoSeleccionado = " + idProductoSeleccionadoExistente + " AND idCarrito=" + this.idUsuario  +";" );
                    System.out.println("ELIMINACION EXITOSA");
                    this.opciones();
                }else{
                    System.out.println("PROCESO CANCELADO");
                }
            }else if (idProductoSeleccionadoExistente == 0){
                System.out.println("El id que ingresastes no corresponde a ningun producto de tu carrito");
            }
    }

    private void facturar() throws Exception{
        System.out.println("¿Estas seguro de que quieres facturar tus productos? (S/N)");
        String decisionFacturar = this.usuario.next();
        if (decisionFacturar.equalsIgnoreCase("S")){
            //Obtendre la cantidad de los productos al igual que obtener el costo total
            this.conexionPostgresql = new ConexionPostgresql("Select * FROM productoSeleccionado WHERE idCarrito = " + this.idUsuario + ";");
            this.rs = this.conexionPostgresql.getRs();

            int cantidadProductos=0;

            while (this.rs.next()){
                cantidadProductos++;
            }
            System.out.println("Cantidad de productos :" + cantidadProductos);

            int idProductos[] = new int[cantidadProductos];
            int idCarrito = this.idUsuario;
            int lugar = 0;
            //GUARDO LOS ID DE LOS PRODUCTOS EN EL CARRITO
            for (int i = 0; i < this.longitudBD; i++) {
                this.conexionPostgresql = new ConexionPostgresql("Select * FROM productoSeleccionado WHERE idCarrito = " + this.idUsuario + "AND idProductoSeleccionado= " + i + ";");
                this.rs = this.conexionPostgresql.getRs();
                int comprobarIdProductos = 0;
                while (this.rs.next()){
                    comprobarIdProductos = this.rs.getInt("idProducto");
                }
                if (comprobarIdProductos!=0){
                    idProductos[lugar] = comprobarIdProductos;
                    lugar++;
                }
            }
            //GUARDO EL NOMBRE Y PRECIO DE LOS PRODUCTOS SELECCIONADOS
            String nombreProductos[] = new String[cantidadProductos];
            int preciosProductos[]=new int[cantidadProductos];
            for (int i = 0; i <cantidadProductos ; i++) {
                this.conexionPostgresql = new ConexionPostgresql("Select * FROM almacen WHERE idProducto = " + idProductos[i]+";");
                this.rs = this.conexionPostgresql.getRs();
                while (this.rs.next()){
                    nombreProductos[i] = this.rs.getString("nombreProducto");
                    preciosProductos[i] = this.rs.getInt("precio");
                }
            }
            //Calculare el total del costo que se pagara
            int pagoTotal=0;
            for (int i = 0; i <cantidadProductos ; i++) {
                pagoTotal+=preciosProductos[i];
            }

            System.out.println("Se facturaron "+ cantidadProductos +"\nPagar: " +pagoTotal);
            System.out.println("PRESIONE CUALQUIER TECLA PARA PAGAR");
            String pagado = this.usuario.next();
            System.out.println("PAGADO");
            Cuenta cuenta = new Cuenta(this.nombre,this.password,this.idUsuario);
            this.conexionPostgresql = new ConexionPostgresql("DELETE FROM productoSeleccionado WHERE idCarrito=" + this.idUsuario + ";");//EN ESTA PARTE COLOCARE LA SERESA DEL PASTEL QUE SERA QUE SE BORRARAN LOS PRODUCTOS DEL CARRITO COMO SI LOS HUBIERA PAGADO
        }else{
            this.opciones();
        }
    }
}