# telekom-lottery Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar input.txt`.

It is configured to use INFO level logging, but with -Dquarkus.log.level=DEBUG argument it will be more verbose, giving meaningful debug info.

## Instructions

After starting the console needs the user input after **READY** is displayed.

When a single line or multiple lines are given an ENTER should be pressed in order to notify the application (readline method) to start processing the input file and check the lottery winners.

The result count of the winners will be displayed in the following format: _ _ _ _
Which means two three four and five hits in sorted ordering. After the results the application will ask for the next input in a recursive way. 

You can stop the application by pressing CTRL / CMD + C

## Related Guides

- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin
