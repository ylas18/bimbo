/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.com.Controller;

import a.com.Bean.ProduccionFacadeLocal;
import a.com.Entity.Produccion;
import a.com.Entity.Usuario;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;
import org.primefaces.component.export.PDFOptions;

/**
 * Bean en cual se encarga de la session del Administrador
 *
 * @author Yesid
 */
@Named(value = "controllerAdmin")
@SessionScoped
public class ControllerAdmin implements Serializable {

    @EJB
    ProduccionFacadeLocal produccionFacadeLocal;

    private List<Produccion> listaProduccion;

    private PDFOptions pdfOpt;

    private Usuario per;
    private int numero1 = 100;
    private int numero2 = 100;
    private int numero3 = 100;
    private int numero4 = 100;
    private int numero5 = 100;
    private int amasado = 100;
    private int moldeado = 100;
    private int elaboracion = 100;
    private int enfriado = 100;
    private int empaque = 100;
    private int total_tiempo = 500;
    private int total_pan = 500;
    private int min_pan = 0;
    private int max_pan = 0;
    private int contador = 0;

    @PostConstruct
    public void init() {
        listaProduccion = produccionFacadeLocal.findAll();
        customizationOptions();

    }

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

    public void calcularTotal() {
        total_tiempo = amasado + elaboracion + empaque + enfriado + moldeado;
        total_pan = (500 - total_tiempo) + 500;
        System.out.println(total_pan);
    }

    public void calcularTiempoDescuento() {
        if (total_tiempo <= 0) {
            System.out.println("Acabe proceso");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrecto", "Datos Incorrectos"));
        } else {
            total_tiempo = total_tiempo - 1;
            contador++;
        }
    }

    public void empezarProduccion() {

        java.util.Date fecha = new Date();

        if (total_tiempo <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe iniciar algun proceso"));
        } else {

            Produccion produccion = new Produccion();
            produccion.setTiempoAmasado(amasado);
            produccion.setTiempoMoldeado(moldeado);
            produccion.setTiempoElaboracion(elaboracion);
            produccion.setTiempoEnfriado(enfriado);
            produccion.setTiempoEmpaque(empaque);
            produccion.setFecha(fecha);
            produccion.setCantidadPan(total_pan);

            produccionFacadeLocal.create(produccion);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Inicio de prodiccion exitoso"));
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "Imagenes" + File.separator + "pdf.png";

        pdf.add(Image.getInstance(logo));
    }

    public void customizationOptions() {
        pdfOpt = new PDFOptions();
        pdfOpt.setFacetBgColor("#036fab");
        pdfOpt.setFacetFontColor("#ffffff");
        pdfOpt.setFacetFontStyle("Arial");
        pdfOpt.setCellFontSize("12");

    }

    public void detenerAmasado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "A finalizado el proceso de Amasado"));
    }

    public void detenerMoldeado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "A finalizado el proceso de Moldeado"));
    }

    public void detenerElaboracion() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "A finalizado el proceso de Elaboracion"));
    }

    public void detenerEnfriado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "A finalizado el proceso de Enfriafo"));
    }

    public void detenerEmpaque() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "A finalizado el proceso de Empaque"));
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

    public ProduccionFacadeLocal getProduccionFacadeLocal() {
        return produccionFacadeLocal;
    }

    public void setProduccionFacadeLocal(ProduccionFacadeLocal produccionFacadeLocal) {
        this.produccionFacadeLocal = produccionFacadeLocal;
    }

    public int getTotal_tiempo() {
        return total_tiempo;
    }

    public void setTotal_tiempo(int total_tiempo) {
        this.total_tiempo = total_tiempo;
    }

    public int getTotal_pan() {
        return total_pan;
    }

    public void setTotal_pan(int total_pan) {
        this.total_pan = total_pan;
    }

    public int getMin_pan() {
        return min_pan;
    }

    public void setMin_pan(int min_pan) {
        this.min_pan = min_pan;
    }

    public int getMax_pan() {
        return max_pan;
    }

    public void setMax_pan(int max_pan) {
        this.max_pan = max_pan;
    }

    public List<Produccion> getListaProduccion() {
        return listaProduccion;
    }

    public void setListaProduccion(List<Produccion> listaProduccion) {
        this.listaProduccion = listaProduccion;
    }

    public PDFOptions getPdfOpt() {
        return pdfOpt;
    }

    public void setPdfOpt(PDFOptions pdfOpt) {
        this.pdfOpt = pdfOpt;
    }

}
