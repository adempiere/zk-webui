# ADempiere ZK Web User Interface

Project implementing a web interface based on the latest version of ZK

## Tasks:
1. Migrate from ZK version 3.6 to the latest stable version of ZK
2. Separate the zkwebui directory from the main project into a separate repository, creating a separate project that can be deployed on any server that supports the https://jakarta.ee/ standard
3. Implement build tools based on [Gradle](https://gradle.org/) and [SBT](https://www.scala-sbt.org/)

The old zkwebui is working with a very old version of the 3.6 library, so migration is a priority. On the other hand, very soon, application servers such as Tomcat and Jetty will stop supporting the old javax package system that was deprecated by Oracle and is now part of the https://jakarta.ee/ specification, so migration is mandatory.
