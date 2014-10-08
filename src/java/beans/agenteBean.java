/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AgenteDao;
import dao.AgenteDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Agentes;
import model.Usuario;

/**
 *
 * @author Bolo
 */
@ManagedBean (name = "agenteBeen")
@ViewScoped
public class agenteBean {
    
    private List<Agentes> agentes;
    private Agentes selectedAgente;
    private final AgenteDao agenteDao;
    private boolean abrirUpdate;
    private boolean abrirCreate;
    private boolean abrirDelete;
    private FacesMessage msg;
    private List<String> tipoLuz;
    private List<String> tipoSentido;
    
    public agenteBean() {
        this.agentes = new ArrayList<>();
        this.abrirUpdate = false;
        this.abrirCreate = false;
        this.abrirDelete = false;
        agenteDao = new AgenteDaoImpl();
        selectedAgente = new Agentes();
        msg = new FacesMessage();
        fijarTipoLuz();
        fijarTipoSentido();
    }
    
    private void fijarTipoLuz(){
        tipoLuz = new ArrayList<>();
        tipoLuz.add("LED");
        tipoLuz.add("INCANDESCENTE");
    }
    
    private void fijarTipoSentido(){
        tipoSentido = new ArrayList<>();
        tipoSentido.add("NORTE-SUR");
        tipoSentido.add("SUR-NORTE");
        tipoSentido.add("ESTE-OESTE");
        tipoSentido.add("OESTE-ESTE");
        tipoSentido.add("NOROESTE-SURESTE");
        tipoSentido.add("SURESTE-NOROESTE");
        tipoSentido.add("NORESTE-SUROESTE");
        tipoSentido.add("SUROESTE-NORESTE");
    }
    
    public List<Agentes> getAgentes() {
        this.agentes = agenteDao.findAll();
        return agentes;
    }

    public Agentes getSelectedAgentes() {
        return selectedAgente;
    }

    public void setSelectedAgentes(Agentes selectedAgente) {
        this.selectedAgente = selectedAgente;
    }
    public void btnDeleteAgentes(){
        
    }
    
    public void guardarAgente(){
        if(!validarAgente()){
            this.abrirCreate = true;
            return;
        }
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe", "El agente se guardó correctamente");
        agenteDao.guardarAgente(selectedAgente);
        this.abrirCreate = false;
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void actualizarAgente(){
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe", "El agente se actualizó correctamente");
        agenteDao.editarAgente(selectedAgente);
        this.abrirUpdate = false;
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void eliminarAgente(){
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe", "El agente se eliminó correctamente");
        agenteDao.eliminarAgente(selectedAgente);
        this.abrirDelete = false;
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    private Boolean validarAgente(){
        if(selectedAgente.getAgeCodigo().isEmpty()){
            return false;
        }
        if(selectedAgente.getAgeDireccion().isEmpty()){
            return false;
        }
        if(selectedAgente.getAgeLatitud() == 0.0){
            return false;
        }
        return selectedAgente.getAgeLongitud() != 0.0;
    }

    public boolean isAbrirUpdate() {
        return abrirUpdate;
    }

    public void setAbrirUpdate(boolean abrirUpdate) {
        this.abrirUpdate = abrirUpdate;
    }

    public boolean isAbrirCreate() {
        return abrirCreate;
    }

    public void setAbrirCreate(boolean abrirCreate) {
        this.abrirCreate = abrirCreate;
        this.selectedAgente = new Agentes();
    }

    public boolean isAbrirDelete() {
        return abrirDelete;
    }

    public void setAbrirDelete(boolean abrirDelete) {
        this.abrirDelete = abrirDelete;
    }

    public FacesMessage getMsg() {
        return msg;
    }

    public void setMsg(FacesMessage msg) {
        this.msg = msg;
    }

    public List<String> getTipoLuz() {
        return tipoLuz;
    }

    public void setTipoLuz(List<String> tipoLuz) {
        this.tipoLuz = tipoLuz;
    }
    
    public List<String> getTipoSentido() {
        return tipoSentido;
    }

    public void setTipoSentido(List<String> tipoSentido) {
        this.tipoSentido = tipoSentido;
    }
    
}