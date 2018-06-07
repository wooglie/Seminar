import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String args[]) {

    	System.out.println("Client started..");	
    	
        try (Socket socket = new Socket("127.0.0.1",8080)){            
            
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            
            String input = "";
            String output = "";

            while (!output.equals("end")) {
                
            	System.out.println("Enter text or 'end' for exit: ");
            	
            	output = buffer.readLine();       
                dataOutputStream.writeUTF(output);
                input = dataInputStream.readUTF();
                System.out.println(input);
            }

        }
        catch (Exception e) {
        	System.out.println("Error: " + e.toString());
        }
        
        System.out.println("Client stopped..");	
        System.exit(0);
    }
}
