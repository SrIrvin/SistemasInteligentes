package agents;

import jade.core.Agent;
import behaviours.RequestPerformer;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import GUI.Interfaz;
import javax.swing.JOptionPane;

public class BookBuyerAgent extends Agent {
  private String bookTitle="#Esto esta imposible";
  private AID[] sellerAgents;
  private int ticker_timer = 10000;
  private BookBuyerAgent this_agent = this;
  private Interfaz gui;
  
  public void setbookTitle(String x){
      bookTitle=x;
  }
  
  protected void setup() {

    
    gui = new Interfaz(this);
    
      
    System.out.println("Buyer agent " + getAID().getName() + "Listo");
    
    Object[] args = getArguments();
    bookTitle  = JOptionPane.showInputDialog(this, "Que libro buscas");
    //if(args != null && args.length > 0) {
      //bookTitle = (String)args[0];
    if( ! bookTitle.equals("")){
      System.out.println("Book: " + bookTitle);
      addBehaviour(new TickerBehaviour(this, ticker_timer) {
        protected void onTick() {
         // System.out.println("Tratando de comprar" + bookTitle);
          if(!gui.isShowing() )
            gui.showGui();
          
          gui.agregar("Tratando de comprar" + bookTitle);
          gui.repaint();
          
          DFAgentDescription template = new DFAgentDescription();
          ServiceDescription sd = new ServiceDescription();
          sd.setType("book-selling");
          template.addServices(sd);
          
          
          
          try {
            DFAgentDescription[] result = DFService.search(myAgent, template);
            //System.out.println("Found the following seller agents:");
            gui.agregar("Se encontraron los siguintes agentes");
            gui.repaint();
            sellerAgents = new AID[result.length];
            for(int i = 0; i < result.length; i++) {
              sellerAgents[i] = result[i].getName();
              //System.out.println(sellerAgents[i].getName());
              gui.agregar(sellerAgents[i].getName());
              gui.repaint();
            }
            
          }catch(FIPAException fe) {
            fe.printStackTrace();
          }
          
          myAgent.addBehaviour(new RequestPerformer(this_agent));
        }
      });
    } else {
        
      System.out.println("No target book title specified");
      //doDelete();
    }
  }
  
  protected void takeDown() {
    JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
    gui.dispose();
    System.out.println("Buyer agent " + getAID().getName() + " terminating");
    
  }
  
  public AID[] getSellerAgents() {
    return sellerAgents;
  }
  
  public String getBookTitle() {
    return bookTitle;
  }
}