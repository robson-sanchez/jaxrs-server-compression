package br.com.rvvsanchez.libs.api.rest.server.jaxrs.filter;

import java.io.IOException;

import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.jaxrs.cfg.EndpointConfigBase;
import com.fasterxml.jackson.jaxrs.cfg.ObjectWriterModifier;

/**
 * Modifies the response to a pretty format.
 * 
 * @author robson-sanchez
 */
public class IndentingModifier extends ObjectWriterModifier {

  private final boolean indent;

  /**
   * Constructor that identifies if the response should be formatted.
   * 
   * @param indent true if the response should be formatted.
   */
  public IndentingModifier(boolean indent) {
    this.indent = indent;
  }

  @Override
  public ObjectWriter modify(EndpointConfigBase<?> endpoint,
      MultivaluedMap<String, Object> responseHeaders, Object valueToWrite, ObjectWriter writer,
      JsonGenerator jsonGenerator) throws IOException {
    if (indent) {
      jsonGenerator.useDefaultPrettyPrinter();
    }

    return writer;
  }

}
