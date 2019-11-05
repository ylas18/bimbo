/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.com.Controller;

import a.com.Entity.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

/**
 * Bean en cual se encarga de la session del Administrador
 *
 * @author Yesid
 */
@Named(value = "controllerAdmin")
@SessionScoped
public class ControllerAdmin implements Serializable {

    /*
     Creamos el objeto persona para validar la session
     */
    private Usuario per;
    private int numero1;
    private int numero2;
    private int numero3;
    private int numero4;
    private int numero5;
    private int amasado;
    private int moldeado;
    private int elaboracion;
    private int enfriado;
    private int empaque;
    private int total;

    /**
     * Metodo el cual valida la session. Valida si la persona tiene session o
     * no, para el rol Administrador
     *
     * @return Retorna un String con el nombre del xhtml al cual se redirecciona
     */
    public String validarSession() {
        String redireccion = "administrador";
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);

        try {
            per = (Usuario) session.getAttribute("usuario");
            System.out.println("sesssssiooo" + session);

        } catch (Exception e) {
        }

        if (per != null) {
            redireccion = "index";
        }
        return redireccion;
    }

    /**
     * Metodo el cual destruye la session del usuario Administrador
     *
     * @return Retorna el String con el nombre del index para redireccionar al
     * index. xhtml
     */
    public String cerrarSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        System.out.println("Session terminada");
        return "index";

    }

    public void onInputChangedAmasado(ValueChangeEvent event) {
        FacesMessage message = new FacesMessage("Entrada Amasado", "Tiempo: " + event.getNewValue() + " Minutos");
        amasado = (int) event.getNewValue();
        System.out.println(amasado);
        calcularTotal();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onInputChangedMoldeado(ValueChangeEvent event) {
        FacesMessage message = new FacesMessage("Entrada Moldeado", "Tiempo: " + event.getNewValue() + " Minutos");
        moldeado = (int) event.getNewValue();
        System.out.println(moldeado);
        calcularTotal();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onInputChangedElaboracion(ValueChangeEvent event) {
        FacesMessage message = new FacesMessage("Entrada Elaboración", "Tiempo: " + event.getNewValue() + " Minutos");
        elaboracion = (int) event.getNewValue();
        System.out.println(elaboracion);
        calcularTotal();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onInputChangedEnfriado(ValueChangeEvent event) {
        FacesMessage message = new FacesMessage("Entrada Enfriado", "Tiempo: " + event.getNewValue() + " Minutos");
        enfriado = (int) event.getNewValue();
        System.out.println(enfriado);
        calcularTotal();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onInputChangedEmpaque(ValueChangeEvent event) {
        FacesMessage message = new FacesMessage("Entrada Empaque", "Tiempo: " + event.getNewValue() + " Minutos");
        empaque = (int) event.getNewValue();
        System.out.println(empaque);
        calcularTotal();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public int calcularTotal() {
        total = amasado + elaboracion + empaque + enfriado + moldeado;
        return total;

    }

    public void calcularTiempoDescuento() {
        if (total <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrecto", "Datos Incorrectos"));
        } else {
            total = total - 1;
        }
    }

    public void empezarProduccion() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Inicio de prodiccion exitoso"));
    }

    public ControllerAdmin() {
    }

    public Usuario getPer() {
        return per;
    }

    public void setPer(Usuario per) {
        this.per = per;
    }

    public int getNumero1() {
        return numero1;
    }

    public void setNumero1(int numero1) {
        this.numero1 = numero1;
    }

    public int getNumero2() {
        return numero2;
    }

    public void setNumero2(int numero2) {
        this.numero2 = numero2;
    }

    public int getNumero3() {
        return numero3;
    }

    public void setNumero3(int numero3) {
        this.numero3 = numero3;
    }

    public int getNumero4() {
        return numero4;
    }

    public void setNumero4(int numero4) {
        this.numero4 = numero4;
    }

    public int getNumero5() {
        return numero5;
    }

    public void setNumero5(int numero5) {
        this.numero5 = numero5;
    }

    public int getAmasado() {
        return amasado;
    }

    public void setAmasado(int amasado) {
        this.amasado = amasado;
    }

    public int getMoldeado() {
        return moldeado;
    }

    public void setMoldeado(int moldeado) {
        this.moldeado = moldeado;
    }

    public int getElaboracion() {
        return elaboracion;
    }

    public void setElaboracion(int elaboracion) {
        this.elaboracion = elaboracion;
    }

    public int getEnfriado() {
        return enfriado;
    }

    public void setEnfriado(int enfriado) {
        this.enfriado = enfriado;
    }

    public int getEmpaque() {
        return empaque;
    }

    public void setEmpaque(int empaque) {
        this.empaque = empaque;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
