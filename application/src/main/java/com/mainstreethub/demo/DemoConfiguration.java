package com.mainstreethub.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DemoConfiguration extends Configuration {
  @JsonProperty("environment")
  @NotNull
  @Valid
  private final String environment = null;

  public String getEnvironment() {
    return environment;
  }
}
