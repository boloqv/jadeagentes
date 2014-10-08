/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contenedor;

import Agentes.Emisor1;
import Agentes.Receptor1;
import dao.AgenteDao;
import dao.AgenteDaoImpl;
import jade.core.Agent;
import jade.core.Location;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Agentes;

/**
 *
 * @author Ing. Henry P. Paz Arias Mgs. Especialista en Inteligencia Artificial
 * Skype: henry-patricio Correo: hpaz@unl.edu.ec
 */
public class ContenedorAgentes {

    //Controlador de Agentes
    AgentController agentControler;
    //Contenedor Principal agentes
    AgentContainer mainContainer;
    private AgenteDao agenteDao;
private Location destino;
    public void contenedorAgentes() {
        //instanciar el runtime para ejecutar el contenedor
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        runtime.setCloseVM(true);
        //crear un perfil por defecto para el runtime
        Profile profile = new ProfileImpl(null, 1099, null);
        //inicializar contenedor principal
        mainContainer = runtime.createMainContainer(profile);
    }

    public void iniciarAgentes() {
        System.out.println("Ingreso por aqui ");
        try {
            agenteDao = new AgenteDaoImpl();
            List<Agentes> listaAgentes = agenteDao.findAll();
            for (Agentes agentes : listaAgentes) {
                agentControler = mainContainer.createNewAgent(agentes.getAgeCodigo(), Emisor1.class.getName(), null);
                agentControler.start();
            }

            agentControler = mainContainer.createNewAgent("Emisor1", Emisor1.class.getName(), null);
            agentControler.start();
            agentControler = mainContainer.createNewAgent("Receptor1", Receptor1.class.getName(), null);
            agentControler.start();
           
           
//            jade.core.Runtime rt = jade.core.Runtime.instance();
//            Profile p = new ProfileImpl();
//            ContainerController cc = rt.createMainContainer(p);
// System.out.printf("contenedor" + cc.getContainerName());
//            System.out.printf("Plataforma:" + mainContainer.getName());
//            AgentController a = mainContainer.getAgent("Emisor1");
//            System.out.printf("agente encontrado:" + a.getName());
//a.clone(destino, "nuevo");
        } catch (StaleProxyException ex) {
            //Logger.getLogger(ContenedorAgentes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ControllerException ex) {
            Logger.getLogger(ContenedorAgentes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AgentContainer getMainContainer() {
        return mainContainer;
    }

    public AgentController getAgentControler() {
        return agentControler;
    }

   // public static void main(String[] args) {
    //  new ContenedorAgentes().iniciarAgentes();
    //}
}
