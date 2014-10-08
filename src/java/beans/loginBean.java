/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import jade.wrapper.ControllerException;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.Usuario;
import org.primefaces.context.RequestContext;
import util.MyUtil;

/**
 *
 * @author Usuario
 */
@Named(value = "loginBean")
@SessionScoped
@ManagedBean
public class loginBean implements Serializable {

    private Usuario usuario;
    private final UsuarioDao usuariodao;
    Contenedor.ContenedorAgentes contentAgent = new Contenedor.ContenedorAgentes();

    public void ejecutarAgentes() {
        contentAgent.contenedorAgentes();
        contentAgent.iniciarAgentes();
    }

    public void emitirMsj() {
        try {
            contentAgent.getMainContainer().getAgent("Emisor1").activate();
        } catch (ControllerException ex) {
            Logger.getLogger(loginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public loginBean() {
        this.usuariodao = new UsuarioDaoImpl();
        if (this.usuario == null) {
            this.usuario = new Usuario();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        boolean loggedIn;
        String ruta = "";

        this.usuario = this.usuariodao.login(this.usuario);

        if (this.usuario != null) {
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getUsuNombre());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getUsuNombre());
            ruta = MyUtil.basepathlogin() + "views/inicio.xhtml";
            //ejecutarAgentes();
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Usuario y/o contraseña incorrecta");
            if (this.usuario == null) {
                this.usuario = new Usuario();
            }
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);
        
    }
    
    public void logout(){
        String ruta = MyUtil.basepathlogin()+"login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
        sesion.invalidate();
        
        context.addCallbackParam("loggetOut", true);
        context.addCallbackParam("ruta", ruta);
    }
}
