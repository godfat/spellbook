
name                                    := "spellbook"

version                                 := "0.1"

scalaVersion                            := "2.9.1"

scalaSource       in Compile            <<= baseDirectory(_ / "src")

resourceDirectory in Compile            <<= baseDirectory(_ / "src")

scalaSource       in Test               <<= baseDirectory(_ / "test")

defaultExcludes   in unmanagedResources := "*.scala"

scalacOptions                           ++= Seq("-deprecation", "-unchecked")

unmanagedJars     in Test               += Attributed.blank(
  file("/usr/local/Cellar/jruby/1.6.4/jruby/lib/jruby.jar"))

libraryDependencies                     ++= Seq(
   "org.scala-tools.testing" %% "scalacheck" % "1.9" % "test")
