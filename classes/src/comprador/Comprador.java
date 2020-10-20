/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comprador;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
        
        
public class Comprador extends Agent {
    private String titulo;
    
    protected void setup(){
        
        System.out.println("Soy el agente comprado"+  getAID().getName() );
        Object[] arg =getArguments();
        
        if(arg != null && arg.length >0){
            titulo = (String) arg[0];
            System.out.println("intentar comnprar libro "+titulo);
            
            addBehaviour( new TickerBehaviour( this , 4000)  {
                protected void onTick(){
                    System.out.println("enviao mi peticion");
                }
            });
            
        }else{
            System.out.println("no se ha especificado lirbor");
            doDelete();

        }
        /*
        Comportamiento com = new Comportamiento();
        this.addBehaviour(com);*/
    }
}
