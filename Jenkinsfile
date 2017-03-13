//def dropwizard = new mainstreethub.pipelines.Dropwizard()
//
//dropwizard.pipeline(
//  ecs: new mainstreethub.vars.Ecs(
//      externalAlb: true,
//      instanceCount: 1
//  ),
//    application: "jenkins-pipeline-demo-app"
//)


def dropwizard = new com.mainstreethub.jenkins.pipelines.DropwizardPipeline()


dropwizard.dropwizardBuildTestReleaseDeploy("jenkins-pipeline-demo-app");

