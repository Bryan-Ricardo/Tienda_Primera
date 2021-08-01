import java.util.Scanner;

public class Inicio {
    private Scanner usuario = new Scanner(System.in);
    Inicio(){
        System.out.println("/////////////VENTACIEN/////////////");
        System.out.println("Hola, te encuentras en el inicio del citio de ventas ");
        System.out.println("1-INICIAR SECION");
        System.out.println("2-REGISTRARSE");
        int tipoInicio = this.usuario.nextInt();
        if(tipoInicio == 1){
            this.iniciarSecion();
        }else if (tipoInicio == 2){
            this.registrarse();
        }else{
            while (tipoInicio!= 1 && tipoInicio != 2){
                System.out.println("/////////////VENTACIEN/////////////");
                System.out.println("Hola, te encuentras en el inicio del citio de ventas ");
                System.out.println("1-INICIAR SECION");
                System.out.println("2-REGISTRARSE");
                tipoInicio = this.usuario.nextInt();
                if (tipoInicio == 1){
                    this.iniciarSecion();
                }else if (tipoInicio == 2){
                    this.registrarse();
                }
            }
        }
    }
    private void iniciarSecion(){
        IniciarSecion iniciarSecion = new IniciarSecion();
    }

    private void registrarse(){
        Registrarse registrarse = new Registrarse();
    }

}
