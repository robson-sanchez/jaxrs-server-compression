package br.com.rvvsanchez.libs.api.rest.server.jaxrs.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.jaxrs.cfg.ObjectWriterInjector;

/**
 * Class responsible to evaluate the request parameters to enable pretty printing of the response.
 * 
 * @author robson-sanchez
 */
@Provider
public class PrettyFormatFilter implements ContainerResponseFilter {

  private static final String PRETTY_PARAM = "pretty";

  /**
   * Evaluates the request parameter named 'pretty' and enable the pretty format if this parameter
   * is 'true'.
   */
  @Override
  public void filter(ContainerRequestContext requestContext,
      ContainerResponseContext responseContext) throws IOException {
    final UriInfo uriInfo = requestContext.getUriInfo();
    final MultivaluedMap<String, String> params = uriInfo.getQueryParameters();

    if (params.containsKey(PRETTY_PARAM)) {
      boolean indent = Boolean.parseBoolean(params.getFirst(PRETTY_PARAM));
      ObjectWriterInjector.set(new IndentingModifier(indent));
    }
  }

}
