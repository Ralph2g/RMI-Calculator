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
import java.util.Stack;
public class prefix {
    public String convert(String expression){

        Stack<String> stack = new Stack<>();
        for (int i = expression.length()-1; i >=0 ; i--) {
            char c = expression.charAt(i);

            if(isOperator(c)){
                String s1 = stack.pop();
                String s2 = stack.pop();
                String temp = "("+s1+c+s2+")";
                stack.push(temp);
            }else{
                stack.push(c+"");
            }
        }

        String result=stack.pop();

        return result;
    }

    boolean isOperator(char x) {
        switch (x) {
            case '+':
            case '-':
            case '/':
            case '*':
                return true;
        }
        return false;
    }
}
