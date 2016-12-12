package com.mainstreethub.demo;

import com.mainstreethub.demo.resources.HelloEnvironmentResource;
import io.dropwizard.Application;
import io.dropwizard.bundles.version.VersionBundle;
import io.dropwizard.bundles.version.suppliers.MavenVersionSupplier;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DemoApplication extends Application<DemoConfiguration> {
  private static final String GROUP_ID = "com.mainstreethub.demo";
  private static final String ARTIFACT_ID = "application";

  public static void main(String[] args) throws Exception {
    new DemoApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<DemoConfiguration> bootstrap) {
    bootstrap.addBundle(new VersionBundle(new MavenVersionSupplier(GROUP_ID, ARTIFACT_ID)));
  }

  @Override
  public void run(DemoConfiguration configuration, Environment environment) throws Exception {
    environment.jersey().register(new HelloEnvironmentResource(configuration));
  }
}
