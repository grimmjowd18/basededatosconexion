package prueba;
//Importaciones
import java.sql.*;
import javax.swing.JOptionPane;

public class Main{
    
    private Connection conexion = null;
    public void estableceConexion()
    {
        if (conexion != null)
            return;
        String url = "jdbc:postgresql://localhost:5432/prueba";
        try
        {
           Class.forName("org.postgresql.Driver");
           conexion = DriverManager.getConnection(url,"postgres","ningendo18");
           if (conexion !=null){
               System.out.println("Conexión a base de datos ... Ok");
           }
        } catch (Exception e) {
            System.out.println("Problema al establecer la Conexión a la base de datos 1 ");
        }
    }
         public ResultSet dameNombre()
    {
        ResultSet rs = null;
        Statement s = null;
        try
        {
            s = conexion.createStatement();
            //seleccionamos la tabla de la base de datos la cual lleva por nombre trabajadores
            rs = s.executeQuery("SELECT * FROM  persona");
        }catch (Exception e)
        {
            System.out.println("Problema al consultar la base de datos 1 ");
        }
        return rs;
    }
public void cierraConexion()
    {
        try
        {
            conexion.close();
        }catch(Exception e)
        {
            System.out.println("Problema para cerrar la Conexión a la base de datos ");
        }
    }

    public static void main (String [] args)
    {
        
        Main x = new Main() ;
        ResultSet rs = null;
        String cadena="";

        x.estableceConexion();
        rs = x.dameNombre();
        try{
        while (rs.next())
        {
            //imprimimos todos los datos contenidos en la tabla
            cadena += rs.getString (1) + ", " + rs.getString(2) + ", " + rs.getString(3)+"\n";


        }

        JOptionPane.showMessageDialog(null, cadena, "prueba",1);
        } catch(Exception e)
        {
            System.out.println("Problema al imprimir la base de datos ");
        }
        x.cierraConexion();
    }

}