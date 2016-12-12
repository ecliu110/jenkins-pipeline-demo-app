package com.mainstreethub.demo;

import com.codahale.metrics.servlets.PingServlet;
import com.google.common.io.Resources;
import com.mainstreethub.demo.resources.HelloEnvironmentResource;
import de.thomaskrille.dropwizard_template_config.TemplateConfigBundle;
import io.dropwizard.Application;
import io.dropwizard.bundles.version.VersionBundle;
import io.dropwizard.bundles.version.suppliers.MavenVersionSupplier;
import io.dropwizard.configuration.FileConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DemoApplication extends Application<DemoConfiguration> {
  private static final String GROUP_ID = "com.mainstreethub.demo";
  private static final String ARTIFACT_ID = "application";
  private static final String CLASSPATH = "classpath://";

  public static void main(String[] args) throws Exception {
    new DemoApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<DemoConfiguration> bootstrap) {
    bootstrap.addBundle(new VersionBundle(new MavenVersionSupplier(GROUP_ID, ARTIFACT_ID)));

    // Load configuration from the classpath
    bootstrap.setConfigurationSourceProvider(path -> path.startsWith(CLASSPATH)
        ? Resources.getResource(path.substring(CLASSPATH.length())).openStream()
        : new FileConfigurationSourceProvider().open(path));
    bootstrap.addBundle(new TemplateConfigBundle());
  }

  @Override
  public void run(DemoConfiguration configuration, Environment environment) throws Exception {
    environment.jersey().register(new HelloEnvironmentResource(configuration));

    // Register the ping servlet on the main port for ECS dynamic port mappings
    environment.servlets().addServlet("ping", new PingServlet()).addMapping("/ping");
  }
}
