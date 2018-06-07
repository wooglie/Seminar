import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Server {
	public static void main(String args[]) {
		
		int PORT = 8080;
		
		System.out.println("Server started..");		

        try (ServerSocket server = new ServerSocket(8080)){
            
            System.out.println("Listening on port: 8080");          
                    
            Socket socket = server.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String input = "";
            String output = "";

            while (!input.equals("end")) {       	         	
            		
            	input = dataInputStream.readUTF();
            	output = input.toUpperCase() + " <at> " + LocalDateTime.now();
                
                System.out.println("Input stream: " + input);	
                
                dataOutputStream.writeUTF(output);
                dataOutputStream.flush();
            }

            socket.close();
        }
        catch (Exception e) {
        	System.out.println("Error: " + e.toString());
        }
        
        System.out.println("Server stopped..");	
        System.exit(0);
    }
}
