package br.com.rvvsanchez.libs.api.rest.server.jaxrs.interceptor;

import static br.com.rvvsanchez.libs.api.rest.server.jaxrs.interceptor.CompressConstants.GZIP_ENCODING;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

/**
 * Reader interceptor responsible to deal with the encoded content.
 * 
 * @author robson-sanchez
 */
@Provider
public class CompressReaderInterceptor implements ReaderInterceptor {

  /**
   * Changes the input stream to support the compression using gzip.
   */
  @Override
  public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException,
      WebApplicationException {
    final MultivaluedMap<String, String> headers = context.getHeaders();
    final List<String> contentEncoding = headers.get(HttpHeaders.CONTENT_ENCODING);

    boolean encoded = isEncoded(contentEncoding);

    if (encoded) {
      final InputStream originalInputStream = context.getInputStream();
      context.setInputStream(new GZIPInputStream(originalInputStream));
    }

    return context.proceed();
  }

  private boolean isEncoded(List<String> headers) {
    boolean encoded = false;

    if (headers != null) {
      for (String encoding : headers) {
        if (encoding.contains(GZIP_ENCODING)) {
          encoded = true;
          break;
        }
      }
    }

    return encoded;
  }

}
