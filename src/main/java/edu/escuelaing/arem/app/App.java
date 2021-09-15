package edu.escuelaing.arem.App;

import edu.escuelaing.arem.Spring.Spring;
import edu.escuelaing.arem.HttpServer.HttpServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This Class make the start of the Application.
 * @author Miguel Angel Rodriguez Siachoque
 */
@SpringBootApplication
public class App 
{
    /**
     * This main method that activates the spring.
     * @param args main method argument for activation.
     */
    public static void main (String[] args) throws Exception
    {
        Spring spring= new Spring();
        spring.start("edu.escuelaing.arem.Spring.Controller");
        
        HttpServer server = new HttpServer(spring);
        server.startServer(args);
    }
}