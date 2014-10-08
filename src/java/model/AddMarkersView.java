/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import dao.AgenteDao;
import dao.AgenteDaoImpl;
import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
 
@ManagedBean (name="addMarkersView")
@ViewScoped
public class AddMarkersView implements Serializable {
     
    private MapModel emptyModel;
      
    private String title;
      
    private double lat;
      
    private double lng;
    
    private Agentes agente;
    
    private AgenteDao agenteDao;
    
    private Boolean abrirCreate;
    
    private List<String> tipoLuz;
    
    private List<String> tipoSentido;
    
    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
        agenteDao = new AgenteDaoImpl();
        abrirCreate = Boolean.FALSE;
        agente = new Agentes();
        agregarMarcadores();
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
    
    private void agregarMarcadores(){
        List<Agentes>  listaAgentes = agenteDao.findAll();
        LatLng coordenada;
        for (Agentes agentes : listaAgentes) {
            coordenada = new LatLng(agentes.getAgeLatitud(), agentes.getAgeLongitud());
            emptyModel.addOverlay(new Marker(coordenada, agentes.getAgeCodigo(), null, "http://www.kitlatren.com/tienda/images/semaforo.png"));
        }
    }
     public void mostrarpanel(PointSelectEvent event){
         LatLng latlng = event.getLatLng();
         setLng(latlng.getLng());
         setLat(latlng.getLat());
        setAbrirCreate(true);
//    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe", "Se ejecuto");
//     FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public MapModel getEmptyModel() {
        return emptyModel;
    }
      
    public String getTitle() {
        return title;
    }
  
    public void setTitle(String title) {
        this.title = title;
    }
  
    public double getLat() {
        return lat;
    }
  
    public void setLat(double lat) {
        this.lat = lat;
    }
  
    public double getLng() {
        return lng;
    }
  
    public void setLng(double lng) {
        this.lng = lng;
    }

    public Agentes getAgente() {
        return agente;
    }

    public void setAgente(Agentes agente) {
        this.agente = agente;
    }

    public Boolean getAbrirCreate() {
        return abrirCreate;
    }

    public void setAbrirCreate(Boolean abrirCreate) {
        this.abrirCreate = abrirCreate;
    }
    
    public void addMarker() {
        if(!validarAgenteMarcador()){
            return;
        }
        Marker marker = new Marker(new LatLng(lat, lng), title, null, "http://www.kitlatren.com/tienda/images/semaforo.png");
        emptyModel.addOverlay(marker);
        agente.setAgeLatitud(lat);
        agente.setAgeLongitud(lng);
        agenteDao.guardarAgente(agente);
        setAbrirCreate(Boolean.FALSE);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agente agregado", "Latitud:" + lat + ", Longitud:" + lng));
    }
    
    private Boolean validarAgenteMarcador(){
        return !agente.getAgeCodigo().isEmpty() || !agente.getAgeDireccion().isEmpty();
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
