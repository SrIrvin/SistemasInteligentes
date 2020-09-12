
package Agentes;
import jade.core.behaviours.Behaviour;

public class Comportamiento extends Behaviour {
    private int cont =0;
    @Override
    public void action() {
        System.out.println(cont);
        cont++;
    }    

    @Override
    public boolean done() {
        return cont>100;
    }
    
}
