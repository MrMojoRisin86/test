import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;



public class HTTPpostRequest {
	
	// HTTP POST request
		public void sendPost(String link,String ime,String prezime,String email) throws Exception {
			

			String url = link;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			InetAddress IP=InetAddress.getLocalHost();

			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String urlParameters = "from=registracija%40ongulus.com&to="+URLEncoder.encode(email, "UTF-8")+"&subject=Dobrodosli&message="+URLEncoder.encode(ime, "UTF-8")+"+"+URLEncoder.encode(prezime, "UTF-8")+"%2C+dobrodosli+na+nasu+stranicu%21+Registracija+je+poslana+sa+IP+adrese%3A"+IP.toString()+".+Hvala+na+registraciji+%21";
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//print result
			System.out.println(response.toString());

		}

		
}
