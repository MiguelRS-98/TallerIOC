package edu.escuelaing.arem.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This interface get the values for the Spring.
 * @author Miguel Angel Rodriguez Siachoque
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetMapping 
{
    public String value();
}
