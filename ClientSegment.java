import java.net.*;
import java.util.*;
import java.text.*;
import java.io.*;
import java.util.Date;
import java.text.*;
import java.util.Scanner;

public class ClientSegment{
   
   

   

   static void threeWayHandShake(String message, byte[] mandar, byte[] recivir, DatagramSocket cliente, InetAddress dir)throws IOException{
   		//Primer paso handshake
   		mandar = message.getBytes();
   		DatagramPacket paquete = new DatagramPacket(mandar, mandar.length, dir, 59898);
   		cliente.send(paquete);

   		//Tercer paso
   		DatagramPacket respuesta = new DatagramPacket(recivir, recivir.length);
        cliente.receive(respuesta);
		String mensajeDos = new String(respuesta.getData());
		System.out.println(mensajeDos);

		String origen = mensajeDos.substring(0, 8);
		String tercerPaso = origen+"31303130303130";
		mandar = tercerPaso.getBytes();
		DatagramPacket paquete2 = new DatagramPacket(mandar, mandar.length, dir, 59898);
   		cliente.send(paquete2);


   		return;
   }



   public static void main(String args[])throws Exception {
   		String firstStepHex = "525430303030303130313030";

   		//Pedir el nombre del archivo
   		Scanner sc = new Scanner(System.in);
   		System.out.println("Ingrese el nombre del archivo:");
   		String fileNombre = sc.nextLine();

   		//Preparar campos para la comunicacion UDP
   		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
   		DatagramSocket client = new DatagramSocket();
   		InetAddress addr = InetAddress.getLocalHost();
   		byte[] send = new byte[1520];
   		byte[] recieve = new byte[1520];

   		threeWayHandShake(firstStepHex, send, recieve, client, addr);
   }
}