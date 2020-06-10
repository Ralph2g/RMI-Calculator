/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Ralph
 */
public class Server2 implements Trigonometricas{
    public Server2(){}
    //Insertas las opraciones Trigonometricas
    public int seno(int x, int y){
        return x/y;//agregar bien la función seno
    }
    
    
    //--------------NO TOCAR YA JALA----------------
    public static void main(String[] args){
        try{
            java.rmi.registry.LocateRegistry.createRegistry(1099);//Puerto default del rmiregistry
            System.out.println("Registro para los 2 servidores iniciado...");        
        }catch (Exception e){
            System.out.println("Exception starting RMI registry:");
            e.printStackTrace();
        }//catch
        
        try{
            System.setProperty("java.rmi.server.codebase", "http://8.25.100.18/clases/");
            Server1 obj = new Server1();
            Arimeticas stub = (Arimeticas)UnicastRemoteObject.exportObject(obj, 0);
            //Bind the remote objects stub in the registry
            Server2 obj2 = new Server2();
            Trigonometricas stub2 = (Trigonometricas)UnicastRemoteObject.exportObject(obj2, 0);
            //Bind the remote objects stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Aritmetica", stub);
            registry.bind("Trigonometricas", stub2);
            System.err.println("Servidores de operaciones basicas y trigonometricas listos");
        }catch(Exception e){
            System.err.println("Server Exception" + e.toString());
            e.printStackTrace();
        }
    }
}
