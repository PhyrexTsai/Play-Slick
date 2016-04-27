name := "Play-Slick"

version := "1.0"

scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  jdbc,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "com.typesafe.slick" %% "slick-codegen" % "3.0.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.play" %% "play-json" % "2.4.3"
)

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )

slick <<= slickCodeGenTask

sourceGenerators in Compile <+= slickCodeGenTask

lazy val slick = TaskKey[Seq[File]]("gen-tables")
lazy val slickCodeGenTask = (sourceManaged, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
  val outputDir = "./src/main/scala"
  val username = "username"
  val password = "password"
  val url = "jdbc:postgresql://localhost/dbname"
  val jdbcDriver = "org.postgresql.Driver"
  val slickDriver = "slick.driver.PostgresDriver"
  val pkg = "dao"
  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg, username, password), s.log))
  val fname = outputDir + "/dao/Tables.scala"
  Seq(file(fname))
}