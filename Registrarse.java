import java.sql.ResultSet;
import java.util.Scanner;

public class Registrarse {
    private Scanner usuario = new Scanner(System.in);
    private ConexionPostgresql conexionPostgresql;
    private ResultSet rs = null;

    private String nombre;
    private String password;
    private int idUsuario;

    Registrarse(){
        System.out.println("Ingresa un usuario");
        this.nombre = this.usuario.next();
        try {
            //Comprobar si hay un usuario con el mismo nombre
            this.conexionPostgresql = new ConexionPostgresql("SELECT usuario FROM usuarios WHERE usuario = '"+ this.nombre + "';");
            this.rs = this.conexionPostgresql.getRs();
            String comprobarUsuarios ="";
            //Guardo el dato si hay una existencia para despues comprobar
            while (this.rs.next()){
                    comprobarUsuarios = this.rs.getString("usuario");
            }
            //Verificando si hay coincidencias
            if (comprobarUsuarios.equals(this.nombre)){
                //Si existe una coincidencia se volvera a repetir el proceso
                while (comprobarUsuarios.equals(this.nombre)){
                    System.out.println("Ya esta ocupado ese usuario");
                    this.regresar();
                    System.out.println("Ingresa otro usuario:");
                    this.nombre = this.usuario.next();
                    //Comprobar si hay un usuario con el mismo nombre
                    this.conexionPostgresql = new ConexionPostgresql("SELECT usuario FROM usuarios WHERE usuario = '"+ this.nombre + "';");
                    this.rs = this.conexionPostgresql.getRs();
                    while (this.rs.next()){
                        comprobarUsuarios = this.rs.getString("usuario");
                    }
                    if (!comprobarUsuarios.equals(this.nombre)){
                        //System.out.println("Es un usuario diferente");
                        //Crear la contraseña segun algunas caaracteristicas de seguridad
                        System.out.println("Muy bien ya con tu usuario creado, necesitaras una contraseña para entrar asi que ingresa una con las siguientes caracteristicas:");
                        System.out.println("1-Debe tener almenos 8 caracteres");
                        System.out.println("2-Debe tener almenos una mayuscula");
                        System.out.println("3-Debe tener almenos un numero");
                        System.out.println("4-Debe tener almenos un signo(?,¿,@,!,...)");
                        this.password = this.usuario.next();
                        //Comprobando las condiciones
                        boolean cumplePrimeraCondicion = this.primeraCondicion();
                        boolean cumpleSegundaCondicion = this.segundaCondicion();
                        boolean cumpleTerceraCondicion = this.terceraCondicion();
                        boolean cumpleCuartaCondicion = this.cuartaCondicion();

                        while (cumplePrimeraCondicion != true ||cumpleSegundaCondicion != true ||cumpleTerceraCondicion != true ||cumpleCuartaCondicion != true ){
                            if (cumplePrimeraCondicion == false){
                                System.out.println("No se cumplio la condicion 1");
                                this.regresar();
                                System.out.println("Ingresa otra contraseña");
                                this.password = this.usuario.next();
                                //Comprobando las condiciones
                                cumplePrimeraCondicion = this.primeraCondicion();
                                cumpleSegundaCondicion = this.segundaCondicion();
                                cumpleTerceraCondicion = this.terceraCondicion();
                                cumpleCuartaCondicion = this.cuartaCondicion();
                            }else if (cumpleSegundaCondicion == false){
                                System.out.println("No se cumplio la condicion 2");
                                this.regresar();
                                System.out.println("Ingresa otra contraseña");
                                this.password = this.usuario.next();
                                //Comprobando las condiciones
                                cumplePrimeraCondicion = this.primeraCondicion();
                                cumpleSegundaCondicion = this.segundaCondicion();
                                cumpleTerceraCondicion = this.terceraCondicion();
                                cumpleCuartaCondicion = this.cuartaCondicion();
                            }else if (cumpleTerceraCondicion == false){
                                System.out.println("No se cumplio la condicion 3");
                                this.regresar();
                                System.out.println("Ingresa otra contraseña");
                                this.password = this.usuario.next();
                                //Comprobando las condiciones
                                cumplePrimeraCondicion = this.primeraCondicion();
                                cumpleSegundaCondicion = this.segundaCondicion();
                                cumpleTerceraCondicion = this.terceraCondicion();
                                cumpleCuartaCondicion = this.cuartaCondicion();
                            }else if (cumpleCuartaCondicion == false){
                                System.out.println("No se cumplio la condicion 4");
                                this.regresar();
                                System.out.println("Ingresa otra contraseña");
                                this.password = this.usuario.next();
                                //Comprobando las condiciones
                                cumplePrimeraCondicion = this.primeraCondicion();
                                cumpleSegundaCondicion = this.segundaCondicion();
                                cumpleTerceraCondicion = this.terceraCondicion();
                                cumpleCuartaCondicion = this.cuartaCondicion();
                            }
                        }
                        if (cumplePrimeraCondicion == true &&cumpleSegundaCondicion == true &&cumpleTerceraCondicion == true &&cumpleCuartaCondicion == true ){
                            System.out.println("Se cumplieron todas las condiciones");
                            this.registrarUsuario();
                        }
                    }
                }
            }else if (comprobarUsuarios.equals("")) {
                //System.out.println("Es un usuario diferente");
                //Crear la contraseña segun algunas caaracteristicas de seguridad
                System.out.println("Muy bien ya con tu usuario creado, necesitaras una contraseña para entrar asi que ingresa una con las siguientes caracteristicas:");
                System.out.println("1-Debe tener almenos 8 caracteres");
                System.out.println("2-Debe tener almenos una mayuscula");
                System.out.println("3-Debe tener almenos un numero");
                System.out.println("4-Debe tener almenos un signo(?,¿,@,!)");
                this.password = this.usuario.next();
                //Comprobando las condiciones
                boolean cumplePrimeraCondicion = this.primeraCondicion();
                boolean cumpleSegundaCondicion = this.segundaCondicion();
                boolean cumpleTerceraCondicion = this.terceraCondicion();
                boolean cumpleCuartaCondicion = this.cuartaCondicion();

                while (cumplePrimeraCondicion != true ||cumpleSegundaCondicion != true ||cumpleTerceraCondicion != true ||cumpleCuartaCondicion != true ){
                    if (cumplePrimeraCondicion == false){
                        System.out.println("No se cumplio la condicion 1");
                        this.regresar();
                        System.out.println("Ingresa otra contraseña");
                        this.password = this.usuario.next();
                        //Comprobando las condiciones
                        cumplePrimeraCondicion = this.primeraCondicion();
                        cumpleSegundaCondicion = this.segundaCondicion();
                        cumpleTerceraCondicion = this.terceraCondicion();
                        cumpleCuartaCondicion = this.cuartaCondicion();
                    }else if (cumpleSegundaCondicion == false){
                        System.out.println("No se cumplio la condicion 2");
                        this.regresar();
                        System.out.println("Ingresa otra contraseña");
                        this.password = this.usuario.next();
                        //Comprobando las condiciones
                        cumplePrimeraCondicion = this.primeraCondicion();
                        cumpleSegundaCondicion = this.segundaCondicion();
                        cumpleTerceraCondicion = this.terceraCondicion();
                        cumpleCuartaCondicion = this.cuartaCondicion();
                    }else if (cumpleTerceraCondicion == false){
                        System.out.println("No se cumplio la condicion 3");
                        this.regresar();
                        System.out.println("Ingresa otra contraseña");
                        this.password = this.usuario.next();
                        //Comprobando las condiciones
                        cumplePrimeraCondicion = this.primeraCondicion();
                        cumpleSegundaCondicion = this.segundaCondicion();
                        cumpleTerceraCondicion = this.terceraCondicion();
                        cumpleCuartaCondicion = this.cuartaCondicion();
                    }else if (cumpleCuartaCondicion == false){
                        System.out.println("No se cumplio la condicion 4");
                        this.regresar();
                        System.out.println("Ingresa otra contraseña");
                        this.password = this.usuario.next();
                        //Comprobando las condiciones
                        cumplePrimeraCondicion = this.primeraCondicion();
                        cumpleSegundaCondicion = this.segundaCondicion();
                        cumpleTerceraCondicion = this.terceraCondicion();
                        cumpleCuartaCondicion = this.cuartaCondicion();
                    }
                }
                if (cumplePrimeraCondicion == true &&cumpleSegundaCondicion == true &&cumpleTerceraCondicion == true &&cumpleCuartaCondicion == true ){
                    System.out.println("Se cumplieron todas las condiciones");
                    this.registrarUsuario();
                }
                /*//Comprobando si tiene 8 caracteres
                if (this.password.length() >= 8) {

                   //La contraseña cumple la condicion 1
                    */
                    /*int a = 'a';
                    int z = 'z';
                    int A = 'A';
                    int Z = 'Z';
                    Minusculas: 97-122 Mayusculas: 65-90
                    System.out.println("Minusculas: " + a + "-" +z + " Mayusculas: " + A + "-" + Z);


                    for (int i = 0; i < this.password.length(); i++) {
                        int comprobarCaracter = this.password.charAt(i);
                        if (comprobarCaracter >=65 && comprobarCaracter <=90){
                            cumpleSegundaCondicion= true;
                        }
                    }
                    if (cumpleSegundaCondicion == true){
                        //La contraseña cumple la condicion 2
                        //System.out.println("La contraseña cumple la condicion 2");
                        boolean cumpleTerceraCondicion = false;
                        for (int i = 0; i <this.password.length() ; i++) {
                            int comprobarNumero = this.password.charAt(i);
                            char numeros[] = {'0','1','2','3','4','5','6','7','8','9'};
                            for (int j = 0; j <= numeros.length-1; j++) {
                                if (comprobarNumero == numeros[j]){
                                    cumpleTerceraCondicion =true;
                                }
                            }
                        }
                        if (cumpleTerceraCondicion == true){
                            //La contraseña cumple la condicion 3
                            //System.out.println("La contraseña cumple la condicion 3");
                            boolean cumpleCuartaCondicion = false;
                            for (int i = 0; i <this.password.length() ; i++) {
                                char comprobarSignos = this.password.charAt(i);
                                char signos[] = {'!','¡','#','$','%','&','/','(',')','=','?','¿'};
                                for (int j = 0; j <signos.length-1 ; j++) {
                                    if (comprobarSignos == signos[j]){
                                        cumpleCuartaCondicion = true;
                                    }
                                }
                            }
                            if (cumpleCuartaCondicion == true){
                                //Ya paso todos los filtros es hora de registrar al usuario en la base de datos
                                //System.out.println("La contraseña cumple la condicion 4");
                                //Creo la sentencia sql y la envio
                                this.conexionPostgresql = new ConexionPostgresql("INSERT INTO usuarios (usuario,contrasena) VALUES('" + this.nombre + "','"+this.password+"');");
                                //Busco al usuario creado para confirmar su registro
                                this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM usuarios WHERE usuario='" +this.nombre+"';");
                                this.rs = this.conexionPostgresql.getRs();
                                //Guardo el dato si hay una existencia para despues comprobar
                                String usuarioCreado = "";
                                while (this.rs.next()){
                                    usuarioCreado = this.rs.getString("usuario");
                                }
                                System.out.println("Se a registrado: "+ usuarioCreado);
                            }else if (cumpleCuartaCondicion == false){
                                while (cumpleCuartaCondicion==false){
                                    System.out.println("La contraseña no cumple la condicion 4");
                                }
                            }
                        }else if (cumpleTerceraCondicion == false){
                            System.out.println("La contraseña no cumple la condicion 3");
                        }
                    }else if (cumpleSegundaCondicion == false){
                        /*while (cumpleSegundaCondicion == false){
                            //Pendiente colocar todo el codigo desde el inicio
                        }

                        System.out.println("La contraseña no cumple la condicion 2");
                    }


                }else if (this.password.length() <= 8){
                    while (this.password.length() <= 8){
                        System.out.println("Tu contraseña tiene menos de 8 caracteres");
                        System.out.println("Ingresa otra contraseña:");
                        this.password = this.usuario.next();
                        if (this.password.length() >= 8){
                            //La contraseña cumple la condicion 1
                        }
                    }
            }*/
            }
        }catch (Exception e){
            System.out.println("ERROR" + e.getMessage());
        }
        /*try{
        conexionPostgresql = new ConexionPostgresql("SELECT nombre FROM ejemplo WHERE id = 1;");
        this.rs = conexionPostgresql.getRs();
        while (this.rs.next()){

        }}catch (Exception e){
            System.out.println(e.getMessage());
        }*/
    }

