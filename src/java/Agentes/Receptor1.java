/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Comportamientos.ComportamientoSimple;
import jade.core.Agent;

/**
 *
 * @Bolo bolo bolo
 *
 */
public class Receptor1 extends Agent {

    @Override
    protected void setup() {
        super.setup(); //To change body of generated methods, choose Tools | Templates.
        ComportamientoSimple comportamientoSimple = new ComportamientoSimple();
        comportamientoSimple.setAgent(this);
        addBehaviour(comportamientoSimple);
    }

    @Override
    protected void takeDown() {
        super.takeDown(); //To change body of generated methods, choose Tools | Templates.
    }

}
