import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer
{
    private static final int PORT = 5000;

    public static void main (String args [])
    {
        System.out.println ("Chat Server is running on port : " + PORT + " ...");

        try (ServerSocket serverSocket = new ServerSocket(PORT))
        {
            System.out.println ("Server is on and waiting for clients port : " + PORT);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected : " + clientSocket);

           
            BufferedReader in = new BufferedReader(new InputStreamReader (clientSocket.getInputStream()));

            PrintWriter out = new PrintWriter (clientSocket.getOutputStream(), true);

            String message = in.readLine();
            while (message != null )
            {
                System.out.println("Received Message from client : " + message);
                //out.println("Server echo : " + message);
            }




            clientSocket.close();
            System.out.println("Client Socket Closed !");

            serverSocket.close();
            System.out.println("Server Socket Closed !");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}