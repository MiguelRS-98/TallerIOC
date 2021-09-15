package edu.escuelaing.arem.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This method controllers the values of get to spring.
 * @author Miguel Angel Rodriguez Siachoque
 */
public class Controller 
{
    /**
     * This method make the mapping of the aplication.
     * @return Index of the mapping.
     */
    @RequestMapping(value = "/")
    public static String index () 
    {
        return "Greetings from micro Spring Boot!";
    }
    /**
     * This method make the mapping of the aplication in path.
     * @return Index the mapping of path.
     */
    @RequestMapping(value = "/path")
    public static String path () 
    {
        return "Hello World!";
    }
}
