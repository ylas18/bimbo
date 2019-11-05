/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.com.Controller;

import a.com.Bean.UsuarioFacadeLocal;
import a.com.Entity.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yesid
 */
@Named(value = "controllerLogin")
@SessionScoped
public class ControllerLogin implements Serializable {
    /*
     Creamos la el objeto UsuarioFacadeLocal para realziar la concexion al EJB
     */

    @EJB(beanName = "UsuarioFacade")
    private UsuarioFacadeLocal usuarioFacadeLocal;
    /**
     * Creamos el objeto Persona para enviar los datos al EJB
     */
    private Usuario usuario;
    /*
     Creamos el objeto persona para poder realizar las validaciones del rol con lo que viene del EJB
     */
    private Usuario usu;
    /*
     Lectura del UsuarioFacadeLocal
     */

    public UsuarioFacadeLocal getUsuarioFacadeLocal() {
        return usuarioFacadeLocal;
    }

    /**
     * Escritura del UsuarioFacadeLocal
     *
     * @param usuarioFacadeLocal
     */
    public void setUsuarioFacadeLocal(UsuarioFacadeLocal usuarioFacadeLocal) {
        this.usuarioFacadeLocal = usuarioFacadeLocal;
    }

    /**
     * Lectura del objeto Persona
     *
     * @return
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Escritura del objeto Persona
     *
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Lectura del objeto Persona
     *
     * @return
     */
    public Usuario getUsu() {
        return usu;
    }

    /**
     * Escritura del objeto Persona
     *
     * @param usu
     */
    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    /**
     * Post Constuct que inicializa los objetos para poder usarlos
     */
    @PostConstruct
    public void init() {
        usuario = new Usuario();
        usu = new Usuario();
    }

    public ControllerLogin() {
    }

    /**
     * Metodo el cual realiza el Login. Hacemos la concexion a base de datos por
     * medio del EJB el cual nos retorna un objeto de tipo Persona y se valida
     * si esta vacio. Al no ser asi creamos la session y validamos el rol para
     * redireccion del usuario como tal
     *
     * @return
     */
    public String empezarLogin() {
        String redireccion = "index";
        try {
            usu = usuarioFacadeLocal.Login(usuario);
            if (usu != null) {
                FacesContext fCtx = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
                session.setAttribute("usuario", usu);
                if (usu.getRol() == 1) {
                    redireccion = "administrador";
                } else {
                    redireccion = "calidad";
                }
            } else {
                System.out.println("usuario invalido");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrecto", "Datos Incorrectos"));
            }
        } catch (Exception e) {
        }
        return redireccion;
    }

}
