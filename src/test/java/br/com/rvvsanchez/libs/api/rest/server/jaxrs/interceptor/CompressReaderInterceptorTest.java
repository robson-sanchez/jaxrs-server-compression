package br.com.rvvsanchez.libs.api.rest.server.jaxrs.interceptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for {@link CompressReaderInterceptor}
 * 
 * @author robson-sanchez
 */
@RunWith(MockitoJUnitRunner.class)
public class CompressReaderInterceptorTest {

  private InputStream inputStream = new ByteArrayInputStream(new byte[512]);

  private MockReaderInterceptorContext context = new MockReaderInterceptorContext();

  @InjectMocks
  private CompressReaderInterceptor interceptor;

  @Before
  public void init() {
    context.setInputStream(inputStream);
  }

  @Test
  public void nullHeaders() throws WebApplicationException, IOException {
    interceptor.aroundReadFrom(context);
    assertEquals(inputStream, context.getInputStream());
  }

  @Test
  public void testGzipHeaderNotFound() throws WebApplicationException, IOException {
    MultivaluedMap<String, String> headers = new MultivaluedHashMap<String, String>(1);
    headers.add(HttpHeaders.CONTENT_ENCODING, "deflate");
    context.setHeaders(headers);

    interceptor.aroundReadFrom(context);

    assertEquals(inputStream, context.getInputStream());
  }

  @Test
  public void testGzipHeader() throws WebApplicationException, IOException {
    MultivaluedMap<String, String> headers = new MultivaluedHashMap<String, String>(1);
    headers.add(HttpHeaders.CONTENT_ENCODING, "gzip");
    context.setHeaders(headers);

    ByteArrayOutputStream os = new ByteArrayOutputStream();
    
    GZIPOutputStream gzip = new GZIPOutputStream(os);
    gzip.write("test".getBytes());
    
    byte[] compressed = os.toByteArray();
    
    InputStream inputStream = new ByteArrayInputStream(compressed);
    context.setInputStream(inputStream);
    
    interceptor.aroundReadFrom(context);

    assertNotEquals(inputStream, context.getInputStream());
  }
}
