/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;


import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * autores: @robert
 * 
 */
public class ComportamientoEmisor extends Behaviour {

    @Override
    public void action() {
        //ACCIONES DEL COMPORTAMIENTO
        System.out.println("Dentro de mi comportamiento: " + getAgent().getLocalName());

        //*************Enviar Mensaje**************//
        //crear el AID (instancia del agente identificador)
        AID id = new AID();
        id.setLocalName("Receptor1");
        //***********************************************
        //Construir el mensaje con ACLMessage (parametro tipo de msj)
        ACLMessage mensaje = new ACLMessage(ACLMessage.REQUEST);
        mensaje.setSender(getAgent().getAID());//quién envía el mensaje
        mensaje.setLanguage("Español");//idioma del mensaje
        mensaje.addReceiver(id);//quien recibe el mensaje
        mensaje.setContent("Hola Receptor. Cómo estas?");//contenido del mensaje

        getAgent().send(mensaje);//obtener el agente para enviar mensaje

        ACLMessage msj2 = getAgent().blockingReceive();

        System.out.println("El agente " + msj2.getSender().getLocalName()
                + " dice: " + msj2.getContent() + " y el Objeto es: ");

        block();
//        reset();
    }

    @Override
    public void setAgent(Agent a) {
        super.setAgent(a); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean done() {
        return false;
    }

}
