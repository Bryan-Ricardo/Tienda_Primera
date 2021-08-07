import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ConfigurarCuenta {
    private Scanner usuario = new Scanner(System.in);
    private ConexionPostgresql conexionPostgresql;
    private ResultSet rs = null;

    private String nombre = "";
    private String password = "";
    private int idUsuario = 0;

    public ConfigurarCuenta(String nombre, String password, int idUsuario){
        this.nombre = nombre;
        this.password = password;
        this.idUsuario = idUsuario;
        try {
            this.acciones();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void acciones() throws Exception{
        System.out.println("¡Hola que tal! Bienvenido a las configuraciones de tu cuenta ");
        System.out.println("¿Que quieres realizar?");
        System.out.println("1-Cambiar nombre de usuario");
        System.out.println("2-Cambiar contraseña");
        System.out.println("3-Eliminar Cuanta");
        System.out.println("4-Regresar a tu cuenta");
        int eleccion = this.usuario.nextInt();
        switch (eleccion){
            case 1:
                System.out.println("Cambiar nombre de usuario");
                this.cambiarNombreUsuario();
                break;
            case 2:
                System.out.println("Cambiar contraseña");
                this.cambiarContrsena();
                break;
            case 3:
                System.out.println("Eliminar tu cuenta");
                this.eliminarCuenta();
                break;
            case 4:
                System.out.println("Regresar a tu cuenta");
                Cuenta cuenta = new Cuenta(this.nombre,this.password,this.idUsuario);
                break;
            default:
                this.acciones();
                break;
        }
    }
    private void cambiarNombreUsuario()throws Exception{
        System.out.println("¿Seguro que quieres cambiar el siguiente nombre: " + this.nombre + "? (S/N)");
        String eleccion = this.usuario.next();
        if (eleccion.equalsIgnoreCase("S") || eleccion.equalsIgnoreCase("SI")){
            System.out.println("Ingresa el nombre que ocupabas anteriormente");
            String nombreAnterior  = this.usuario.next();
            //Verifico que el usuario se encuentre en la base de datos
            if (this.nombre.equals(nombreAnterior)){
                System.out.println("Comprobacion exitosa");
                System.out.println("Ingresa el nuevo nombre que quieres tomar:");
                String nombreNuevo = this.usuario.next();

                //Comprobar si hay un usuario con el mismo nombre
                this.conexionPostgresql = new ConexionPostgresql("SELECT usuario FROM usuarios WHERE usuario = '"+ nombreNuevo + "';");
                this.rs = this.conexionPostgresql.getRs();
                String comprobarUsuarios ="";
                //Guardo el dato si hay una existencia para despues comprobar
                while (this.rs.next()){
                    comprobarUsuarios = this.rs.getString("usuario");
                }
                if (comprobarUsuarios.equals(nombreNuevo)){
                    while (comprobarUsuarios.equals(nombreNuevo)){
                        System.out.println("Ya esta ocupado ese nombre");
                        System.out.println("¿Quieres cancelar la accion? (S/N)");
                        String cancelarEleccion = this.usuario.next();
                        if (cancelarEleccion.equalsIgnoreCase("S") || cancelarEleccion.equals("SI")){
                            this.acciones();
                        }else{
                            System.out.println("Ingresa otro nombre:");
                            nombreNuevo = this.usuario.next();

                            //Comprobar si hay un usuario con el mismo nombre
                            this.conexionPostgresql = new ConexionPostgresql("SELECT usuario FROM usuarios WHERE usuario = '"+ nombreNuevo + "';");
                            this.rs = this.conexionPostgresql.getRs();
                            //Guardo el dato si hay una existencia para despues comprobar
                            while (this.rs.next()){
                                comprobarUsuarios = this.rs.getString("usuario");
                            }
                            if (!comprobarUsuarios.equals(nombreNuevo)){
                                this.conexionPostgresql = new ConexionPostgresql("UPDATE usuarios SET "+"usuario='"+ nombreNuevo+ "' WHERE idUsuario= " +this.idUsuario );
                                this.nombre = nombreNuevo;
                                System.out.println("Cambio de nombre exitoso " + this.nombre);
                                this.acciones();
                            }
                        }
                    }
                }else if (!comprobarUsuarios.equals(nombreNuevo)){
                    this.conexionPostgresql = new ConexionPostgresql("UPDATE usuarios SET "+"usuario='"+ nombreNuevo+ "' WHERE idUsuario= " +this.idUsuario );
                    this.nombre = nombreNuevo;
                    System.out.println("Cambio de nombre exitoso " + this.nombre);
                    this.acciones();
                }
            }else if (!this.nombre.equals(nombreAnterior)){
                while (!this.nombre.equals(nombreAnterior)){
                    System.out.println("EL nombre no coincide");
                    System.out.println("¿Quieres cancelar la accion? (S/N)");
                    String cancelarEleccion = this.usuario.next();
                    if (cancelarEleccion.equalsIgnoreCase("S") || cancelarEleccion.equals("SI")){
                        this.acciones();
                    }else if (!cancelarEleccion.equalsIgnoreCase("S") || !cancelarEleccion.equals("SI")){
                        System.out.println("Ingresa el nombre que ocupabas anteriormente:");
                        nombreAnterior  = this.usuario.next();
                        if (this.nombre.equals(nombreAnterior)){
                            System.out.println("Comprobacion exitosa");
                            System.out.println("Ingresa el nuevo nombre que quieres tomar:");
                            String nombreNuevo = this.usuario.next();

                            //Comprobar si hay un usuario con el mismo nombre
                            this.conexionPostgresql = new ConexionPostgresql("SELECT usuario FROM usuarios WHERE usuario = '"+ nombreNuevo + "';");
                            this.rs = this.conexionPostgresql.getRs();
                            String comprobarUsuarios ="";
                            //Guardo el dato si hay una existencia para despues comprobar
                            while (this.rs.next()){
                                comprobarUsuarios = this.rs.getString("usuario");
                            }
                            if (comprobarUsuarios.equals(nombreNuevo)){
                                while (comprobarUsuarios.equals(nombreNuevo)){
                                    System.out.println("Ya esta ocupado ese nombre");
                                    System.out.println("¿Quieres cancelar la accion? (S/N)");
                                    cancelarEleccion = this.usuario.next();
                                    if (cancelarEleccion.equalsIgnoreCase("S") || cancelarEleccion.equals("SI")){
                                        this.acciones();
                                    }else{
                                        System.out.println("Ingresa otro nombre:");
                                        nombreNuevo = this.usuario.next();

                                        //Comprobar si hay un usuario con el mismo nombre
                                        this.conexionPostgresql = new ConexionPostgresql("SELECT usuario FROM usuarios WHERE usuario = '"+ nombreNuevo + "';");
                                        this.rs = this.conexionPostgresql.getRs();
                                        //Guardo el dato si hay una existencia para despues comprobar
                                        while (this.rs.next()){
                                            comprobarUsuarios = this.rs.getString("usuario");
                                        }
                                        if (!comprobarUsuarios.equals(nombreNuevo)){
                                            this.conexionPostgresql = new ConexionPostgresql("UPDATE usuarios SET "+"usuario='"+ nombreNuevo+ "' WHERE idUsuario= " +this.idUsuario );
                                            this.nombre = nombreNuevo;
                                            System.out.println("Cambio de nombre exitoso " + this.nombre);
                                            this.acciones();
                                        }
                                    }
                                }
                            }else if (!comprobarUsuarios.equals(nombreNuevo)){
                                this.conexionPostgresql = new ConexionPostgresql("UPDATE usuarios SET "+"usuario='"+ nombreNuevo+ "' WHERE idUsuario= " +this.idUsuario );
                                this.nombre = nombreNuevo;
                                System.out.println("Cambio de nombre exitoso " + this.nombre);
                                this.acciones();
                            }
                        }
                    }
                }
            }
        }else{
            this.acciones();
        }
    }

    private void cambiarContrsena() throws Exception{
        System.out.println("¿Seguro que quieres cambiar la contraseña? (S/N)");
        String respuestaCambiarContraseña = this.usuario.next();
        if (respuestaCambiarContraseña.equalsIgnoreCase("S") || respuestaCambiarContraseña.equalsIgnoreCase("SI")){
            System.out.println("Ingresa la antigua contraseña: ");
            String passwordAnterior = this.usuario.next();
            if (passwordAnterior.equals(this.password)){
                System.out.println("¡Comprobacion Exitosa!");
                this.comprobacion();
            }else if (!passwordAnterior.equals(this.password)){
                while (!passwordAnterior.equals(this.password)){
                    System.out.println("No coincide con la antigua contraseña");
                    this.regresar();
                    System.out.println("Ingresa la antigua contraseña: ");
                    passwordAnterior = this.usuario.next();
                }
                if (passwordAnterior.equals(this.password)){
                    System.out.println("¡Comprobacion Exitosa!");
                    this.comprobacion();
                }
            }
        }else{
            this.acciones();
        }
    }

    private void eliminarCuenta()throws Exception{
        System.out.println("¿Seguro que quieres eliminar tu cuenta "+this.nombre+" (S/N)");
        String respuestaElimiarCuenta = this.usuario.next();
        if (respuestaElimiarCuenta.equalsIgnoreCase("S") || respuestaElimiarCuenta.equalsIgnoreCase("SI")){
            System.out.println("Ingresa tu usuario: ");
            String comprobarUsuario = this.usuario.next();
            if (comprobarUsuario.equals(this.nombre)){
                System.out.println("Comprobacion exitosa");
                System.out.println("Ingresa tu contraseña");
                String comprobarPassword = this.usuario.next();
                if (comprobarPassword.equals(this.password)){
                    this.conexionPostgresql = new ConexionPostgresql("DELETE FROM productoSeleccionado WHERE idCarrito=" + this.idUsuario + ";");
                    this.conexionPostgresql = new ConexionPostgresql("DELETE FROM carrito WHERE idusuario=" + this.idUsuario + ";");
                    this.conexionPostgresql = new ConexionPostgresql("DELETE FROM usuarios WHERE idusuario=" + this.idUsuario + "AND usuario= '" + this.nombre + "';");
                    System.out.println("ELIMINACION DE CUENTA EXITOSA");
                    Inicio inicio = new Inicio();
                }else if (!comprobarPassword.equals(this.password)){
                    while (!comprobarPassword.equals(this.password)){
                        System.out.println("No coincide con tu contraseña");
                        this.regresar();
                        System.out.println("Ingresa nuevamente tu contraseña");
                        comprobarPassword = this.usuario.next();
                    }
                    if (comprobarPassword.equals(this.password)){
                        this.conexionPostgresql = new ConexionPostgresql("DELETE FROM usuarios WHERE idusuario=" + this.idUsuario + "AND usuario= '" + this.nombre + "';");
                        System.out.println("ELIMINACION DE CUENTA EXITOSA");
                        Inicio inicio = new Inicio();
                    }
                }
            }else if (!comprobarUsuario.equals(this.nombre)){
                while (!comprobarUsuario.equals(this.nombre)){
                    System.out.println("No coincide con tu Usuario");
                    this.regresar();
                    System.out.println("Ingresa nuevamente tu usuario: ");
                    comprobarUsuario = this.usuario.next();
                }
                if (comprobarUsuario.equals(this.nombre)){
                    System.out.println("Comprobacion exitosa");
                    System.out.println("Ingresa tu contraseña");
                    String comprobarPassword = this.usuario.next();
                    if (comprobarPassword.equals(this.password)){
                        this.conexionPostgresql = new ConexionPostgresql("DELETE FROM usuarios WHERE idusuario=" + this.idUsuario + "AND usuario= '" + this.nombre + "';");
                        System.out.println("ELIMINACION DE CUENTA EXITOSA");
                        Inicio inicio = new Inicio();
                    }else if (!comprobarPassword.equals(this.password)){
                        while (!comprobarPassword.equals(this.password)){
                            System.out.println("No coincide con tu contraseña");
                            this.regresar();
                            System.out.println("Ingresa nuevamente tu contraseña");
                            comprobarPassword = this.usuario.next();
                        }
                        if (comprobarPassword.equals(this.password)){
                            this.conexionPostgresql = new ConexionPostgresql("DELETE FROM usuarios WHERE idusuario=" + this.idUsuario + "AND usuario= '" + this.nombre + "';");
                            System.out.println("ELIMINACION DE CUENTA EXITOSA");
                            Inicio inicio = new Inicio();
                        }
                    }
                }
            }
        }else{
            this.acciones();
        }
    }

    private void comprobacion()throws Exception{
        System.out.println("Tu nueva contraseña debe contener los siguientes requerimientos");
        System.out.println("1-Debe tener almenos 8 caracteres");
        System.out.println("2-Debe tener almenos una mayuscula");
        System.out.println("3-Debe tener almenos un numero");
        System.out.println("4-Debe tener almenos un signo(?,¿,@,!)");
        String nuevaPassword = this.usuario.next();
        //Comprobando las condiciones
        boolean cumplePrimeraCondicion = this.primeraCondicion(nuevaPassword);
        boolean cumpleSegundaCondicion = this.segundaCondicion(nuevaPassword);
        boolean cumpleTerceraCondicion = this.terceraCondicion(nuevaPassword);
        boolean cumpleCuartaCondicion = this.cuartaCondicion(nuevaPassword);

        while (cumplePrimeraCondicion != true ||cumpleSegundaCondicion != true ||cumpleTerceraCondicion != true ||cumpleCuartaCondicion != true ){
            if (cumplePrimeraCondicion == false){
                System.out.println("No se cumplio la condicion 1");
                this.regresar();
                System.out.println("Ingresa otra contraseña");
                nuevaPassword = this.usuario.next();
                //Comprobando las condiciones
                cumplePrimeraCondicion = this.primeraCondicion(nuevaPassword);
                cumpleSegundaCondicion = this.segundaCondicion(nuevaPassword);
                cumpleTerceraCondicion = this.terceraCondicion(nuevaPassword);
                cumpleCuartaCondicion = this.cuartaCondicion(nuevaPassword);
            }else if (cumpleSegundaCondicion == false){
                System.out.println("No se cumplio la condicion 2");
                this.regresar();
                System.out.println("Ingresa otra contraseña");
                nuevaPassword = this.usuario.next();
                //Comprobando las condiciones
                cumplePrimeraCondicion = this.primeraCondicion(nuevaPassword);
                cumpleSegundaCondicion = this.segundaCondicion(nuevaPassword);
                cumpleTerceraCondicion = this.terceraCondicion(nuevaPassword);
                cumpleCuartaCondicion = this.cuartaCondicion(nuevaPassword);
            }else if (cumpleTerceraCondicion == false){
                System.out.println("No se cumplio la condicion 3");
                this.regresar();
                System.out.println("Ingresa otra contraseña");
                nuevaPassword = this.usuario.next();
                //Comprobando las condiciones
                cumplePrimeraCondicion = this.primeraCondicion(nuevaPassword);
                cumpleSegundaCondicion = this.segundaCondicion(nuevaPassword);
                cumpleTerceraCondicion = this.terceraCondicion(nuevaPassword);
                cumpleCuartaCondicion = this.cuartaCondicion(nuevaPassword);
            }else if (cumpleCuartaCondicion == false){
                System.out.println("No se cumplio la condicion 4");
                this.regresar();
                System.out.println("Ingresa otra contraseña");
                nuevaPassword = this.usuario.next();
                //Comprobando las condiciones
                cumplePrimeraCondicion = this.primeraCondicion(nuevaPassword);
                cumpleSegundaCondicion = this.segundaCondicion(nuevaPassword);
                cumpleTerceraCondicion = this.terceraCondicion(nuevaPassword);
                cumpleCuartaCondicion = this.cuartaCondicion(nuevaPassword);
            }
        }
        if (cumplePrimeraCondicion == true &&cumpleSegundaCondicion == true &&cumpleTerceraCondicion == true &&cumpleCuartaCondicion == true ){
            System.out.println("Se cambio la contraseña correctamente");
            this.conexionPostgresql = new ConexionPostgresql("UPDATE usuarios SET "+"contrasena='"+nuevaPassword + "' WHERE idUsuario= " +this.idUsuario );
            this.password = nuevaPassword;
            this.acciones();
        }
    }

    private boolean primeraCondicion(String nuevaPassword){
        boolean primeraCondicion = false;
        if (nuevaPassword.length() >= 8){
            primeraCondicion = true;
        }
        return primeraCondicion;
    }
    private boolean segundaCondicion(String nuevaPassword){
        boolean segundaCondicion = false;
        for (int i = 0; i < nuevaPassword.length(); i++) {
            int comprobarCaracter = nuevaPassword.charAt(i);
            if (comprobarCaracter >=65 && comprobarCaracter <=90){
                segundaCondicion= true;
            }
        }
        return segundaCondicion;
    }
    private boolean terceraCondicion(String nuevaPassword){
        boolean terceraCondicion = false;
        for (int i = 0; i <nuevaPassword.length() ; i++) {
            int comprobarNumero = nuevaPassword.charAt(i);
            char numeros[] = {'0','1','2','3','4','5','6','7','8','9'};
            for (int j = 0; j <= numeros.length-1; j++) {
                if (comprobarNumero == numeros[j]){
                    terceraCondicion =true;
                }
            }
        }
        return terceraCondicion;
    }

    private boolean cuartaCondicion(String nuevaPassword){
        boolean cuartaCondicion = false;
        for (int i = 0; i <nuevaPassword.length() ; i++) {
            char comprobarSignos = nuevaPassword.charAt(i);
            char signos[] = {'!','¡','#','$','%','&','/','(',')','=','?','¿'};
            for (int j = 0; j <signos.length-1 ; j++) {
                if (comprobarSignos == signos[j]){
                    cuartaCondicion = true;
                }
            }
        }
        return cuartaCondicion;
    }

    private void regresar()throws Exception{
        System.out.println("¿Te gustaria cancelar la accion?(S/N)");
        String respuesta = this.usuario.next();
        if(respuesta.equalsIgnoreCase("S")|| respuesta.equalsIgnoreCase("SI")){
            this.acciones();
        }
    }
}
