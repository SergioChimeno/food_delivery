package com.practicaFinal.controlador.Utilidades;

import com.practicaFinal.modelo.Direccion;
import com.practicaFinal.modelo.Pedido;
import com.practicaFinal.modelo.Restaurante;
import com.practicaFinal.modelo.TipoRecogida;
import java.util.Properties;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Singleton
public class JavaMailService {

    private static final String DIRECCION_EMISOR="**********@gmail.com";
    private static final String CONTRASENA="***********";
    
    public static final String CABECERA_MAILS="<h1 style='"
                +"font-size: 50px;font-weight: bold;margin: 0px;padding: 0px;color: #D20005;border-bottom: 1px solid #cbcccc;"
                + "'>Food delivery</h1>";
    private static final String INICIO_PARRAFO="<p style='font-size:19px;'>";
    private static final String FIN_PARRAFO="</p>";
    
    
    private static void enviarMail(String mensaje,String asunto,String destinatario){
        
            Properties propiedades=new Properties();
            propiedades.put("mail.smtp.auth","true");
            propiedades.put("mail.smtp.starttls.enable","true");
            propiedades.put("mail.smtp.host","smtp.gmail.com");
            propiedades.put("mail.smtp.port","587");
            
            Session sesion=Session.getDefaultInstance(propiedades);
            
            MimeMessage mail=new MimeMessage(sesion);

            try{
                mail.setFrom(new InternetAddress(DIRECCION_EMISOR));
                mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
                mail.setSubject(asunto);
                mail.setContent(mensaje,"text/html");
                
                Transport transporte=sesion.getTransport("smtp");
                transporte.connect(DIRECCION_EMISOR,CONTRASENA);
                transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
                transporte.close();
            }catch(MessagingException e){}
    }
    
    @Asynchronous
    public void enviarMailInformativo(Pedido pedido){
        enviarMailInformativo(pedido,null);
    }
    
    @Asynchronous
    public void enviarMailInformativo(Pedido pedido,Direccion direccionRestaurante){
        String asunto=null;
        String mensaje=CABECERA_MAILS;
        
        switch(pedido.getEstado()){
            
            case NUEVO:
                asunto="Pedido confirmado";
                mensaje+=INICIO_PARRAFO+
                        "Hola, "+ pedido.getCliente().getNombre()
                        +"<br/>hemos confirmado su pedido y en breves comenzara su preparación."
                        +"<br/>Le mantendremos informado del estado de su pedido."
                        + "<br/><br/>Gracias."
                        +FIN_PARRAFO;
                break;
            case EN_PREPARACION:
                asunto="Pedido preparandose";
                mensaje+=INICIO_PARRAFO+
                        "Hola, "+ pedido.getCliente().getNombre()
                        +"<br/>Le informamos de que el restaurante ha comenzado el proceso de preparación de su pedido."
                        +"<br/>Le mantendremos informado del estado de su pedido."
                        + "<br/><br/>Gracias."
                        +FIN_PARRAFO;
                break;
            case EN_CAMINO:
                asunto="Pedido en camino";
                mensaje+=INICIO_PARRAFO+
                        "Hola, "+ pedido.getCliente().getNombre()
                        +"<br/>Su pedido ha sido preparado, y en breves comenzara su envío a la direccion indicada:"
                        +"<br/>"+pedido.getDireccion()
                        + "<br/><br/>Gracias."
                        +FIN_PARRAFO;
                break;
            case ESPERANDO_CLIENTE:
                asunto="Pedido esperando";
                mensaje+=INICIO_PARRAFO+
                        "Hola, "+ pedido.getCliente().getNombre()
                        +"<br/>Hemos terminado la preparación de su pedido, puede pasar a recogerlo cuando desee en la dirección del restaurante:"
                        +"<br/>"+direccionRestaurante
                        +"<br/>"
                        + "<br/><br/>Gracias."
                        +FIN_PARRAFO;
                break;
            case ENTREGADO:
                asunto="Pedido realizado";
                mensaje+=INICIO_PARRAFO+
                        "Hola, "+ pedido.getCliente().getNombre()
                        +"<br/>puede realizar una valoración del servicio recibido en la página del producto."
                        + "<br/><br/>Gracias."
                        +FIN_PARRAFO;
                break;
        }
        
        enviarMail(mensaje, asunto, pedido.getCliente().getEmail());
    }
    
    @Asynchronous
    public void restauranteNuevoPedido(Pedido pedido,Restaurante restaurante){
        String asunto="Nuevo pedido";
        String mensaje=CABECERA_MAILS+INICIO_PARRAFO
                +"Hola, "+restaurante.getUsername()
                +"<br/>Ha recibido un nuevo pedido por valor de "+pedido.getPrecioTotal()+" euros"
                +"<br/>para ser"+(pedido.getRecogida()==TipoRecogida.ENVIO?" enviado a la direccion "+pedido.getDireccion():" recogido por el cliente en su tienda")
                +"<br/>Comienze a prepararlo lo antes posible."
                + "<br/><br/>Gracias."
                +FIN_PARRAFO;
        
        enviarMail(mensaje, asunto, restaurante.getEmail());
    }
}
