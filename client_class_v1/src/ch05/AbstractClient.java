package ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class AbstractClient {
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader socketReader;
	private PrintWriter socketWriter;
	private BufferedReader keyboardReader;
	
	public final void run() {
		try {
			setupServer();
			connection();
			setupStream();
			startService();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanup();
		}
	}

	// 1. ip 포트 번호 할당
	protected abstract void setupServer() throws IOException;

	// 2. 서버 요청
	protected abstract void connection() throws IOException;

	// 3. 스트림 초기화 (연결된 소켓에서 스트림을 뽑아야 함) - 여기서 함(private)
	private void setupStream() throws IOException {
		socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		socketWriter = new PrintWriter(socket.getOutputStream(), true);
		keyboardReader = new BufferedReader(new InputStreamReader(System.in));
	}

	// 4. 서비스 시작
	private void startService() {
		Thread readThread = createReadThread();
		Thread writeThred = createWriteThread();
		
		readThread.start();
		writeThred.start();
		
		try {
			readThread.join();
			writeThred.join();
			// main 스레드 잠깐 기다려 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private Thread createReadThread() {
		return new Thread(() -> {
			try {
				String msg; 
				// scnnner.nextLine();  <--- 무한 대기 (사용자가 콘솔에 값 입력 까지 대기) 
				// 코드 .... 
				while(  (msg = socketReader.readLine()) != null ) {
					// 서버측 콘솔에 출력 
					System.out.println("server측 msg : " + msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
	}
	
	private Thread createWriteThread() {
		return new Thread(() -> {
			try {
				String msg;
				// 서버측 키보드에서 데이터를 한줄라인으로 읽음 
				while( (msg = keyboardReader.readLine()) != null ) {
					// 클라이언트와 연결된 소켓에다가 데이터를 보냄 
					socketWriter.println(msg);
					socketWriter.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	private void cleanup() {
		try {
			if(socket != null) {
				socket.close();
			}
			
			if(serverSocket != null) {
				serverSocket.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}	
}// end of class
