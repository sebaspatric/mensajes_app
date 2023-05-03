/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mensajes_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author SMA
 */
public class MensajesDAO {
    public static void crearMemsajeDB(Mensajes mensaje){
        Conexion dbConnection = new Conexion();
        try(Connection conexion = dbConnection.get_connection()){
            PreparedStatement ps=null;
                try{
                    String query="INSERT INTO mensajes(mensaje, autor_mensaje) VALUES (?,?)";
                    ps=conexion.prepareStatement(query);
                    ps.setString(1, mensaje.getMensaje());
                    ps.setString(2, mensaje.getAutor_mensaje());
                    ps.executeUpdate();
                    System.out.println("mensaje creado");
                    
                }catch(SQLException e){
                    System.out.println(e);
                }
        } catch(SQLException e){
            System.out.println(e);
        }
                
        
    }
    public static ArrayList<Mensajes> leerMEnsajesDB(){
        Conexion dbConnection = new Conexion();
        
        ArrayList<Mensajes> mensajes = new ArrayList<Mensajes>();
        
        PreparedStatement ps=null;
        ResultSet rs = null; //crear los datos en filas y poderlos procesar
        try(Connection conexion = dbConnection.get_connection()){
            String query = "SELECT * FROM mensajes"; 
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery(); //ejecuta la consulta pero no tiene transaccion
            
            while(rs.next()){
                Mensajes mensaje = new Mensajes();
                mensaje.setId_mensaje(rs.getInt("id_mensaje"));
                mensaje.setMensaje(rs.getString("mensaje"));
                mensaje.setAutor_mensaje(rs.getString("autor_mensaje"));
                mensaje.setFecha_mensaje(rs.getString("fecha_mensaje"));
                mensajes.add(mensaje);             
            }
        } catch(SQLException e){
            System.out.println("no se pudieron recuperar los mensajes");
            System.out.println(e);
        }
        return mensajes;
    }
    
    /*
    public static void leerMEnsajesDB(){
      
        Conexion dbConnection = new Conexion();
        PreparedStatement ps=null;
        ResultSet rs = null; //crear los datos en filas y poderlos procesar
        try(Connection conexion = dbConnection.get_connection()){
            String query = "SELECT * FROM mensajes"; 
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery(); //ejecuta la consulta pero no tiene transaccion
            
            while(rs.next()){
                System.out.println("ID: "+rs.getInt("id_mensaje"));
                System.out.println("Mensaje: "+rs.getString("mensaje"));
                System.out.println("Autor: "+rs.getString("autor_mensaje"));
                System.out.println("Fecha: "+rs.getString("fecha_mensaje"));
                System.out.println("");
                
            }
            
      
        } catch(SQLException e){
            System.out.println("no se pudieron recuperar los mensajes");
            System.out.println(e);
        }
                
        
    }
        
    */
    
    public static void borrarMensajeDB(int idMensaje){
        
        Conexion dbConnection = new Conexion();
        try(Connection conexion = dbConnection.get_connection()){
            PreparedStatement ps=null;
                try{
                    String query = "DELETE FROM mensajes WHERE id_mensaje = ?";
                    ps = conexion.prepareStatement(query);
                    ps.setInt(1, idMensaje);
                    ps.executeUpdate();
                    System.out.println("el mensaje ha sido borrado");
                }catch(SQLException e){
                    System.out.println(e);
                    System.out.println("no se pudo borrar el mensaje");
                }
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    public static void actualizarMensajeDB(Mensajes mensaje){
         Conexion dbConnection = new Conexion();
         try(Connection conexion = dbConnection.get_connection()){
             PreparedStatement ps=null;
             try{
                 String query = "UPDATE mensajes SET mensaje = ? WHERE id_mensaje =?";
                 ps = conexion.prepareStatement(query);
                 ps.setString(1, mensaje.getMensaje());
                 ps.setInt(2, mensaje.getId_mensaje());
                 ps.executeUpdate();
                 System.out.println("mensaje se actualizó correctamente");
                 
             }catch(SQLException ex){
                 System.out.println(ex);
                 System.out.println("no se pudo actualizar el mensaje");
             }
              
             
         }catch(SQLException e){
            System.out.println(e);
        }
    }
}
