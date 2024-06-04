package ch01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
	public static void main(String[] args) {

		try(ServerSocket serverSocket = new ServerSocket(5010) ;) {
			Socket socket = serverSocket.accept();
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String message = reader.readLine();
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
