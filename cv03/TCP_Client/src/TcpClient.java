import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TcpClient {

	public static void main(String[] args) {
		Socket s = null;
		try {
			 s=new Socket("pcfeib425t.vsb.cz",8000);
			 BufferedReader sis = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter os = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			 String l;
			 do {
				 System.out.println("Type a line to send to server.");
				 l=sis.readLine();
				 os.write(l);
				 os.newLine();
				 os.flush();
			 } while (!l.equals("exit") && !l.equals("down"));
			 s.close();
		} catch (IOException e) { System.out.println(e); }
	}
}
