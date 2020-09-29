import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.util.Date;





public class UdpClient {

	public static void main(String[] args) {
		
		 try {
			 DatagramSocket s=new DatagramSocket(8010);
			 DatagramPacket p;
			 String msg;
			 do {
				 p=new DatagramPacket(new byte[512], 512);
				 s.receive(p);
				 byte buffer[] = p.getData();
				 msg = new String(buffer,9,p.getLength());
				 //Getting IP from data
				 String ip = "";
				 for(int i = 0; i < 4; i++) {
					 if(buffer[i]<0) {
						 ip+=(buffer[i]*-1)+127;
					 }
					 else {
						 ip += buffer[i];
					 }
					 if(i<3) {
						 ip+=".";
					 }
				 }
				 //Getting time from data
				 byte[] timeBuffer= {buffer[4], buffer[5], buffer[6], buffer[7]};
				 ByteBuffer bb = ByteBuffer.wrap(timeBuffer);
				 long timestamp = bb.getInt() * 1000;
				 Date realDate = new Date(timestamp);
				 //Printing info
				 System.out.println("Datagram from IP: " + p.getAddress() + " | Port:" + p.getPort() + " | Time: " + realDate +  " | Message length: " + buffer[8] + " | sender IP: " + ip +" > " + msg);
				 p.setData(msg.toUpperCase().getBytes());
				 p.setLength(msg.length());
				 s.send(p);
			 } while (!msg.equals("down"));
			 s.close();
			} catch (IOException e) { System.out.println(e); }

	}

}
