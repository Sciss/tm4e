lazy val projectVersion = "0.1.0"

// sonatype plugin requires that these are in global
ThisBuild / version      := projectVersion
ThisBuild / organization := "de.sciss"

lazy val commonSettings = Seq(
  licenses         := Seq("EPL-1.0" -> url("https://opensource.org/licenses/eclipse-1.0.txt")),
  homepage         := Some(url(s"https://github.com/Sciss/${name.value}")),
  description      := "TextMate grammar support for Java",
  scalaVersion     := "2.13.6",  // unused
  autoScalaLibrary := false,
  crossPaths       := false,
  javacOptions in Compile ++= Seq("-g", "-target", "1.8", "-source", "1.8"),
  javacOptions in (Compile, doc) := Nil,
)

lazy val root = project.in(file("."))
  .settings(commonSettings)
  .settings(publishSettings)
  .settings(
    name             := "tm4e-core",
   libraryDependencies ++= Seq(
      "org.jruby.joni"         % "joni"              % "2.1.11",
      "org.jruby.jcodings"     % "jcodings"          % "1.0.18",
      "com.google.code.gson"   % "gson"              % "2.8.6",   // TODO: get rid of
      "org.apache.xmlgraphics" % "batik-css"         % "1.13",    // TODO: get rid of
      "org.apache.xmlgraphics" % "batik-util"        % "1.13",    // TODO: get rid of
      "org.junit.jupiter"      % "junit-jupiter-api" % "5.7.1" % Test,
    ),
 )

lazy val publishSettings = Seq(
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  developers := List(
    // XXX TODO --- where is the list of original developers?
    Developer(
      id    = "sciss",
      name  = "Hanns Holger Rutz",
      email = "contact@sciss.de",
      url   = url("https://www.sciss.de")
    )
  ),
  scmInfo := {
    val h = "github.com"
    val a = s"Sciss/${name.value}"
    Some(ScmInfo(url(s"https://$h/$a"), s"scm:git@$h:$a.git"))
  },
)

