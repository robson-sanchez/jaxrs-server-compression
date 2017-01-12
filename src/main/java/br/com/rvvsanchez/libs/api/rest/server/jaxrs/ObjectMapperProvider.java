package br.com.rvvsanchez.libs.api.rest.server.jaxrs;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Provides an {@link ObjectMapper} class to the application.
 * 
 * @author robson-sanchez
 */
@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {

  private final ObjectMapper result = new ObjectMapper();

  @Override
  public ObjectMapper getContext(Class<?> type) {
    return result;
  }

}
