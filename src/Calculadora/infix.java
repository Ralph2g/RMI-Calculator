/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;

/**
 *
 * @author luise
 */

import java.util.*; 
  
class infix { 
      
static boolean isOperand(char x){ 
    return (x >= '0' && x <= '9');
} 
  
// Get Infix for a given postfix 
// expression 
static String getInfix(String exp) 
{ 
    Stack<String> s = new Stack<String>(); 
  
    for (int i = 0; i < exp.length(); i++) 
    { 
        // Push operands 
        if (isOperand(exp.charAt(i))) 
        { 
        s.push(exp.charAt(i) + ""); 
        } 
  
        // We assume that input is 
        // a valid postfix and expect 
        // an operator. 
        else
        { 
            String op1 = s.peek(); 
            s.pop(); 
            String op2 = s.peek(); 
            s.pop(); 
            s.push("(" + op2 + exp.charAt(i) + 
                    op1 + ")"); 
        } 
    } 
    // There must be a single element 
    // in stack now which is the required 
    // infix. 
    return s.peek(); 
} 
  
// Driver code 
    public static void main(String args[]){ 
        String exp = "12*3+";
        System.out.println(getInfix(exp)); 
    }
} 

