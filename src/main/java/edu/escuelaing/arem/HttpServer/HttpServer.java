package edu.escuelaing.arem.HttpServer;

import edu.escuelaing.arem.Spring.Spring;
import java.net.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class runs an http server for datagram execution.
 * @author Miguel Angel Rodriguez Siachoque
 * @author Luis Daniel Benavides Navarro
 */
public class HttpServer 
{
    private Spring spring;
    /**
     * This set the Spring of the server.
     * @param spring Spring that generate the server.
     */
    public HttpServer(Spring spring) 
    {
        this.spring = spring;
    }
    /**
     * This method starts the HTTP server.
     * @param args Item to be displayed by the server.
     * @throws IOException Exception of a server malfunction.
     */
    public void startServer (String[] args) throws IOException 
    {
        int port = getPort();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(1);
        }
        Socket clientSocket = null;
        boolean running = true;
        while (running) {
            try {
                System.out.println("Listo para recibir en puerto: " + port);
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            proccessRequest(clientSocket);
        }
        serverSocket.close();
    }
    /**
     * This method perform the server request process and displays its header, received and request.
     * @param clientSocket Client that wants to run the HTTP server.
     * @throws IOException Exception in the malfunction of some request.
     */
    public void proccessRequest (Socket clientSocket) throws IOException
    {
        BufferedReader in;
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            String method = "";
            String path = "";
            String version = "";
            List<String> headers = new ArrayList<String>();
            while ((inputLine = in.readLine()) != null) {
                if (method.isEmpty()){
                    String[] requestStrings = inputLine.split(" ");
                    method = requestStrings[0];
                    path = requestStrings[1];
                    version = requestStrings[2];
                    System.out.println("Request: " + method + " " + path + " " + version);
                }
                else {
                    System.out.println("Header: " + inputLine);
                    headers.add(inputLine);
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            String responseMsg = createTextResponse(path);
            out.println(responseMsg);
        }
        in.close();
        clientSocket.close();
    }
    /**
     * This method is in charge of reading the content of the files for the HTTP server.
     * @param path The file path to identify and read.
     * @return File content.
     */
    public String createTextResponse (String path)
    {
        Path file = Paths.get("./TestHttpServer" + path);
        Charset charset = Charset.forName("UTF-8");
        String outmsg = "";
        try (BufferedReader reader = Files.newBufferedReader(file,charset)) {
            String line = null;
            while ((line = reader.readLine())!= null) {
                System.out.println(line);
                outmsg = outmsg + "\r\n" + line;
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        String outputLine =
                "HTTP/1.1 200 OK\r\n" 
                    + "Content-Type_ text/html\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + " <head>"
                    + " <title>TODO supply a title</title>"
                    + " <meta charset=\"UTF-8\">"
                    + " <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                    + " </head>"
                    + " <body>"
                    + outmsg
                    + " </body>"
                    + "</html>";
        return outputLine;
    }
    /**
     * This method defines the type of the file.
     * @param path The file path to identify and read.
     * @return Type of file.
     */
    public String contentType(String path) 
    {
        String mimeType = null;

        if (path.endsWith(".html")) {
            mimeType = "text/html";
        } else if (path.endsWith(".css")) {
            mimeType = "text/css";
        } else if (path.endsWith(".js")) {
            mimeType = "text/javascript";
        } else if (path.endsWith(".svg")) {
            mimeType = "image/jpeg";
        } else if (path.endsWith(".gif")) {
            mimeType = "image/gif";
        } else if (path.endsWith(".jpg")) {
            mimeType = "image/jpg";
        } else if (path.endsWith(".png")) {
            mimeType = "image/png";
        }
        return mimeType;
    }
    /**
     * This method get the error 404 if the server dont found the page
     * @param outmsg Message output.
     * @return HTML with Not Found 404.
     */
    public String default404Message(String outmsg)
    {
        String outputLine =
                "HTTP/1.1 404 not found\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + " <head>"
                    + " <title>TODO supply a title</title>"
                    + " <meta charset=\"UTF-8\">"
                    + " <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                    + " </head>"
                    + " <body>"
                    + " <div><h1>error404</h1></div>"
                    + " " + outmsg + "\n"
                    + " </body>"
                    + "</html>";
        return outputLine;
    }
    /**
     * This method get port.
     * @return Port.
     */
    public static int getPort () 
    {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }
}