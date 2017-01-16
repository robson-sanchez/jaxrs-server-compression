package br.com.rvvsanchez.libs.api.rest.server.jaxrs.interceptor;

import static br.com.rvvsanchez.libs.api.rest.server.jaxrs.interceptor.CompressConstants.GZIP_ENCODING;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 * Writer interceptor to evaluate the request parameters and compress the response using gzip if
 * required.
 * 
 * @author robson-sanchez
 */
@Provider
public class CompressWriterInterceptor implements WriterInterceptor {

  /**
   * HTTP headers
   */
  private HttpHeaders httpHeaders;

  public CompressWriterInterceptor(@Context HttpHeaders httpHeaders) {
    this.httpHeaders = httpHeaders;
  }

  /**
   * Changes the output stream to support the compression using gzip.
   */
  @Override
  public void aroundWriteTo(WriterInterceptorContext context) throws IOException,
      WebApplicationException {
    final MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
    final List<String> acceptEncoding = requestHeaders.get(HttpHeaders.ACCEPT_ENCODING);

    if (acceptEncoding != null) {
      for (String encoding : acceptEncoding) {
        if (encoding.contains(GZIP_ENCODING)) {
          context.getHeaders().add(HttpHeaders.CONTENT_ENCODING, GZIP_ENCODING);

          final OutputStream outputStream = context.getOutputStream();
          context.setOutputStream(new GZIPOutputStream(outputStream));

          break;
        }
      }
    }

    context.proceed();
  }

}
