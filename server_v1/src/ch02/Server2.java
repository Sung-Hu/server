package ch02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {

			serverSocket = new ServerSocket(5011);
			System.out.println("서버시작");
			
			Socket socket = serverSocket.accept();
			System.out.println("클라이언트 연결");
			
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String message = reader.readLine();
			System.out.println("클라이언트 측 메세지를 받음 :" + message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
