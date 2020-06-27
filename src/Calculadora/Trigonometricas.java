/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Ralph
 */
public interface Trigonometricas extends Remote  {
    //Pones las operaciones que vas a usar en la interfaz y 
    public double seno(double x)throws RemoteException;
    public double coseno(double x)throws RemoteException;
    public double tangente(double x)throws RemoteException;
}
