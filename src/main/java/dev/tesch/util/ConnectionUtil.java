package dev.tesch.util;

import java.io.*;
import java.sql.*;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionUtil {
    private static Connection connectionInstance = null;
    private static final Logger log = LogManager.getLogger(ConnectionUtil.class);

    private ConnectionUtil() {

    }

     public static Connection getConnection() {
         if (ConnectionUtil.connectionInstance != null) {
             return ConnectionUtil.connectionInstance;
         }

         InputStream in = null;

         try {
             // load information from properties file
             Properties props = new Properties();
             in = new FileInputStream(
                     "/Users/tesch/Documents/Documents - Sean’s MacBook Pro/Projects/Java/Bank App/src/main/resources/connection.properties");
             props.load(in);

             // get the connection object
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con = null;

             String endpoint = props.getProperty("jdbc.url");
             String username = props.getProperty("jdbc.username");
             String password = props.getProperty("jdbc.password");

             con = DriverManager.getConnection(endpoint, username, password);
             connectionInstance = con;
             return connectionInstance;
         } catch (Exception ex) {
             log.error("Unable to get connection to database");
             System.out.println("SQLException: " + ex.getMessage());
         } finally {
             try {
                 in.close();
             } catch (IOException e) {
                 System.out.println("Couldn't close connection " + e);
             }
         }
         return null;
     }
}
