package br.com.rvvsanchez.libs.api.rest.server.jaxrs.interceptor;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.WriterInterceptorContext;

public class MockWriterInterceptorContext implements WriterInterceptorContext {

  private OutputStream outputStream;

  private MultivaluedMap<String, Object> requestHeaders = new MultivaluedHashMap<String, Object>(10);

  @Override
  public Object getProperty(String name) {
    return null;
  }

  @Override
  public Collection<String> getPropertyNames() {
    return null;
  }

  @Override
  public void setProperty(String name, Object object) {
    // Do nothing
  }

  @Override
  public void removeProperty(String name) {
    // Do nothing
  }

  @Override
  public Annotation[] getAnnotations() {
    return null;
  }

  @Override
  public void setAnnotations(Annotation[] annotations) {
    // Do nothing
  }

  @Override
  public Class<?> getType() {
    return null;
  }

  @Override
  public void setType(Class<?> type) {
    // Do nothing
  }

  @Override
  public Type getGenericType() {
    return null;
  }

  @Override
  public void setGenericType(Type genericType) {
    // Do nothing
  }

  @Override
  public MediaType getMediaType() {
    return null;
  }

  @Override
  public void setMediaType(MediaType mediaType) {
    // Do nothing
  }

  @Override
  public void proceed() throws IOException, WebApplicationException {
    // Do nothing
  }

  @Override
  public Object getEntity() {
    return null;
  }

  @Override
  public void setEntity(Object entity) {
    // Do nothing
  }

  @Override
  public OutputStream getOutputStream() {
    return outputStream;
  }

  @Override
  public void setOutputStream(OutputStream os) {
    this.outputStream = os;
  }

  @Override
  public MultivaluedMap<String, Object> getHeaders() {
    return requestHeaders;
  }

}
