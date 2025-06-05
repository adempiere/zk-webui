# ADempiere ZK Web User Interface

Project implementing a web interface based on the latest version of ZK

## Tasks:
1. Migrate from ZK version 3.6 to the latest stable version of ZK
2. Separate the zkwebui directory from the main project into a separate repository, creating a separate project that can be deployed on any server that supports the https://jakarta.ee/ standard
3. Implement build tools based on [Gradle](https://gradle.org/) and [SBT](https://www.scala-sbt.org/)

The old zkwebui is working with a very old version of the 3.6 library, so migration is a priority. On the other hand, very soon, application servers such as Tomcat and Jetty will stop supporting the old javax package system that was deprecated by Oracle and is now part of the https://jakarta.ee/ specification, so migration is mandatory.

## Support for the following application servers:

### Jetty (https://jetty.org/)	
HTTP/1.1 (RFC 7230), HTTP/2 (RFC 7540), WebSocket (RFC 6455, JSR 356), FastCGI, JakartaEE Namespace [2], JavaEE Namespace

## Apache Tomcat (https://tomcat.apache.org/)
This is the top-level entry point of the documentation bundle for the Apache Tomcat Servlet/JSP container. Apache Tomcat version 11.0 implements the Servlet 6.0 and Pages 4.0 specifications from Jakarta EE, and includes many additional features that make it a useful platform for developing and deploying web applications and web services.

## WildFly (https://www.wildfly.org/)

WildFly supports the latest standards for REST based data access, including JAX-RS 2, and JSON-P.
Building on Jakarta EE provides rich enterprise capabilities in easy to consume frameworks that eliminate boilerplate and reduce technical burden.
The quick boot of WildFly combined with the easy-to-use Arquillian framework allows for test driven development using the real environment your code will be running in. Your test code is separate and simply deployed along side your application where it has full access to server resources.
