package ml.chromaryu.BattleArena.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by midgard on 16/11/23.
 * url = "jdbc:mysql://" + properties.getProperty("sql.url") + ":" + properties.getProperty("sql.port") + "/" + properties.getProperty("sql.db") + "?useUnicode=true&characterEncoding=utf-8";
 */
//
public class sqlapi {
    private String url,password,username,dbname;
    private Properties properties;
    private Connection connection;
    public sqlapi() {
        properties = new Properties();
        try {
            InputStream is = new FileInputStream(new File(System.getProperty("user.dir") + "/sql.properties"));
            properties.load(is);
        } catch(Exception e) {
            e.printStackTrace();
        }
        url = "jdbc:mysql://" + properties.getProperty("sql.url") + ":" + properties.getProperty("sql.port") + "/" + properties.getProperty("sql.db") + "?useUnicode=true&characterEncoding=utf-8";
        username = properties.getProperty("sql.username");
        password = properties.getProperty("sql.pass");

    }
    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public boolean maketable() {
        Connection connection = null;
        try {
            connection = open();
            PreparedStatement ps = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS CharDatabase" +
                            "(" +
                            "    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                            "    CharString VARCHAR(255)," +
                            "    UUID VARCHAR(36)," +
                            "    CreatedAt DATETIME," +
                            "    UpdatedAt DATETIME," +
                            "    Password VARCHAR(255)" +
                            ");"
            );
            ps.execute();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(connection);
        }
        return true;
    }

}
