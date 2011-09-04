
name         := "spellbook"

version      := "0.1"

scalaVersion := "2.9.1"

scalaSource       in Compile <<= baseDirectory(_ / "src")

resourceDirectory in Compile <<= baseDirectory(_ / "src")

defaultExcludes in unmanagedResources := "*.scala"

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= Seq(
   "org.scala-tools.testing" %% "scalacheck" % "1.9" % "test")
