package httphu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class http {
	public static void main(String[] args) {
		
	
	String urlString = "https://www.daum.net/";
	
	URL url;
	try {
		url = new URL(urlString);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
		conn.setRequestMethod("GET");
		
		int responseCode = conn.getResponseCode();
		
		System.out.println(responseCode);
		
		BufferedReader brIn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String inputLine;
		StringBuffer responseBuffer = new StringBuffer();
		
		while((inputLine = brIn.readLine()) != null ) {
			responseBuffer.append(inputLine);
		}
		brIn.close();
		
		String[] strHtmls = responseBuffer.toString().split("\\s");
		System.out.println("index count : " + strHtmls.length);

		for(String word : strHtmls) {
			System.out.println(word);
		}
		
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	}
}
