package br.com.rvvsanchez.libs.api.rest.server.jaxrs.interceptor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.ReaderInterceptorContext;

public class MockReaderInterceptorContext implements ReaderInterceptorContext {

  private InputStream inputStream;

  private MultivaluedMap<String, String> headers = new MultivaluedHashMap<String, String>(10);

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
  public Object proceed() throws IOException, WebApplicationException {
    return null;
  }

  @Override
  public InputStream getInputStream() {
    return inputStream;
  }

  @Override
  public void setInputStream(InputStream is) {
    this.inputStream = is;
  }

  @Override
  public MultivaluedMap<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(MultivaluedMap<String, String> headers) {
    this.headers = headers;
  }

}
