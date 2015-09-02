
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.h2.jdbcx.JdbcDataSource;

/**
 *
 * @author Ivan Gil Najera */

public class LambdaTarea {

   
   
    public static void main(String[] args) {
        List<Producto> producto = new ArrayList<>();
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
            //createStatement.execute("Create Table rol ( id_rol INT(4) NOT NULL AUTO_INCREMENT, nombre_rol varchar(30))");
            ResultSet query = createStatement.executeQuery("Select * from productos;");

            while (query.next()) {
                
                int id_clave = query.getInt("id_clave");
                String prod = query.getString("producto");
                String descripcion = query.getString("descripcion");
                int precio = query.getInt("precio");
                String clasificacion = query.getString("clasificacion");
                int existencia = query.getInt("existencia");
                int min = query.getInt("minimo");
                int max = query.getInt("maximo");
                
                
                
                
                
                Producto prduct = new Producto(id_clave,prod,descripcion, precio,clasificacion, existencia, max, min);
                producto.add(prduct);
            }

            // createStatement.executeUpdate("INSERT INTO productos (producto,descripcion,precio,clasificacion,existencia,minimo,maximo) VALUES ('Pantalon','Pantalon azul',100,'Ropa',10,1,50);");
        } catch (SQLException ex) {
            Logger.getLogger(LambdaTarea.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Primer Ejercicio ");
        producto.stream()
                .filter(p -> p.getExistencia() > 20)
                .forEach(p -> System.out.println(p));
        
        System.out.println("Segundo Ejercicio ");
        producto.stream()
                .filter(p -> p.getExistencia() < 15)
                .forEach(p -> System.out.println(p));
        
        System.out.println("Tercer Ejercicio ");
        String[] clas = {"Ropa","Electronica","Ferreteria"};
        for (String cla : clas) {
            producto.stream()
                    .filter(p -> p.getClasificacion().equals(cla))
                    .filter(p -> p.getPrecio() > 15)
                    .forEach(p -> System.out.println(p));
        }
        for (String cla : clas) {
            List<String> clasi = producto.stream()
                    .map(p -> p.getClasificacion())
                    .collect(Collectors.toList());
            for (String clasi1 : clasi) {
                long count = producto.stream()
                    .filter(p -> p.getClasificacion().equals(cla))
                    .count();
                    System.out.printf("%s , %d \n", clasi1, count);
            }
        }
        
        System.out.println("Cuarto Ejercicio ");
        producto.stream()
                .filter(p -> p.getPrecio()> 20.30 && p.getPrecio() < 45)
                .forEach(p -> System.out.println(p));
        
        
    }
    
    
    
    

    public static class Producto {

        private int id_clave;
        private String producto;
        private String descripcion;
        private int precio;
        private String clasificacion;
        private int existencia;
        private int max;
        private int min;

        public Producto(int id, String producto, String descripcion, int precio, String clasificacion, int existencia, int max, int min) {
            this.id_clave = id_clave;
            this.producto = producto;
            this.descripcion = descripcion;
            this.precio = precio;
            this.clasificacion = clasificacion;
            this.existencia = existencia;
            this.max = max;
            this.min = min;
        }
        
        @Override
        public String toString() {
            return String.format("%s %s Tiene %d",
                    this.producto, this.descripcion, this.precio);
        }
        
        public int getId_clave() {
            return id_clave;
        }

        public void setId_clave(int id_clave) {
            this.id_clave = id_clave;
        }

        public String getProducto() {
            return producto;
        }

        public void setProducto(String producto) {
            this.producto = producto;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public int getPrecio() {
            return precio;
        }

        public void setPrecio(int precio) {
            this.precio = precio;
        }

        public String getClasificacion() {
            return clasificacion;
        }

        public void setClasificacion(String clasificacion) {
            this.clasificacion = clasificacion;
        }

        public int getExistencia() {
            return existencia;
        }

        public void setExistencia(int existencia) {
            this.existencia = existencia;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        

    }

}
