/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Ralph
 */

//AQUI INSERTARASA UN MENU PARA METER LA OPERACIÓN SE PROCESA DE MANERA POSTFIJA, INFIJA Y PREFIJA Y SOLO LAS MANDAS A LLAMAR DEPENDIENDO
// DEL STUB AL QUE SE ENCUENTRAN, EL STUB1 TIENE OPERACIONES BÁSICAS, TIENES QUE AGREGAR LAS QUE FALTAN
// EL STUB2 TIENE LAS ARITMETICAS FALTA COMPLETARLO :3 
//PD: MANEJA PUROS DOUBLE AHORITA TODO TIENE INT
public class Cliente {
    private Cliente(){}
    public static void main(String[] args){
        String host = (args.length< 1)? null: args[0];
        try{
            Registry registry = LocateRegistry.getRegistry(host);
            Arimeticas stub = (Arimeticas)registry.lookup("Aritmetica");
            int x = 6,y = 4;
            int response = stub.suma(x, y);
            int resta = stub.resta(x,y);
            Trigonometricas stub2 =(Trigonometricas)registry.lookup("Trigonometricas");
            int div = stub2.seno(x, y);
            Mensajes mensaje = stub.obtenerMensaje();
            System.out.println("Mensaje: "+ mensaje.m+" v: "+mensaje.v);
	    System.out.println("respuesta sumar "+x+" y "+y+" : " + response);
            System.out.println("Respuesta Restar "+x+" y "+y+" : " + resta);
            System.out.println("Respuesta Dividir "+x+" y "+y+" : " + div);
        }catch(Exception e){
            System.err.println("Client exception: " + e.toString());
	    e.printStackTrace();
        }
    }
}
