/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.StringTokenizer;

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
        Scanner scan = new Scanner(System.in);
        String host = (args.length< 1)? null: args[0];
        try{
            Registry registry = LocateRegistry.getRegistry(host);
            Arimeticas stub = (Arimeticas)registry.lookup("Aritmetica");
            Trigonometricas stub2 =(Trigonometricas)registry.lookup("Trigonometricas");
            String modo="";
            String operacion="";
            String aux="s";
            while(aux.equals("s")){
                System.out.println("Elija una opcion");
                System.out.println("1.- Infija");
                System.out.println("2.- Postfija");
                System.out.println("3.- Prefija");
                System.out.println("4.- Calculo de angulos");
                modo=scan.nextLine();
                if(modo.equals("1")){
                                System.out.println("Introduzca Expresion");
                                operacion=scan.nextLine();
                                System.out.println("La operacion es: "+operacion);
                                /////////////////////calculo////////////////////////
                                /**remove if any spaces from the expression**/
                                operacion = operacion.replaceAll("\\s+", "");
                                /**we assume that the expression is in valid format**/
                                MyStack<String> stack = new MyStack<String>(operacion.length());
                                /**break the expression into tokens**/
                                StringTokenizer tokens = new StringTokenizer(operacion, "{}()*/+-", true);
                                while(tokens.hasMoreTokens()){
                                    String tkn = tokens.nextToken();
                                    /**read each token and take action**/
                                    if(tkn.equals("(") 
                                            || tkn.equals("{")
                                            || tkn.matches("[0-9]+") 
                                            || tkn.equals("*")
                                            || tkn.equals("/")
                                            || tkn.equals("+")
                                            || tkn.equals("-")){
                                        /**push token to the stack**/
                                        stack.push(tkn);
                                    } else if(tkn.equals("}") || tkn.equals(")")){
                                        try {
                                            int op2 = Integer.parseInt(stack.pop()); 
                                            String oprnd = stack.pop();
                                            int op1 = Integer.parseInt(stack.pop());
                                            /**Below pop removes either } or ) from stack**/
                                            if(!stack.isStackEmpty()){
                                                stack.pop();
                                            }
                                            int result = 0;
                                            if(oprnd.equals("*")){
                                                result = stub.multiplicacion(op1,op2);
                                            } else if(oprnd.equals("/")){
                                                result = stub.division(op1,op2);
                                            } else if(oprnd.equals("+")){
                                                result = stub.suma(op1,op2);
                                            } else if(oprnd.equals("-")){
                                                result = stub.resta(op1,op2);
                                            }
                                            /**push the result to the stack**/
                                            stack.push(result+"");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            break;
                                        }
                                    }
                                }
                                String finalResult = "";
                                try {
                                    finalResult = stack.pop();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                        System.out.println(""+finalResult);
                    }else{
                        if(modo.equals("2")){
                            System.out.println("Introduzca Expresion");
                                String exp=scan.nextLine();
                                
                                infix in=new infix();
                                operacion=in.getInfix(exp);
                                System.out.println("La operacion es: "+operacion);
                                /////////////////////calculo////////////////////////
                                /**remove if any spaces from the expression**/
                                operacion = operacion.replaceAll("\\s+", "");
                                /**we assume that the expression is in valid format**/
                                MyStack<String> stack = new MyStack<String>(operacion.length());
                                /**break the expression into tokens**/
                                StringTokenizer tokens = new StringTokenizer(operacion, "{}()*/+-", true);
                                while(tokens.hasMoreTokens()){
                                    String tkn = tokens.nextToken();
                                    /**read each token and take action**/
                                    if(tkn.equals("(") 
                                            || tkn.equals("{")
                                            || tkn.matches("[0-9]+") 
                                            || tkn.equals("*")
                                            || tkn.equals("/")
                                            || tkn.equals("+")
                                            || tkn.equals("-")){
                                        /**push token to the stack**/
                                        stack.push(tkn);
                                    } else if(tkn.equals("}") || tkn.equals(")")){
                                        try {
                                            int op2 = Integer.parseInt(stack.pop()); 
                                            String oprnd = stack.pop();
                                            int op1 = Integer.parseInt(stack.pop());
                                            /**Below pop removes either } or ) from stack**/
                                            if(!stack.isStackEmpty()){
                                                stack.pop();
                                            }
                                            int result = 0;
                                            if(oprnd.equals("*")){
                                                result = stub.multiplicacion(op1,op2);
                                            } else if(oprnd.equals("/")){
                                                result = stub.division(op1,op2);
                                            } else if(oprnd.equals("+")){
                                                result = stub.suma(op1,op2);
                                            } else if(oprnd.equals("-")){
                                                result = stub.resta(op1,op2);
                                            }
                                            /**push the result to the stack**/
                                            stack.push(result+"");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            break;
                                        }
                                    }
                                }
                                String finalResult = "";
                                try {
                                    finalResult = stack.pop();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                        System.out.println(""+finalResult);
                        }else{
                            if(modo.equals("3")){
                                System.out.println("Introduzca Expresion");
                                String exp=scan.nextLine();
                                
                                prefix pre=new prefix();
                                operacion=pre.convert(exp);
                                System.out.println("La operacion es: "+operacion);
                                /////////////////////calculo////////////////////////
                                /**remove if any spaces from the expression**/
                                operacion = operacion.replaceAll("\\s+", "");
                                /**we assume that the expression is in valid format**/
                                MyStack<String> stack = new MyStack<String>(operacion.length());
                                /**break the expression into tokens**/
                                StringTokenizer tokens = new StringTokenizer(operacion, "{}()*/+-", true);
                                while(tokens.hasMoreTokens()){
                                    String tkn = tokens.nextToken();
                                    /**read each token and take action**/
                                    if(tkn.equals("(") 
                                            || tkn.equals("{")
                                            || tkn.matches("[0-9]+") 
                                            || tkn.equals("*")
                                            || tkn.equals("/")
                                            || tkn.equals("+")
                                            || tkn.equals("-")){
                                        /**push token to the stack**/
                                        stack.push(tkn);
                                    } else if(tkn.equals("}") || tkn.equals(")")){
                                        try {
                                            int op2 = Integer.parseInt(stack.pop()); 
                                            String oprnd = stack.pop();
                                            int op1 = Integer.parseInt(stack.pop());
                                            /**Below pop removes either } or ) from stack**/
                                            if(!stack.isStackEmpty()){
                                                stack.pop();
                                            }
                                            int result = 0;
                                            if(oprnd.equals("*")){
                                                result = stub.multiplicacion(op1,op2);
                                            } else if(oprnd.equals("/")){
                                                result = stub.division(op1,op2);
                                            } else if(oprnd.equals("+")){
                                                result = stub.suma(op1,op2);
                                            } else if(oprnd.equals("-")){
                                                result = stub.resta(op1,op2);
                                            }
                                            /**push the result to the stack**/
                                            stack.push(result+"");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            break;
                                        }
                                    }
                                }
                                String finalResult = "";
                                try {
                                    finalResult = stack.pop();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                        System.out.println(""+finalResult);
                        }else{
                            if(modo.equals("4")){
                                System.out.println("Introduzca una opcion");
                                        System.out.println("1.- Seno");
                                        System.out.println("2.- Coseno");
                                        System.out.println("3.- Tangente");
                                        System.out.println("4.- Secante");
                                        System.out.println("5.- Cosecante");
                                        System.out.println("6.- Cotangente");
                                        operacion=scan.nextLine();
                                        System.out.println("Introduzca el angulo");
                                        double angulo=scan.nextDouble();
                                        switch(operacion){
                                            case "1":
                                                        System.out.println("El seno de "+angulo+" es: "+stub2.seno(angulo)); 
                                                break;
                                            case "2":
                                                        System.out.println("El coseno de "+angulo+" es: "+stub2.coseno(angulo));
                                                break;
                                            case "3":
                                                        System.out.println("La tangente de "+angulo+" es: "+stub2.tangente(angulo));
                                                break;
                                            case "4":
                                                        System.out.println("La secante de "+angulo+" es: "+(1/(stub2.coseno(angulo))));
                                                break;
                                            case "5":
                                                        System.out.println("La cosecante de "+angulo+" es: "+(1/(stub2.seno(angulo))));
                                                break;
                                            case "6":
                                                        System.out.println("La cotangente de "+angulo+" es: "+(1/(stub2.tangente(angulo))));
                                                break;
                                            default:
                                        }
                            }
                        }
                    }
                }
                System.out.println("Desea continuar? s/n");
                aux=scan.nextLine();
              }
            }
            catch(Exception e){
            System.err.println("Client exception: " + e.toString());
	    e.printStackTrace();
        }
    }
    
}