    private boolean primeraCondicion(){
        boolean primeraCondicion = false;
        if (this.password.length() >= 8){
            primeraCondicion = true;
        }
        return primeraCondicion;
    }
    private boolean segundaCondicion(){
        boolean segundaCondicion = false;
        for (int i = 0; i < this.password.length(); i++) {
            int comprobarCaracter = this.password.charAt(i);
            if (comprobarCaracter >=65 && comprobarCaracter <=90){
                segundaCondicion= true;
            }
        }
        return segundaCondicion;
    }
    private boolean terceraCondicion(){
        boolean terceraCondicion = false;
        for (int i = 0; i <this.password.length() ; i++) {
            int comprobarNumero = this.password.charAt(i);
            char numeros[] = {'0','1','2','3','4','5','6','7','8','9'};
            for (int j = 0; j <= numeros.length-1; j++) {
                if (comprobarNumero == numeros[j]){
                    terceraCondicion =true;
                }
            }
        }
        return terceraCondicion;
    }

    private boolean cuartaCondicion(){
        boolean cuartaCondicion = false;
        for (int i = 0; i <this.password.length() ; i++) {
            char comprobarSignos = this.password.charAt(i);
            char signos[] = {'!','¡','#','$','%','&','/','(',')','=','?','¿'};
            for (int j = 0; j <signos.length-1 ; j++) {
                if (comprobarSignos == signos[j]){
                    cuartaCondicion = true;
                }
            }
        }
        return cuartaCondicion;
    }

