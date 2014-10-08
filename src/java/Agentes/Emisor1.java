/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Comportamientos.ComportamientoEmisor;
import jade.core.AID;
import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ing. Henry P. Paz Arias Mgs. Especialista en Inteligencia Artificial
 * Skype: henry-patricio Correo: hpaz@unl.edu.ec
 */
public class Emisor1 extends Agent {
Runtime rt= Runtime.instance();
        Profile p = new ProfileImpl();
        ContainerController cc = rt.createMainContainer(p);
    //inicializar agentes
    @Override
    protected void setup() {
        
        try {
          
            
            System.out.println("Obteneniendo el contededor con los ajentes " + cc.getContainerName()+"");
//           AgentController agc =cc.getAgent("Receptor1",true);
//            Vector GroupOfAgents = new Vector();
//            GroupOfAgents.addElement(agc);
//           Agent agen = (Agent) GroupOfAgents.get(0);
//            System.out.println("Obteneniendo ajentes " + agen.getName()+"");
        } catch (ControllerException ex) {
            Logger.getLogger(Emisor1.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Hola. Yo soy " + this.getLocalName());
        ComportamientoEmisor comportamientoEmisor = new ComportamientoEmisor();
        comportamientoEmisor.setAgent(this);
        addBehaviour(comportamientoEmisor);

        super.setup(); //To change body of generated methods, choose Tools | Templates.

    }

    //matar agente
    @Override
    protected void takeDown() {
        super.takeDown(); //To change body of generated methods, choose Tools | Templates.
    }

}
