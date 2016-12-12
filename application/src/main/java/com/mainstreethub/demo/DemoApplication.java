package com.mainstreethub.demo;

import com.mainstreethub.demo.resources.HelloEnvironmentResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class DemoApplication extends Application<DemoConfiguration> {
  public static void main(String[] args) throws Exception {
    new DemoApplication().run(args);
  }

  @Override
  public void run(DemoConfiguration configuration, Environment environment) throws Exception {
    environment.jersey().register(new HelloEnvironmentResource(configuration));
  }
}
