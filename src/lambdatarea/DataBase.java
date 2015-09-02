/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lambdatarea;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.h2.jdbcx.JdbcDataSource;

/**
 *
 * @author Ivan
 */
public class DataBase {
    public static void main(String[] args) {
        String database = "database";
        String user = "sa";
        String password = "";
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
        }
        JdbcDataSource h2DataSource = new JdbcDataSource();
        String userDir = System.getProperty("user.dir");
        h2DataSource.setURL("jdbc:h2:file:" + userDir + "/" + database);
        h2DataSource.setUser(user);
        h2DataSource.setPassword(password);
        
        Connection cn;
        
        try {
            cn = h2DataSource.getConnection();
            Statement createStatement = cn.createStatement();
            //createStatement.execute("Create Table productos ( id_clave INT(4) NOT NULL AUTO_INCREMENT, producto varchar(30) NOT NULL, descripcion varchar(30), precio INT(10) NOT NULL, clasificacion varchar(30) NOT NULL, existencia INT(10) NOT NULL, minimo INT(10) NOT NULL, maximo INT(10) NOT NULL, CONSTRAINT pk_id PRIMARY KEY (id_clave) );");
            //ResultSet query = createStatement.executeQuery("Select * from rol;");
//            while (query.next()) {  
//                int id_rol = query.getInt("id_rol");
//                String nombre = query.getString("nombre_rol");
//                System.out.println("ID"+id_rol+"Nombre"+nombre);
//            }
            
            createStatement.executeUpdate("INSERT INTO productos( producto,descripcion,precio,clasificacion,existencia,minimo,maximo) VALUES ('cachucha','plana',120,'ropa',50,1,20)");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
