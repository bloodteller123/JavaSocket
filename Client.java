import java.net.*;
import java.io.*;



public class Client {
	public static void main(String args[]) throws InterruptedException {
		
			Socket clientS = null;
			int serverport = 7896;
			DataInputStream in;
			DataOutputStream out;
			String temp;
			String request;
			try {
				System.out.println("Please Type String :\n");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String code = reader.readLine();
				InetAddress addr = InetAddress.getLocalHost();
				
				System.out.print("Starting new Client Server\n");
				clientS = new Socket(addr.getHostName(),serverport);
			
				String encodedCode = encode(code);
				request = "request-to-send";
				
				while(true) {
					in = new DataInputStream(clientS.getInputStream());			
					out = new DataOutputStream(clientS.getOutputStream());	
					System.out.println("Client is sending request-to-send message to Server\n");
					Thread.sleep(1000);
					out.writeUTF(request);	
	            	while ((temp = in.readUTF()) != null) {
	            		if(temp.equals("clear-to-send")) {
	            			Thread.sleep(2000);
	            			System.out.println("Server has granted Client permission(clear_to_send) to send Code\n");
	            			Thread.sleep(2000);
	            			System.out.println("Now Client is sending encodedCode "+ encodedCode + " to the Server\n" );
	            			out.writeUTF(encodedCode);
	            			
	            		}
	            		else if(temp.equals("Received")) {
	            			Thread.sleep(2000);
	            			System.out.println("Server has received Code");
	            			break;
	            		}
	            		
	            	}
	            	break;        	
				}
				in.close();
				out.close();
			}
			catch(IOException e){System.out.println("Socket:"+e.getMessage());}
	}
	
	private static String encode(String code) {
		int next = 0;
		int mark = 0;
		int markOne = 0;
		String encodedCode = "";
		char[] chars = code.toCharArray();
		for(int i=0;i<chars.length;i++){
			if(chars[i]=='1'){
				if(i>0){
					if(chars[i-1]=='1'){
						if(encodedCode.charAt(i-1)=='+'){
							encodedCode += "-";
							markOne = i;
						}
						else if(encodedCode.charAt(i-1)=='-'){
							encodedCode += "+";
							markOne = i;
						}
					}
					else{
						if(encodedCode.charAt(markOne)=='+'){
							encodedCode += "-";
							markOne = i;
						}
						else if(encodedCode.charAt(markOne)=='-'){
							encodedCode += "+";
							markOne = i;
						}
					}
				}
				else
					encodedCode +="+";
			}
			else if(chars[i]=='0'){
				int check = 0;
				if(chars.length - i >=8){
					for(int j=i;j<i+8;j++){
						if(chars[j]=='0'){		
							check +=1;
						}
					}
				}
				next = check;
				if(check==8){
					mark = i-1;
					String subStringEightZ = "";		
					if(encodedCode.charAt(mark)=='+'){
						subStringEightZ = "000+-0-+";
					}
					else if(encodedCode.charAt(mark)=='-'){
						subStringEightZ = "000-+0+-";
					}
					encodedCode += subStringEightZ;
					i = i+7;
				}
				else
					if(next==0){
						encodedCode+="0";
					}
					else {
						next--;
					}		
			}
		}	
			
		return encodedCode;
	}
}
