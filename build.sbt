resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith", "maven")
libraryDependencies ++=   Seq(
  "edu.holycross.shot.cite" %% "xcite" % "4.3.0",
  "edu.holycross.shot" %% "citebinaryimage" % "3.2.0",
)
