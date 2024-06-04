package ch01;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client1 {
	public static void main(String[] args) {
		try (Socket socket = new Socket("localgost", 5010) ){

			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			writer.println("Hello");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
