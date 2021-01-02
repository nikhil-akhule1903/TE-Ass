import java.io.*;
import java.net.*;

public class Udpclient
{
	public static void main(String args[]) throws IOException
	{
		DatagramSocket cs = new DatagramSocket();
		byte senddata[] = new byte[512];
		byte receivedata[] = new byte[512];
		boolean running = true;

		while(true)
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			InetAddress ip = InetAddress.getByName("127.0.0.1");
			int port = 7000;

			System.out.println("ENTER CLINET MESSAGE : ");
			String message = br.readLine();
			senddata = message.getBytes();

			DatagramPacket sendpacket = new DatagramPacket(senddata,senddata.length,ip,port);

			cs.send(sendpacket);

			if(message.equalsIgnoreCase("Stop"))
			{
				running = false;
				break;
			}

			DatagramPacket receivepacket = new DatagramPacket(receivedata,receivedata.length);

			cs.receive(receivepacket);

			message = new String(receivepacket.getData(),receivepacket.getOffset(),receivepacket.getLength());

			System.out.println("SERVER SAYS :  "+message);

			if(message.equalsIgnoreCase("Stop"))
			{
				running = false;
				break;
			}
		} 
		cs.close();
		System.out.println("CONNECTION STOPPED");
	}
}
