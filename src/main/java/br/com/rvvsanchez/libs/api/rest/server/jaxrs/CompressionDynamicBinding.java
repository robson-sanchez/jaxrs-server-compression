package br.com.rvvsanchez.libs.api.rest.server.jaxrs;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rvvsanchez.libs.api.rest.server.jaxrs.annotation.Compress;
import br.com.rvvsanchez.libs.api.rest.server.jaxrs.annotation.DisableCompression;
import br.com.rvvsanchez.libs.api.rest.server.jaxrs.filter.PrettyFormatFilter;
import br.com.rvvsanchez.libs.api.rest.server.jaxrs.interceptor.CompressWriterInterceptor;

/**
 * Dynamic registration for compression interceptors and filters.
 * 
 * If the resource class or resource method contains the annotation {@link Compress} this class
 * should register the {@link CompressWriterInterceptor} and the {@link PrettyFormatFilter}.
 * 
 * If the resource method contains the annotation {@link Compress} this registration will be
 * skipped.
 * 
 * @author robson-sanchez
 *
 **/
public class CompressionDynamicBinding implements DynamicFeature {

  private static final Logger LOGGER = LoggerFactory.getLogger(CompressionDynamicBinding.class);

  /**
   * Evaluates the resource annotations and register the interceptors and filters if required.
   */
  @Override
  public void configure(ResourceInfo resourceInfo, FeatureContext context) {
    Method resourceMethod = resourceInfo.getResourceMethod();

    if (hasAnnotation(resourceMethod, DisableCompression.class)) {
      LOGGER.trace("Compression disabled");
      return;
    }

    Class<?> resourceClass = resourceInfo.getResourceClass();

    if ((hasAnnotation(resourceMethod, Compress.class))
        || (hasAnnotation(resourceClass, Compress.class))) {
      context.register(CompressWriterInterceptor.class);
      context.register(PrettyFormatFilter.class);
    }
  }

  /**
   * Evaluates the resource annotations.
   * @param annotatedObject Object to be evaluated
   * @param annotation Annotation class
   * @return true if the object contains the annotation class or false otherwise.
   */
  private boolean hasAnnotation(AnnotatedElement annotatedObject,
      Class<? extends Annotation> annotation) {
    if (annotatedObject == null) {
      return false;
    }

    return annotatedObject.isAnnotationPresent(annotation);
  }
  
}
