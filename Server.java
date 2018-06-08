import java.net.*;
import java.io.*;

public class Server {
		public static void main(String args[]) throws InterruptedException {
			DataInputStream in;
			DataOutputStream out;
			int serverPort = 7896;
			String request ;
			String confirm;
			String ACK;
		   	try {
		        InetAddress addr = InetAddress.getLocalHost();
		    
		        // Get IP Address
		        byte[] ipAddr = addr.getAddress();
		    
		        // Get hostName
		        String hostname = addr.getHostName();
		        System.out.println("Server Name: " + hostname + "\nServer Port: " + serverPort);
		    } catch (UnknownHostException e) {
		    }
		   	
		   	try{
				@SuppressWarnings("resource")
				ServerSocket listenSocket = new ServerSocket(serverPort);// build server 
				System.out.println("Server is Ready");
				System.out.println("listening to client sockets");
				Socket clientSocket = listenSocket.accept();
				while(true) {

					in = new DataInputStream(clientSocket.getInputStream());
					
					out = new DataOutputStream(clientSocket.getOutputStream());
					
					request = in.readUTF();
					if(request.equals("request-to-send")) {
						System.out.println("Server received sending request from client\n");
						Thread.sleep(2000);
						
						confirm = "clear-to-send";
						System.out.println("Server sent clear-to-send to Cilent\n");
						
						out.writeUTF(confirm);
					}

					else {
						
						ACK = "Received";
						Thread.sleep(2000);
						System.out.println("Server received code from client\n");
						out.writeUTF(ACK);
						System.out.println("Server is decoding\n");
						Thread.sleep(2000);
						System.out.println("Decoding Process is now completed\n");
						System.out.println("Now Server is displaying decoded code: \n");
						Thread.sleep(1000);
						System.out.println(decode(request));
						break;
						

					}
					
					
				}
				
				//in.close();
				//out.close();
			} catch(IOException e) {while (true) System.out.println("IOException Listen socket:"+e.getMessage());}

		}
		
		private static String decode(String s) {
			String decodedCode = "";
			
			s = s.replaceAll("000[-][+]0[+][-]","00000000");
			s = s.replaceAll("000[+][-]0[-][+]","00000000");
			s = s.replaceAll("[+]","1");
			s = s.replaceAll("[-]","1");	
			
			decodedCode = s;

			return decodedCode;
		}
		
		
}
