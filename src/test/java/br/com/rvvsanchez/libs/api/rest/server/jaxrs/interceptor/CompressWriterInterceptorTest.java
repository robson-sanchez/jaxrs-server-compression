package br.com.rvvsanchez.libs.api.rest.server.jaxrs.interceptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for {@link CompressWriterInterceptor}
 * 
 * @author robson-sanchez
 */
@RunWith(MockitoJUnitRunner.class)
public class CompressWriterInterceptorTest {

  @Mock
  private HttpHeaders httpHeaders;

  @InjectMocks
  private CompressWriterInterceptor interceptor;

  private MultivaluedMap<String, String> requestHeaders;

  @Before
  public void init() {
    requestHeaders = new MultivaluedHashMap<String, String>(1);
    doReturn(requestHeaders).when(httpHeaders).getRequestHeaders();
  }

  @Test
  public void nullHeaders() throws WebApplicationException, IOException {
    WriterInterceptorContext context = new MockWriterInterceptorContext();
    interceptor.aroundWriteTo(context);

    assertNull(context.getHeaders().get(HttpHeaders.CONTENT_ENCODING));
  }

  @Test
  public void emptyHeaders() throws WebApplicationException, IOException {
    requestHeaders.addAll(HttpHeaders.ACCEPT_ENCODING, Collections.emptyList());

    WriterInterceptorContext context = new MockWriterInterceptorContext();
    interceptor.aroundWriteTo(context);

    assertNull(context.getHeaders().get(HttpHeaders.CONTENT_ENCODING));
  }

  @Test
  public void testGzipHeaderNotFound() throws WebApplicationException, IOException {
    requestHeaders.add(HttpHeaders.ACCEPT_ENCODING, "deflate");

    WriterInterceptorContext context = new MockWriterInterceptorContext();
    interceptor.aroundWriteTo(context);

    assertNull(context.getHeaders().get(HttpHeaders.CONTENT_ENCODING));
  }

  @Test
  public void testGzipHeader() throws WebApplicationException, IOException {
    requestHeaders.add(HttpHeaders.ACCEPT_ENCODING, "gzip,deflate");

    OutputStream os = new ByteArrayOutputStream();
    WriterInterceptorContext context = new MockWriterInterceptorContext();
    context.setOutputStream(os);
    
    interceptor.aroundWriteTo(context);

    List<Object> result = context.getHeaders().get(HttpHeaders.CONTENT_ENCODING);
    
    assertEquals(1, result.size());
    assertEquals("gzip", result.get(0));
    assertNotEquals(os, context.getOutputStream());
  }
}
