/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Agentes;

/**
 *
 * @author Usuario
 */
public interface AgenteDao {
    public Agentes findByUsuario(Agentes agente);
    public Agentes login(Agentes agente);
    public List<Agentes> findAll();
    public void guardarAgente(Agentes agente);
    public void eliminarAgente(Agentes agente);
    public void editarAgente(Agentes agente);
}
