package mychat2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private ClientFrame clientFrame;
	private String Name;

	public Client(String serverAddress, int serverPort, ClientFrame clientFrame) {
		this.clientFrame = clientFrame;
		Name = clientFrame.getInputID().getText();
		try {
			socket = new Socket(serverAddress, serverPort);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			clientFrame.setPrintWriter(out);
			
			// 서버측에게 프로토콜 전송  
			out.println(Name);
			out.flush();
			
			//clientFrame.appendMessage(Name + "등장");
						
			String message;
			System.out.println("client ~~~~~~~~");
			while ((message = in.readLine()) != null) {
				System.out.println("-------------------");
				clientFrame.appendMessage( " => " + message);
			}
		} catch (Exception e) {
			clientFrame.appendMessage("SEVER ERROR!!!  : " + e.getMessage());
		}
	}
}