import java.sql.ResultSet;
import java.util.Scanner;

public class IniciarSecion {
    private Scanner usuario = new Scanner(System.in);
    private ConexionPostgresql conexionPostgresql;
    private ResultSet rs = null;

    private String nombre;
    private String password;
    private int idUsuario;

    IniciarSecion(){
        try {
        System.out.println("Ingresa tu usuario:");
        this.nombre = this.usuario.next();
        //Creo la sentencia sql y la envio
        this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM usuarios WHERE usuario='" + this.nombre+"';");
        this.rs = this.conexionPostgresql.getRs();
        //Ver si existe un usuario
        String usuarioExistente = "";
        while (this.rs.next()){
            usuarioExistente = this.rs.getString("usuario");
        }
        if(usuarioExistente.equals(this.nombre)){
            //En este punto se valida que ya hay un usuario solo falta colocar la contraseña
            System.out.println("EL USUARIO ES EXISTENTE");
            System.out.println("Ingresa la contraseña:");
            this.password = this.usuario.next();
            //Creo la sentencia sql y la envio
            this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM usuarios WHERE usuario='" + this.nombre+"' AND contrasena='" + this.password + "';");
            this.rs = this.conexionPostgresql.getRs();
            //Aqui se comprobara si la contraseña oicide con el usuario ingresado
            String passwordExistente = "";
            int idUsuarioExistente = 0;
            while (this.rs.next()){
                passwordExistente = this.rs.getString("contrasena");
                idUsuarioExistente = this.rs.getInt("idUsuario");
            }
            //Verifico si coloco la contraseña correca
            if (passwordExistente.equals(this.password)){
                this.idUsuario = idUsuarioExistente;
                System.out.println("Ingresaste a tu cuenta");
                Cuenta cuenta = new Cuenta(this.nombre,this.password,this.idUsuario);
            }else if (!passwordExistente.equals(this.password)){
                while (!passwordExistente.equals(this.password)){
                    System.out.println("La contraseña es incorreca");
                    this.regresar();
                    System.out.println("Ingresa nuevamente la contraseña:");
                    this.password = this.usuario.next();
                    //Creo la sentencia sql y la envio
                    this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM usuarios WHERE usuario='" + this.nombre+"' AND contrasena='" + this.password + "';");
                    this.rs = this.conexionPostgresql.getRs();
                    //Aqui se comprobara si la contraseña oicide con el usuario ingresado
                    while (this.rs.next()){
                        passwordExistente = this.rs.getString("contrasena");
                        idUsuarioExistente = this.rs.getInt("idUsuario");
                    }
                    //Verifico si coloco la contraseña correcta
                    if (passwordExistente.equals(this.password)){
                        this.idUsuario = idUsuarioExistente;
                        //System.out.println("Ingresaste a tu cuenta");
                        Cuenta cuenta = new Cuenta(this.nombre,this.password,this.idUsuario);
                    }
                }
            }
        }else if (!usuarioExistente.equals(this.nombre)){
            while (!usuarioExistente.equals(this.nombre)){
                System.out.println("No existe un usuario");
                this.regresar();
                System.out.println("Ingresa nuevamente el usuario");
                this.nombre = this.usuario.next();
                //Creo la sentencia sql y la envio
                this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM usuarios WHERE usuario='" + this.nombre+"';");
                this.rs = this.conexionPostgresql.getRs();
                //Ver si existe un usuario
                while (this.rs.next()){
                    usuarioExistente = this.rs.getString("usuario");
                }
                if (usuarioExistente.equals(this.nombre)){
                    //En este punto se valida que ya hay un usuario solo falta colocar la contraseña
                    System.out.println("EL USUARIO ES EXISTENTE");
                    System.out.println("Ingresa la contraseña:");
                    this.password = this.usuario.next();
                    //Creo la sentencia sql y la envio
                    this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM usuarios WHERE usuario='" + this.nombre+"' AND contrasena='" + this.password + "';");
                    this.rs = this.conexionPostgresql.getRs();
                    //Aqui se comprobara si la contraseña oicide con el usuario ingresado
                    String passwordExistente = "";
                    int idUsuarioExistente = 0;
                    while (this.rs.next()){
                        passwordExistente = this.rs.getString("contrasena");
                        idUsuarioExistente = this.rs.getInt("idUsuario");
                    }
                    //Verifico si coloco la contraseña correca
                    if (passwordExistente.equals(this.password)){
                        //System.out.println("Ingresaste a tu cuenta");
                        this.idUsuario = idUsuarioExistente;
                        Cuenta cuenta = new Cuenta(this.nombre,this.password,this.idUsuario);
                    }else if (!passwordExistente.equals(this.password)){
                        while (!passwordExistente.equals(this.password)){
                            System.out.println("La contraseña es incorrecta");
                            this.regresar();
                            System.out.println("Ingresa nuevamente la contraseña:");
                            this.password = this.usuario.next();
                            //Creo la sentencia sql y la envio
                            this.conexionPostgresql = new ConexionPostgresql("SELECT * FROM usuarios WHERE usuario='" + this.nombre+"' AND contrasena='" + this.password + "';");
                            this.rs = this.conexionPostgresql.getRs();
                            //Aqui se comprobara si la contraseña oicide con el usuario ingresado
                            while (this.rs.next()){
                                passwordExistente = this.rs.getString("contrasena");
                                idUsuarioExistente = this.rs.getInt("idUsuario");
                            }
                            //Verifico si coloco la contraseña correcta
                            if (passwordExistente.equals(this.password)){
                                //System.out.println("Ingresaste a tu cuenta");
                                this.idUsuario = idUsuarioExistente;
                                Cuenta cuenta = new Cuenta(this.nombre,this.password,idUsuarioExistente);
                            }
                        }
                    }
                }
            }
        }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void regresar(){
        System.out.println("¿Quieres regresar al inicio? (S/N)");
        char respuesta = this.usuario.next().charAt(0);
        if(respuesta == 'S'|| respuesta == 's'){
            Inicio inicio = new Inicio();
        }
    }
}
