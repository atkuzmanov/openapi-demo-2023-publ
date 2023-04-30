package org.openapitools.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig  extends ResourceConfig {

    public JerseyConfig(ObjectMapper objectMapper) {
        register(CORSFilter.class);
        // register OpenApi resources for generation of OpenApi swagger spec
        register(OpenApiResource.class);
        register(AcceptHeaderOpenApiResource.class);
    }
}
