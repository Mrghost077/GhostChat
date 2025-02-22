import java.io.*;
import java.net.*;
import java.util.Scanner;

class ChatClient
{
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 5000;

    public static void main (String args [])
    {
        try (Socket socket = new Socket(SERVER_IP,PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in))
        {
            System.out.println("Connected to the Chat Server ...");

            Thread listenerThread = new Thread (() -> {
                try{
                    String serverMessage = in.readLine();
                    while (serverMessage != null)
                    {
                        System.out.println("Server : " + serverMessage);
                    }
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                    System.out.println("Disconected from the Server");
                }

            });

            listenerThread.start();

            while (true){
                System.out.println("You : ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) 
                {
                    System.out.println("Disconnecting...");
                     break;
                }

                out.println(message);
            }
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}