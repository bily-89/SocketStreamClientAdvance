/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketstreamclientadvance;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class SocketStreamClientAdvance {
    private static Socket clientSocket;
    
    //addr solo para pruebas en local
    static InetSocketAddress localAddr = new InetSocketAddress("localhost", 5555);
    static InetSocketAddress localAddrIP = new InetSocketAddress("127.0.0.1",5555);

    //addr LAN
    static InetSocketAddress localAddrPublicIP = new InetSocketAddress("192.168.1.189",5556);

    static InetSocketAddress ManuelRamosAddr = new InetSocketAddress("192.168.1.163", 5556);
    static InetSocketAddress RafaAddr = new InetSocketAddress("192.168.1.179", 5556);
    
    SocketStreamClientAdvance(){
    
    }
    
    public static void main(String[] args) throws IOException {
        boolean conxPosible=false;
        String mensaje="";
        BufferedReader teclado;
        
        do{
            do{
                System.out.println('\n'+"Introduzca el mensaje que desea enviar:");
                teclado = new BufferedReader (new InputStreamReader(System.in));
                try {
                    mensaje=teclado.readLine();                
                } catch (IOException ex) {
                    Logger.getLogger("Error entrada de datos");
                }
            }while(mensaje.isEmpty());
            
            if(conxPosible = conectar()){
                enviarMensaje(mensaje);
                //desconectar();
            }
        }while(!(mensaje.trim()).equalsIgnoreCase("fin") && conxPosible);
        desconectar();
    }
    
    public static boolean conectar(){
        boolean conxPosible=true;
        System.out.println('\n'+"Solicitando conexion al servidor");
        
        //System.out.println("Creando el Socket cliente");
        clientSocket = new Socket();
        //System.out.println("Direccionando el Socket cliente");
        try {
            clientSocket.connect(localAddrPublicIP);
        } catch (IOException ex) {
            conxPosible=false;
            System.out.println("No se pudo conectar con el servidor! Mensaje no enviado");
            /*Logger.getLogger(SocketStreamClientAdvance.class.getName()).log(
                    Level.SEVERE, null, ex);*/
        }
        
        return  conxPosible;
    }
    
    public static void enviarMensaje(String mensaje){
        //System.out.println("Creando streams de I/O en el Socket cliente");
        //InputStream is = clientSocket.getInputStream();
        OutputStream os;
        try {
            os = clientSocket.getOutputStream();
            System.out.println("Enviando el mensaje al servidor");
            os.write(mensaje.getBytes());
        } catch (IOException ex) {
            System.out.println("Perdida la conexion con el servidor!");
            /*Logger.getLogger(SocketStreamClientAdvance.class.getName()).
                    log(Level.SEVERE, null, ex);*/
        }
        
        
    }
    
    public static void desconectar(){
        //System.out.println("Cerrando el Socket cliente");
        try {
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketStreamClientAdvance.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}