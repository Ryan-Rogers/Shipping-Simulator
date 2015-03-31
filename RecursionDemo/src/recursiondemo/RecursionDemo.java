/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursiondemo;

/**
 *
 * @author mason
 */
public class RecursionDemo
{
    
    static double percentRemove;
    static int wordsAdd;
    
    static double f(int day)
    {
        if(day==0)
        {
            return 20;//words
        }
        
        return (1 - percentRemove) * f(day - 1) + wordsAdd; //words
    }
    
    static void test1()
    {
        percentRemove = .09;
        wordsAdd = 30;
                
        for(int i = 1; i < 1000; i++)
        {
            System.out.println("Day: " + i + " Characters: " + f(i) * 5);
            
            if(f(i) * 5 > 1000)
                break;
        }
    }
    
    public static void main(String[] args)
    {
        test1();
    }
    
}
