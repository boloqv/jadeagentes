/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDI;

import java.util.HashMap;

/**
 *
 * @author Ing. Henry P. Paz Arias Mgs. Especialista en Inteligencia Artificial
 * Skype: henry-patricio Correo: hpaz@unl.edu.ec
 */
public final class Creencias extends HashMap<Object, Object> {

    public void creenciasIniciales() {
        put("Frío", "Esta mucho frío");
        put("Viento", "Existe mucho viento");
        put("Llover", "Esta lluviendo");
    }

    public Creencias() {
        creenciasIniciales();
    }

    @Override
    public Object put(Object key, Object value) {
        return super.put(key, value); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(Object key) {
        return super.get(key); //To change body of generated methods, choose Tools | Templates.
    }

    public Object buscarCreencia(String creencia) {
        Object creencia_E = null;
        for (Entry<Object, Object> entry : this.entrySet()) {
            Object object = entry.getKey();
            Object object1 = entry.getValue();
            if (object1.toString().contains(creencia)) {
                creencia_E = object1;
                break;

            }
        }
        return creencia_E;
    }

//    public static void main(String[] args) {
//        new Creencias().buscarCreencia("lluviendo");
//    }
}
