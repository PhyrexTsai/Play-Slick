# Play-Slick
Using Play and Slick connect to database and auto generate code from table.

## Introduction
This is a project using Play and Slick build a simple RESTful service.

## Library
* Scala : Core language.
* Play : Setting ```routes``` to display data.
* Slick : Database connection.
* Slick-codegen : Generate code using ```sbt```.
* Postgresql : Database library.

## Code-gen
Run ```sbt compile```, auto generate code to ```Tables.scala```.
```
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
```

# Run local
    > sbt run
It should run on : [http://localhost:9000](http://localhost:9000)
