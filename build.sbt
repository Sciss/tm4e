lazy val commonSettings = Seq(
  organization     := "de.sciss",
  version          := "0.1.0-SNAPSHOT",
  licenses         := Seq("EPL-1.0" -> url("https://opensource.org/licenses/eclipse-1.0.txt")),
  scalaVersion     := "2.13.6",
  autoScalaLibrary := false,
  crossPaths       := false,
  javacOptions in Compile ++= Seq("-g", "-target", "1.8", "-source", "1.8"),
  javacOptions in (Compile, doc) := Nil,
)

lazy val root = project.in(file("core"))
  .settings(commonSettings)
  .settings(
    name             := "tm4e-core",
   libraryDependencies ++= Seq(
      "org.jruby.joni"         % "joni"              % "2.1.11",
      "org.jruby.jcodings"     % "jcodings"          % "1.0.18",
      "com.google.code.gson"   % "gson"              % "2.8.6",
      "org.apache.xmlgraphics" % "batik-css"         % "1.13",
      "org.apache.xmlgraphics" % "batik-util"        % "1.13",
      "org.junit.jupiter"      % "junit-jupiter-api" % "5.7.1" % Test,
    ),
 )
