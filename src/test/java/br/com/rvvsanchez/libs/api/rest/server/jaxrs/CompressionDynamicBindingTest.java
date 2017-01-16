package br.com.rvvsanchez.libs.api.rest.server.jaxrs;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Method;

import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.rvvsanchez.libs.api.rest.server.jaxrs.filter.PrettyFormatFilter;
import br.com.rvvsanchez.libs.api.rest.server.jaxrs.interceptor.CompressReaderInterceptor;
import br.com.rvvsanchez.libs.api.rest.server.jaxrs.interceptor.CompressWriterInterceptor;

/**
 * Unit tests for {@link CompressionDynamicBinding}
 * 
 * @author robson-sanchez
 */
@RunWith(MockitoJUnitRunner.class)
public class CompressionDynamicBindingTest {

  @Mock
  private ResourceInfo resourceInfo;

  @Mock
  private FeatureContext context;

  private Class<?> resourceClass;

  private CompressionDynamicBinding dynamicBinding = new CompressionDynamicBinding();

  @Before
  public void init() {
    resourceClass = MockResource.class;
    doReturn(resourceClass).when(resourceInfo).getResourceClass();
  }

  @Test
  public void testDisableCompression() throws NoSuchMethodException, SecurityException {
    Method resourceMethod = resourceClass.getMethod("disableCompression");
    doReturn(resourceMethod).when(resourceInfo).getResourceMethod();

    dynamicBinding.configure(resourceInfo, context);

    verify(context, never()).register(CompressReaderInterceptor.class);
    verify(context, never()).register(CompressWriterInterceptor.class);
    verify(context, never()).register(PrettyFormatFilter.class);
  }

  @Test
  public void testResourceMethodCompress() throws NoSuchMethodException, SecurityException {
    Method resourceMethod = resourceClass.getMethod("compress");
    doReturn(resourceMethod).when(resourceInfo).getResourceMethod();

    dynamicBinding.configure(resourceInfo, context);

    verify(context, times(1)).register(CompressReaderInterceptor.class);
    verify(context, times(1)).register(CompressWriterInterceptor.class);
    verify(context, times(1)).register(PrettyFormatFilter.class);
  }

  @Test
  public void testResourceClassCompress() throws NoSuchMethodException, SecurityException {
    Method resourceMethod = resourceClass.getMethod("useDefault");
    doReturn(resourceMethod).when(resourceInfo).getResourceMethod();

    dynamicBinding.configure(resourceInfo, context);

    verify(context, times(1)).register(CompressReaderInterceptor.class);
    verify(context, times(1)).register(CompressWriterInterceptor.class);
    verify(context, times(1)).register(PrettyFormatFilter.class);
  }

  @Test
  public void testUncompressed() throws NoSuchMethodException, SecurityException {
    Method resourceMethod = resourceClass.getMethod("useDefault");
    doReturn(resourceMethod).when(resourceInfo).getResourceMethod();
    doReturn(null).when(resourceInfo).getResourceClass();

    dynamicBinding.configure(resourceInfo, context);

    verify(context, never()).register(CompressReaderInterceptor.class);
    verify(context, never()).register(CompressWriterInterceptor.class);
    verify(context, never()).register(PrettyFormatFilter.class);
  }
}
