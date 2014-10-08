/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import util.MyUtil;

/**
 *
 * @author Usuario
 */
@Named(value = "mapaBean")
@SessionScoped
public class mapaBean implements Serializable{

    /**
     * Creates a new instance of appBean
     */
    public mapaBean() {
    }
    
   public void onStateChange(StateChangeEvent event) {
        LatLngBounds bounds = event.getBounds();
        int zoomLevel = event.getZoomLevel();
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Zoom Level", String.valueOf(zoomLevel)));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Center", event.getCenter().toString()));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "NorthEast", bounds.getNorthEast().toString()));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "SouthWest", bounds.getSouthWest().toString()));
    }
      
    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
    }
      
    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
