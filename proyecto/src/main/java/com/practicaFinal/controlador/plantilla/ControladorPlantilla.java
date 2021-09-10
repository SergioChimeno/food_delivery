package com.practicaFinal.controlador.plantilla;

import com.practicaFinal.controlador.RolUsuario;
import com.practicaFinal.controlador.Utilidades.Utilidades;
import com.practicaFinal.modelo.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;


@Named
@ViewScoped
public class ControladorPlantilla implements Serializable{
    
    public static final String RAIZ="/PracticaFinal/";
    public static final String[][] MENU_USUARIO_SIN_LOGIN={{"Productos","pi pi-table",RAIZ+"productos.xhtml"}
            ,{"Restaurantes","pi pi-home",RAIZ+"restaurantes.xhtml"}};
    public static final String[][] MENU_USUARIO_CLIENTE={{"Productos","pi pi-table",RAIZ+"productos.xhtml"},
        {"Restaurantes","pi pi-home",RAIZ+"restaurantes.xhtml"},{"Perfil","pi pi-user",RAIZ+"privado/cliente/perfilCliente.xhtml"},
        {"Pedidos","pi pi-folder-open",RAIZ+"privado/cliente/misPedidos.xhtml"}};
    public static final String[][] MENU_USUARIO_RESTAURANTE={{"Restaurante","pi pi-user",RAIZ+"privado/restaurante/datosRestaurante.xhtml"}
            ,{"Productos","pi pi-bars",RAIZ+"privado/restaurante/productosRestaurante.xhtml"}
            ,{"Pedidos","pi pi-inbox",RAIZ+"privado/restaurante/pedidos.xhtml"}};
    public static final String[][] MENU_USUARIO_ADMIN={{"Clientes","pi pi-user",RAIZ+"privado/admin/adminClientes.xhtml"}
            ,{"Restaurantes","pi pi-inbox",RAIZ+"privado/admin/adminRestaurantes.xhtml"},{"Pedidos","pi pi-inbox",RAIZ+"privado/admin/adminPedidos.xhtml"}
            ,{"Categorias","pi pi-tag",RAIZ+"privado/admin/adminCategorias.xhtml"},{"Perfil","pi pi-id-card",RAIZ+"privado/admin/perfilAdmin.xhtml"}};

    private MenuModel modelo;
    private Usuario usuario;
    
    
    @PostConstruct
    public void init(){
        
        modelo=new DefaultMenuModel();
        usuario=Utilidades.getUsuario();
        
        if(usuario==null){
            anadirMenus(modelo,MENU_USUARIO_SIN_LOGIN);
        }else{
            
            RolUsuario rol=Utilidades.getRol();
            
            switch (rol) {
                case CLIENTE:
                    anadirMenus(modelo,MENU_USUARIO_CLIENTE);
                    break;
                case ADMIN:
                    anadirMenus(modelo,MENU_USUARIO_ADMIN);
                    break;
                case RESTAURANTE:
                    anadirMenus(modelo,MENU_USUARIO_RESTAURANTE);
                    break;
            }
        }
    }
    
    private static void anadirMenus(MenuModel modelo,String[][] menus){
        
        for(String[] datosMenu: menus){
            DefaultMenuItem m = DefaultMenuItem.builder()
                    .value(datosMenu[0])
                    .icon(datosMenu[1])
                    .url(datosMenu[2])
                    .build();

            modelo.getElements().add(m);
        }
    }

    public MenuModel getModelo() {
        return modelo;
    }

    public void setModelo(MenuModel modelo) {
        this.modelo = modelo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public boolean usuarioLogeado(){
        return usuario!=null;
    }
    
    public void logOut() throws IOException{
        Utilidades.borrarSesion();
        Utilidades.redireccion("?faces-redirect=true");
    }
    
    public boolean clienteLogeado(){
        return Utilidades.getRol()==RolUsuario.CLIENTE;
    }
    
    
    /**************************************
     *         VERIFICAR NAVEGACIÓN
     **************************************/
    public static final String PAGINA_ERROR="/PaginasError/Error404";
    public static final String[] PAGINAS_USUARIO_SIN_LOGIN={"/productos","/registro","/paginaProducto","/restaurantes","/paginaRestaurante"};
    public static final String[] PAGINAS_USUARIO_CLIENTE={"/productos","/paginaProducto","/paginaProducto","/restaurantes"
            ,"/paginaRestaurante","/privado/cliente/perfilCliente","/privado/cliente/tramitarPedido","/privado/cliente/misPedidos"};
    public static final String[] PAGINAS_USUARIO_RESTAURANTE={"/privado/restaurante/datosRestaurante"
            ,"/privado/restaurante/productosRestaurante","/privado/restaurante/pedidos"};
    public static final String[] PAGINAS_USUARIO_ADMINISTRADOR={"/privado/admin/adminClientes","/privado/admin/adminRestaurantes"
            ,"/privado/admin/adminPedidos","/privado/admin/adminCategorias","/privado/admin/perfilAdmin"
            ,"/privado/admin/adminDetallesPedido","/privado/admin/adminProductos"};
    
    public void verificarNavegacion() throws IOException{
                
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String servletPath=externalContext.getRequestServletPath();
                
        if(servletPath.contains(PAGINA_ERROR))
            return;
        
        if(usuario==null){
            
            if(!matchesString(servletPath,PAGINAS_USUARIO_SIN_LOGIN))
                Utilidades.redireccion(PAGINAS_USUARIO_SIN_LOGIN[0]+".xhtml");
            
        }else{
            RolUsuario rol=Utilidades.getRol();
            
            switch (rol) {
                case CLIENTE:
                    if(!matchesString(servletPath,PAGINAS_USUARIO_CLIENTE)){
                        Utilidades.redireccion(PAGINAS_USUARIO_CLIENTE[0]+".xhtml");
                    
                    }
                    break;
                case ADMIN:
                    if(!matchesString(servletPath,PAGINAS_USUARIO_ADMINISTRADOR))
                        Utilidades.redireccion(PAGINAS_USUARIO_ADMINISTRADOR[0]+".xhtml");
                    break;
                case RESTAURANTE:
                    if(!matchesString(servletPath,PAGINAS_USUARIO_RESTAURANTE))
                        Utilidades.redireccion(PAGINAS_USUARIO_RESTAURANTE[0]+".xhtml");
                    break;
                default:
                    Utilidades.redireccion(PAGINA_ERROR+".xhtml");
                    break;
            }
            
        }
    }
    
    private static boolean matchesString(String cadena,String[] listStrings){
        
        boolean contains=false;
        
        for(String s: listStrings)
            if(Pattern.matches(s+".*", cadena)){
                contains=true;
                break;
            }

        return contains;
    }
}
