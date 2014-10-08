/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Usuario;

/**
 *
 * @author Usuario
 */
public interface UsuarioDao {
    public Usuario findByUsuario(Usuario usuario);
    public Usuario login(Usuario usuario);
    public List<Usuario> findAll();
    public void guardar(Usuario usuario);
    public void eliminarusuario(Usuario usuario);
    public void editarusuario (Usuario usuario);
}
