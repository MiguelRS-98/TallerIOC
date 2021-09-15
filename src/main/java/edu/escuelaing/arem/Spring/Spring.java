package edu.escuelaing.arem.Spring;

import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * This class create the spring of the start.
 * @author Miguel Angel Rodriguez Siachoque
 */
public class Spring 
{
    private Map<String,Method> methodArray= new HashMap<>();
    /**
     * This method make the mapping for the spring.
     * @param args Items for the list of mapping
     * @throws Exception IOException Exception of a server malfunction.
     */
    public void start(String args) throws Exception 
    {
    }
    /**
     * This method return the array of methods.
     * @param path Items for call the method.
     * @return List of the methods of Spring.
     * @throws InvocationTargetException IOException Exception of a server malfunction.
     * @throws IllegalAccessException IOException Exception of a server ilegal Access.
     */
    public  String invoke (String path) throws InvocationTargetException, IllegalAccessException 
    {
        return methodArray.get(path).invoke(null).toString();
    }
}
