# Jetty 9 Embedded on CloudBees

Jetty is a fast lightweight servlet engine for the JVM.

One of the best ways (and most popular) to deploy Jetty is to embed it in the app and produce an executable app that is ready to go, no external dependencies needed.

<img src="https://raw.github.com/CloudBees-community/jetty9-embedded-clickstart/master/icon.png"/>

That is what this clickstart does (and also shows it running on CloudBees with just a JVM).
Use this as a starting point if you like, works with any version of Jetty you need.

Press the big button :

<a href="https://grandcentral.cloudbees.com/?CB_clickstart=https://raw.github.com/CloudBees-community/jetty9-embedded-clickstart/master/clickstart.json"><img src="https://d3ko533tu1ozfq.cloudfront.net/clickstart/deployInstantly.png"/></a>



# Running locally during development

    mvn clean compile exec:java

# Deploying by hand

    mvn
    bees app:deploy -t java -R class=localdomain.localhost.jetty.Main target/jetty9-embedded-clickstart-0.1-SNAPSHOT-jar-with-dependencies.jar


# Executable jar

    mvn clean package
    java -jar target/jetty9-embedded-clickstart-0.1-SNAPSHOT-jar-with-dependencies.jar

You can also run that executable jar anywhere using java -jar... (it will default to port 8080 when not running in the cloud).


# FAQ

## Why using the Maven Goal `package` instead of `assembly:single`

To make it easier, we configured maven to execute the goal `assembly:single` in the phase `package`.

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>2.4</version>
    <configuration>
        <archive>
            <manifest>
                <mainClass>localdomain.localhost.jetty.Main</mainClass>
            </manifest>
        </archive>
        <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
    </configuration>
    <executions>
        <execution>
            <id>make-my-jar-with-dependencies</id>
            <phase>package</phase>
            <goals>
                <goal>single</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

