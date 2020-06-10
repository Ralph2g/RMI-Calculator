/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;

import com.sun.security.ntlm.Server;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Ralph
 */
public class Server1 implements Arimeticas{
    public Server1(){}
    public int suma(int a, int b)throws RemoteException{
        return a+b;
    }
    
    public int resta(int a, int b){
        return a-b;
    }
    
    public Mensajes obtenerMensaje()throws RemoteException{
        Mensajes msj = new Mensajes("nada",1);
        return msj;
    }
    
//    public static void main(String args[]){
//        try{
//            java.rmi.registry.LocateRegistry.createRegistry(1099);//Puerto default del rmiregistry
//            System.out.println("RMI registry ready");        
//        }catch (Exception e){
//            System.out.println("Exception starting RMI registry:");
//            e.printStackTrace();
//        }//catch
//        
//        try{
//            System.setProperty("java.rmi.server.codebase", "http://8.25.100.18/clases/");
//            Server1 obj = new Server1();
//            Arimeticas stub = (Arimeticas)UnicastRemoteObject.exportObject(obj, 0);
//            
//            //Bind the remote objects stub in the registry
//            Registry registry = LocateRegistry.getRegistry();
//            registry.bind("Aritmetica", stub);
//            System.err.println("Servidor listo");
//        }catch(Exception e){
//            System.err.println("Server Exception" + e.toString());
//            e.printStackTrace();
//        }
//    }
}
