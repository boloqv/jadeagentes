/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import BDI.Creencias;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @robert
 * 
 */
public class ComportamientoSimple extends Behaviour {

    @Override
    public void action() {
        //acciones

        //recibir un mensaje en el receptor
        //bloquear el agente hasta que llegue un mensaje
        ACLMessage mensaje = getAgent().blockingReceive();
        if (mensaje.getContent().equalsIgnoreCase("Hola Receptor. Cómo estas?")) {
            System.out.println("El agente " + mensaje.getSender().getLocalName()
                    + " dice: " + mensaje.getContent());
            ACLMessage mensaje2 = new ACLMessage(ACLMessage.INFORM);
            mensaje2.setSender(getAgent().getAID());
            mensaje2.addReceiver(mensaje.getSender());
            mensaje2.setLanguage("Español");
            mensaje2.setContent("deliberando");
            getAgent().send(mensaje2);
            Creencias creencias = new Creencias();
            System.out.println(creencias.buscarCreencia("lluviendo"));
            creencias.put("Llover", "Yo no lluviendo");
            System.out.println(creencias.buscarCreencia("lluviendo"));
        }
    }

    @Override
    public void setAgent(Agent a) {
        super.setAgent(a); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean done() {
        //terminado
        //si retorna true termina su comportamiento
        //retorna false continua su comportamiento
        return false;
    }

}
