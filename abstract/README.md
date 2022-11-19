# Abstract

Learning the use of abstract classes

See https://www.youtube.com/watch?v=5qP-lcg53H0

## Setupup this code

```shell
mvn archetype:generate -DgroupId=bsoft.com -DartifactId=abstract -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

## Logging
See 
- https://dzone.com/articles/adding-slf4j-your-maven
- https://www.slf4j.org/manual.html
- https://www.baeldung.com/log4j-no-appenders-found


# Abstract classes

Usage:
- Avoid code duplication an increase reuseability
- Are used as baseclass for (non) abstract classes
- Encapsulate common functionality in one place and let sub classes implement differences

Restrictions:
- Cannot be instantiated
- have no method body
- need to be inside abstract classes or interfaces

# Maven dependencies
See https://repository.sonatype.org/#welcome

# Run java using maven

```shell
bvpelt@pluto:~/Develop/spring-tutorials/abstract$ mvn exec:java -Dexec.mainClass="bsoft.com.App"
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------------< bsoft.com:abstract >-------------------------
[INFO] Building abstract 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- exec-maven-plugin:3.1.0:java (default-cli) @ abstract ---
Hello World!
2022-11-16 20:54:21 INFO Woff Woff
2022-11-16 20:54:21 INFO Miaoo
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.274 s
[INFO] Finished at: 2022-11-16T20:54:21+01:00
[INFO] ----------------------------------------------------
```

# Tutorial

## Tasks
- Implement NumberExtractorReport
- Refactor
  - Extract common functionality (into Extract Report)
    - open file
    - read file
    - abstract match function
    - send email