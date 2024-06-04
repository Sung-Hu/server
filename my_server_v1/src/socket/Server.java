package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import gui.ServerFrame;
import lombok.Data;

@Data
public class Server {
	private ServerFrame serverFrame;
	private ServerSocket serverSocket;
	private Socket socket;
	private JTextArea mainBoard;
	private FileWriter fileWriter;
	private BufferedReader reader;
	private BufferedWriter writer;
	private static final int PORT = 10001;
	private static Vector<USER> clientWriters = new Vector<>();

	public void startServer() {
		serverFrame = new ServerFrame(this);

		mainBoard = serverFrame.getMainBoard();
		try {
			serverSocket = new ServerSocket(PORT);
			serverFrame.getConnectBtn().setEnabled(false);
			connectClient();
		} catch (IOException e) {
			serverFrame.getConnectBtn().setEnabled(true);
		}
	}

	private void connectClient() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {

						// 소켓 장치
						socket = serverSocket.accept();
						serverViewAppendWriter("[알림] 사용자 접속 대기\n");
						USER user = new USER(socket);
						user.start();
					} catch (IOException e) {
						serverViewAppendWriter("[에러] 접속 에러 ! !\n");

					}
				}
			}
		}).start();
	}

	private void serverViewAppendWriter(String str) {
		try {
			fileWriter = new FileWriter("my_server_log.txt", true);
			mainBoard.append(str);
			fileWriter.write(str);
			fileWriter.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class USER extends Thread {
		// 소켓 장치
		private Socket socket;

		// 입출력 장치
		private BufferedReader reader;
		private BufferedWriter writer;

		public USER(Socket socket) {
			this.socket = socket;
			connectIO();
		}

		private void connectIO() {
			try {
				// str
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				// serverViewAppendWriter(null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void writer(String msg) {
			try {
				writer.write(msg + "\n");
				writer.flush();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "서버 출력 에러 !");
			}
		}
	}

	public void reader(String msg) {
		try {
			reader.readLine();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.startServer();

		System.out.println("서버오픈");
	}
}// end of Class
