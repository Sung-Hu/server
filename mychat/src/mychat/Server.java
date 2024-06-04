package mychat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Server {

	ServerFrame serverFrame;
	
	private ServerSocket serverSocket;
	private Socket socket;
	
	private JTextArea mainBoard;
	
	private FileWriter fileWriter;
	
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private static final int PORT = 10001;
	
	private static Vector<USER> clientWriters = new Vector<>();

	public ServerFrame getServerFrame() {
		return serverFrame;
	}

	public void setServerFrame(ServerFrame serverFrame) {
		this.serverFrame = serverFrame;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public JTextArea getMainBoard() {
		return mainBoard;
	}

	public void setMainBoard(JTextArea mainBoard) {
		this.mainBoard = mainBoard;
	}

	public FileWriter getFileWriter() {
		return fileWriter;
	}

	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}

	public static Vector<USER> getClientWriters() {
		return clientWriters;
	}

	public static void setClientWriters(Vector<USER> clientWriters) {
		Server.clientWriters = clientWriters;
	}

	public static int getPort() {
		return PORT;
	}

	public Server() {
		// TODO Auto-generated constructor stub
		serverFrame = new ServerFrame(this);

		mainBoard = serverFrame.getMainBoard();
	}
	
	public void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            serverFrame.getConnectBtn().setEnabled(false);
            serverViewAppendWriter("서버가 시작되었습니다.\n");
            connectClient();
        } catch (IOException e) {
            serverFrame.getConnectBtn().setEnabled(true);
            serverViewAppendWriter("서버 시작 실패: " + e.getMessage() + "\n");
        }
    }

	private void connectClient() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						// 소켓 장치
						serverViewAppendWriter("[알림] 사용자 접속 대기\n");
						socket = serverSocket.accept();
						System.out.println(1111);
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
	                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	                clientWriters.add(this);

	                String message;
	                while ((message = reader.readLine()) != null) {
	                    System.out.println(message);
	                    serverViewAppendWriter(message + "\n");
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            } finally {
	                try {
	                    socket.close();
	                    clientWriters.remove(this);
	                    System.out.println("클라이언트 연결 해제");
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                }
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
	
	public void chatting() {
		
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.startServer();

		System.out.println("서버오픈");
	}
}
