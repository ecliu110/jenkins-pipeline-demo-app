@Library('Dropwizard')
import mainstreethub.pipelines.Dropwizard

def dUtility = new Dropwizard(steps)

node("java:8") {
  println "About to compile"
  checkout scm
  dUtility.doStuff(env)
}