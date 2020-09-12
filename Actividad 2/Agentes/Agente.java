package Agentes;

import jade.core.Agent;

public class Agente extends Agent{
    protected void setup(){
        Comportamiento com = new Comportamiento();
        this.addBehaviour(com);
    }
}
