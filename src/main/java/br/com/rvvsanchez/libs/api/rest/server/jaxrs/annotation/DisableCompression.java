package br.com.rvvsanchez.libs.api.rest.server.jaxrs.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

/**
 * Identify the resource methods that shouldn't be compressed.
 * @author robson-sanchez
 */
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@NameBinding
public @interface DisableCompression {

}