    private void registrarUsuario()throws Exception{
        //Ya paso todos los filtros es hora de registrar al usuario en la base de datos
        //System.out.println("La contraseña cumple la condicion 4");
        //Creo la sentencia sql y la envio
        this.conexionPostgresql = new ConexionPostgresql("INSERT INTO usuarios (usuario,contrasena) VALUES('" + this.nombre + "','"+this.password+"');");
        //Busco al usuario creado para confirmar su registro
        this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM usuarios WHERE usuario='" +this.nombre+"';");
        this.rs = this.conexionPostgresql.getRs();
        //Guardo el dato si hay una existencia para despues comprobar
        String usuarioCreado = "";
        int idUsuarioCreado = 0;
        while (this.rs.next()){
            usuarioCreado = this.rs.getString("usuario");
            idUsuarioCreado = this.rs.getInt("idUsuario");
        }
        //Le doy valor al id del Usuario
        this.idUsuario = idUsuarioCreado;
        //Le creo un carrito de compras al usuario
        this.conexionPostgresql = new ConexionPostgresql("INSERT INTO carrito(idCarrito,idUsuario) VALUES("+idUsuarioCreado+ ","+ idUsuarioCreado+");");
        //Le comento que su cuenta ya fue creada
        System.out.println("Se a registrado: "+ usuarioCreado);
        Cuenta cuenta = new Cuenta(this.nombre,this.password,this.idUsuario);
    }
    private void regresar(){
        System.out.println("¿Te gustaria ir al inicio nuevamente?(S/N)");
        char respuesta = this.usuario.next().charAt(0);
        if(respuesta == 'S'|| respuesta == 's'){
            Inicio inicio = new Inicio();
        }
    }
}
