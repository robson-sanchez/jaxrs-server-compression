package br.com.rvvsanchez.libs.api.rest.server.jaxrs;

import br.com.rvvsanchez.libs.api.rest.server.jaxrs.annotation.Compress;
import br.com.rvvsanchez.libs.api.rest.server.jaxrs.annotation.DisableCompression;

@Compress
public class MockResource {

  @Compress
  public void compress() {
    // Do nothing
  }
  
  @DisableCompression
  public void disableCompression() {
    // Do nothing
  }
  
  public void useDefault() {
    // Do nothing
  }
}
