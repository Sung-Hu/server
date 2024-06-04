package mychat2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
	private ServerFrame serverFrame;
	private int PORT = 5010;
	private static Vector<PrintWriter> clientWriters = new Vector<>();

	public Server(int port, ServerFrame serverFrame) {
		this.PORT = port;
		this.serverFrame = serverFrame;
		startServer();
	}

	private void startServer() {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			serverFrame.appendMessage("서버가 포트 " + PORT + "에서 시작되었습니다.");

			while (true) {
				Socket socket = serverSocket.accept();
				serverFrame.appendMessage("클라이언트 연결됨 : " + socket.getInetAddress());
				new ClientHandler(socket, serverFrame).start();
			}
		} catch (IOException e) {
			serverFrame.appendMessage("서버 오류 : " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static class ClientHandler extends Thread {
		private Socket socket;
		private PrintWriter out;
		private BufferedReader in;
		private ServerFrame serverFrame;
		private String clientID;

		public ClientHandler(Socket socket, ServerFrame serverFrame) {
			this.socket = socket;
			this.serverFrame = serverFrame;
		}

		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				
				clientID = in.readLine();
				serverFrame.appendMessage(clientID + "님 [등.장]");
				clientWriters.add(out);
				
				
				broadcastMessage(clientID + "님 [등.장]");				
				
				String message;
				while ((message = in.readLine()) != null) {
					serverFrame.appendMessage("수신됨 : " + message);
					broadcastMessage(message);

				}
			} catch (IOException e) {
				serverFrame.appendMessage("클라이언트 처리 오류 : " + e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					serverFrame.appendMessage(clientID + "님 [퇴장]");
					broadcastMessage(clientID + "님 [퇴장]");

					socket.close();
					serverFrame.appendMessage("클라이언트 연결 해제됨 : " + socket.getInetAddress());
				} catch (IOException e) {
					serverFrame.appendMessage("소켓 종료 오류 : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}

		private void broadcastMessage(String message) {
			System.out.println("방송 확인 ");
			for (PrintWriter writer : clientWriters) {
				System.out.println("111111111111111111 " + message);
				writer.println(message);
				writer.flush();
			}
		}
	}
}