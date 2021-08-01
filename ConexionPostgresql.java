import java.sql.*;

public class ConexionPostgresql {
    private String sql;
    private ResultSet rs = null;

    ConexionPostgresql(){

    }

    ConexionPostgresql(String sql){
        this.sql = sql;
        //Parametros de la conexion
        Connection BaseDatos = null;
        Statement st = null;

        //Donde se localiza la base de datos
        String url="jdbc:postgresql://localhost:5432/Tienda";

        //Credenciales de la base de datos
        String usuario="rinux";
        String contrasena="RIfardo999";
        PreparedStatement pstm = null;
        Connection con = null;
        try {
            //Conexion con la base de datos
            con = DriverManager.getConnection(url, usuario, contrasena);
            //System.out.println("Conectado");
            /*//executar query
            //defino el query
            String sql = "SELECT nombre FROM ejemplo WHERE id = 1;";*/
            //preparo la sentencia que voy a ejecutar
            pstm = con.prepareStatement(sql);
            //ejecuto la sentencia y obtengo los resultados en rs
            this.rs = pstm.executeQuery();
            /*while (rs.next()){
                    String reigistros = rs.getString("nombre");
                    System.out.println(reigistros);
            }*/
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void ejecutarSql(String getSql)throws Exception{
        //Donde se localiza la base de datos
        String url="jdbc:postgresql://localhost:5432/Tienda";
        //Credenciales de la base de datos
        String usuario="rinux";
        String contrasena="RIfardo999";
        PreparedStatement pstm = null;
        Connection con = null;
        try {
            //Conexion con la base de datos
            con = DriverManager.getConnection(url, usuario, contrasena);
            //System.out.println("Conectado");
            /*//executar query
            //defino el query
            String sql = "SELECT nombre FROM ejemplo WHERE id = 1;";*/
            //preparo la sentencia que voy a ejecutar
            pstm = con.prepareStatement(getSql);
            //ejecuto la sentencia y obtengo los resultados en rs
            this.rs = pstm.executeQuery();
            /*while (rs.next()){
                    String reigistros = rs.getString("nombre");
                    System.out.println(reigistros);
            }*/
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //SETERS AND GETTERS
    public ResultSet getRs(){
        return (ResultSet) this.rs;
    }
    public void setRs(ResultSet rs){
        this.rs = rs;
    }
}

