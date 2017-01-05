package com.mainstreethub.demo.resources;

import com.mainstreethub.demo.DemoConfiguration;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.google.common.base.Preconditions.checkNotNull;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloEnvironmentResource {
  private final DemoConfiguration config;

  public HelloEnvironmentResource(DemoConfiguration config) {
    this.config = checkNotNull(config);
  }

  @GET
  public String getEnvironment() {
    return config.getEnvironment();
  }
}
