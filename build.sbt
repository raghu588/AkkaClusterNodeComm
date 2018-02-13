name := "AkkaCluster1"
version := "0.1"
scalaVersion := "2.12.0"

libraryDependencies += "com.typesafe.akka" %% "akka-cluster" % "2.5.9"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.9"
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.5.9"
libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "3.0.0"
libraryDependencies += "org.apache.cassandra" % "cassandra-all" % "2.1.2"

