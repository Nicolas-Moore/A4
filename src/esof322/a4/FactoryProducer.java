/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esof322.a4;

/**
 *
 * @author Nick
 */
public class FactoryProducer {
    public AdventureGameFactory getFactory(int choice){
   
      if(choice == 0){
         return new Lvl0Factory();
         
      }else if(choice == 1){
         return new Lvl1Factory();
      }
      
      return null;
   }
}
