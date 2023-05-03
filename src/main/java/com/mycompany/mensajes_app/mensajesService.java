/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mensajes_app;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author SMA
 */
public class mensajesService {
    public static void crearMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("escribe un mensaje");
        String mensaje = sc.nextLine();
        
        System.out.println("tu nombre");
        String nombre = sc.nextLine();
        
        Mensajes registro = new Mensajes();
        registro.setMensaje(mensaje);
        registro.setAutor_mensaje(nombre);
        
        //objeto creado
        //enviarlo a la capa DAO
        MensajesDAO.crearMemsajeDB(registro);
        
        
        
    }
    public static void listarMEnsajes(){
        //MensajesDAO.leerMEnsajesDB();
        ArrayList<Mensajes> mensajes = MensajesDAO.leerMEnsajesDB();
        
        for (Mensajes mensaje: mensajes){
            System.out.println("id: "+mensaje.getId_mensaje());
            System.out.println("Mensaje: "+mensaje.getMensaje());
            System.out.println("Autor: "+mensaje.getAutor_mensaje());
            System.out.println("Fecha: "+mensaje.getFecha_mensaje());
        }
    }
    public static void borrarMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("indica el ID a borrar");
        int id_mensaje = sc.nextInt();
        MensajesDAO.borrarMensajeDB(id_mensaje);
        
        
        
    }
    public static void editarMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("escribe tu nuevo mensaje");
        String mensaje = sc.nextLine();
        
        System.out.println("indica el ID del mensaje a editar");
        int id_mensaje = sc.nextInt();
        Mensajes actualizacion = new Mensajes();
        actualizacion.setId_mensaje(id_mensaje);
        actualizacion.setMensaje(mensaje);
        MensajesDAO.actualizarMensajeDB(actualizacion);
        
    }
    
}
