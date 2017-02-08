def dUtility = new mainstreethub.pipelines.Dropwizard(steps)

node("java:8") {
  println "About to compile"
  checkout scm
  dUtility.doStuff(env)
}