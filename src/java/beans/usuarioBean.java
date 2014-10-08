/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import jade.wrapper.ControllerException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Usuario;

/**
 *
 * @author Bolo
 */
@ManagedBean (name = "usuarioBeen")
@ViewScoped
public class usuarioBean {

    private List<Usuario> usuarios;
    private Usuario selectedUsuario;
    private final UsuarioDao usuarioDao;
    private boolean abrirUpdate;
    private boolean abrirCreate;
    private boolean abrirDelete;
    private FacesMessage msg;
    
    public usuarioBean() {
        this.usuarios = new ArrayList<>();
        this.abrirUpdate = false;
        this.abrirCreate = false;
        this.abrirDelete = false;
        usuarioDao = new UsuarioDaoImpl();
        selectedUsuario = new Usuario();
        msg = new FacesMessage();
    }

    public List<Usuario> getUsuarios() {
        this.usuarios = usuarioDao.findAll();
        return usuarios;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    @SuppressWarnings("empty-statement")
    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }
    
    public void guardarUsuario(){
        if(!validarUsuario()){
            this.abrirCreate = true;
            return;
        }
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe", "El usuario se guardó correctamente");
        usuarioDao.guardar(selectedUsuario);
        this.abrirCreate = false;
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void actualizarUsuario(){
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe", "El usuario se actualizó correctamente");
        usuarioDao.editarusuario(selectedUsuario);
        this.abrirUpdate = false;
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void eliminarUsuario(){
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe", "El usuario se eliminó correctamente");
        usuarioDao.eliminarusuario(selectedUsuario);
        this.abrirDelete = false;
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    private Boolean validarUsuario(){
        return !selectedUsuario.getUsuNombre().isEmpty() && !selectedUsuario.getUsuClave().isEmpty();
    }
    
    public void cancelar(){
        this.abrirUpdate = false;
    }
    
    public boolean getAbrirUpdate() {
        return abrirUpdate;
    }

    public void setAbrirUpdate(boolean abrirUpdate) {
        this.abrirUpdate = abrirUpdate;
    }

    public boolean getAbrirCreate() {
        return abrirCreate;
    }

    public void setAbrirCreate(boolean abrirCreate) {
        this.abrirCreate = abrirCreate;
        this.selectedUsuario = new Usuario();
    }

    public boolean isAbrirDelete() {
        return abrirDelete;
    }

    public void setAbrirDelete(boolean abrirDelete) {
        this.abrirDelete = abrirDelete;
    }
    
}
