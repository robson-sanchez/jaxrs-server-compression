package br.com.rvvsanchez.libs.api.rest.server.jaxrs.filter;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonGenerator;

/**
 * Unit test for {@link IndentingModifier}
 * 
 * @author robson-sanchez
 */
@RunWith(MockitoJUnitRunner.class)
public class IndentingModifierTest {

  @Mock
  private JsonGenerator jsonGenerator;

  @Test
  public void testNotIndenting() throws IOException {
    IndentingModifier modifier = new IndentingModifier(false);
    modifier.modify(null, null, null, null, jsonGenerator);

    verify(jsonGenerator, never()).useDefaultPrettyPrinter();
  }

  @Test
  public void testIndenting() throws IOException {
    IndentingModifier modifier = new IndentingModifier(true);
    modifier.modify(null, null, null, null, jsonGenerator);

    verify(jsonGenerator, times(1)).useDefaultPrettyPrinter();
  }
}
