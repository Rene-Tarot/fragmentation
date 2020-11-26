import java.io.*;
import java.net.*;

public class ServerSegment{
	public static void main(String[] args)throws Exception {
		DatagramSocket server = new DatagramSocket(59898);
        byte[] inMensaje = new byte[6020];
        byte[] out = new byte[1520];

        DatagramPacket inDatagram = new DatagramPacket(inMensaje, inMensaje.length);
        server.receive(inDatagram);

        String mensaje = new String(inDatagram.getData());
        
        System.out.println(mensaje);
        
        //Preparar el segundo paso del handshake
        String origen = mensaje.substring(0, 4);
        String resto =  mensaje.substring(8);
        String segundoPaso = origen+ "5254"+ "3030313130313030";
        
        InetAddress ip = inDatagram.getAddress();
        int puerto = inDatagram.getPort();

       	out = segundoPaso.getBytes();

        DatagramPacket outDatagram = new DatagramPacket(out, out.length, ip, puerto);

        server.send(outDatagram);

        //Imprimir tercer paso hand shake
        DatagramPacket inDatagram2 = new DatagramPacket(inMensaje, inMensaje.length);
        server.receive(inDatagram2);

        String mensaje2 = new String(inDatagram2.getData());
        
        System.out.println(mensaje2);

	}
}