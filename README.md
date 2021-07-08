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

The application is now runnable using: 
```shell script
java -jar build/quarkus-app/quarkus-run.jar input.txt
```

`INFO` level is used by default for logging, but with `-Dquarkus.log.level=DEBUG` argument the progress of the search and additional debug information is printed out to the console. 

(For example what threads are finished with processing, what are the invalid values that are skipped etc.)
```shell script
java -jar -Dquarkus.log.level=DEBUG build/quarkus-app/quarkus-run.jar input.txt`
```

## Instructions

### Starting up

After starting the console needs the user input after `READY` is displayed. This takes a significant amount of time, if we are talking about huge input files.

```shell script
>> java -jar build/quarkus-app/quarkus-run.jar input.txt

__  ____  __  _____   ___  __ ____  ______
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/
2021-07-08 11:05:26,232 INFO  [io.quarkus] (main) telekom-lottery 1.0.0-SNAPSHOT on JVM (powered by Quarkus 2.0.0.Final) started in 0.455s.
2021-07-08 11:05:26,262 INFO  [io.quarkus] (main) Profile prod activated.
2021-07-08 11:05:26,262 INFO  [io.quarkus] (main) Installed features: [cdi, kotlin]
READY
```

When a single line is given, an `ENTER` should be pressed in order to notify the application (readline method) to start processing the input file. 

The lottery winners will be checked, and printed out to the console, if the user has given valid lottery numbers separated by space.

```shell script
>> java -jar build/quarkus-app/quarkus-run.jar input.txt

__  ____  __  _____   ___  __ ____  ______
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/
2021-07-08 11:05:26,232 INFO  [io.quarkus] (main) telekom-lottery 1.0.0-SNAPSHOT on JVM (powered by Quarkus 2.0.0.Final) started in 0.455s.
2021-07-08 11:05:26,262 INFO  [io.quarkus] (main) Profile prod activated.
2021-07-08 11:05:26,262 INFO  [io.quarkus] (main) Installed features: [cdi, kotlin]
READY
65 13 62 26 55
224386 8268 105 1
```

The application can consume multiple lines if they are `ENTER` terminated, in that way the search will happen on different threads. 

    / NOTE: For that it's worth to turn on debug mode so the user can see the winners for the belonging drawn numbers. /


After the input has given the application will start searching in the background, so the console is immediately available for additional drawn numbers. 

### Debug mode
```shell script
 java -jar -Dquarkus.log.level=DEBUG build/quarkus-app/quarkus-run.jar input.txt
 
__  ____  __  _____   ___  __ ____  ______
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/
2021-07-08 11:11:58,119 DEBUG [org.jbo.logging] (main) Logging Provider: org.jboss.logging.JBossLogManagerProvider
2021-07-08 11:11:58,410 DEBUG [io.qua.arc.impl] (main) ArC DI container initialized [beans=9, observers=0]
2021-07-08 11:11:58,459 DEBUG [org.jbo.threads] (main) JBoss Threads version 3.4.0.Final
2021-07-08 11:11:58,541 INFO  [io.quarkus] (main) telekom-lottery 1.0.0-SNAPSHOT on JVM (powered by Quarkus 2.0.0.Final) started in 0.579s.
2021-07-08 11:11:58,542 INFO  [io.quarkus] (main) Profile prod activated.
2021-07-08 11:11:58,542 INFO  [io.quarkus] (main) Installed features: [cdi, kotlin]
2021-07-08 11:11:58,552 DEBUG [ser.NumberReaderService] (main) Reading file: /Users/tamastomorkenyi/temp/input.txt
2021-07-08 11:12:00,542 DEBUG [ser.NumberReaderService] (main) Has duplicates or not lottery numbers in the required format: 70 6 20 46 70 will be skipped
2021-07-08 11:12:00,543 DEBUG [ser.NumberReaderService] (main) Has duplicates or not lottery numbers in the required format: 84 12 68 26 47 40 will be skipped
2021-07-08 11:12:00,544 DEBUG [ser.NumberReaderService] (main) Has duplicates or not lottery numbers in the required format: 40 11 57 36 40 will be skipped
2021-07-08 11:12:00,544 DEBUG [ser.NumberReaderService] (main) Has duplicates or not lottery numbers in the required format: 5 34 24 42 will be skipped
2021-07-08 11:12:00,544 DEBUG [ser.NumberReaderService] (main) Has duplicates or not lottery numbers in the required format: 85 21 67 93 2 will be skipped
2021-07-08 11:12:00,550 DEBUG [ser.NumberReaderService] (main) Has duplicates or not lottery numbers in the required format: 10 85 69 -7 50 will be skipped
2021-07-08 11:12:17,270 DEBUG [ser.NumberReaderService] (main) Reading input file finished.
READY
5 3 4 2 1
5 4 3 2 1
1 2 3 4 5
3 32 33 34 35
12 13 14 15 16
65 13 62 26 55
27 77 41 29 30
88 41 35 57 32
...
2021-07-08 11:15:51,152 DEBUG [ser.WinnerEvaluatorService] (ForkJoinPool.commonPool-worker-3) For [5, 3, 4, 2, 1] the winners are: {0=7463771, 1=2302757, 2=225152, 3=8219, 4=95}
2021-07-08 11:15:51,171 DEBUG [ser.WinnerEvaluatorService] (ForkJoinPool.commonPool-worker-9) For [3, 32, 33, 34, 35] the winners are: {0=7462313, 1=2304860, 2=224566, 3=8163, 4=92}
2021-07-08 11:15:51,200 DEBUG [ser.WinnerEvaluatorService] (ForkJoinPool.commonPool-worker-13) For [65, 13, 62, 26, 55] the winners are: {0=7463516, 1=2303718, 2=224386, 3=8268, 4=105, 5=1}
2021-07-08 11:15:51,202 DEBUG [ser.WinnerEvaluatorService] (ForkJoinPool.commonPool-worker-7) For [1, 2, 3, 4, 5] the winners are: {0=7463771, 1=2302757, 2=225152, 3=8219, 4=95}
224566 8163 92 0
224386 8268 105 1
225152 8219 95 0
225152 8219 95 0
2021-07-08 11:15:51,205 DEBUG [ser.LotteryService] (ForkJoinPool.commonPool-worker-9) Search finished for [3, 32, 33, 34, 35]
2021-07-08 11:15:51,206 DEBUG [ser.LotteryService] (ForkJoinPool.commonPool-worker-13) Search finished for [65, 13, 62, 26, 55]
2021-07-08 11:15:51,206 DEBUG [ser.LotteryService] (ForkJoinPool.commonPool-worker-7) Search finished for [1, 2, 3, 4, 5]
2021-07-08 11:15:51,206 DEBUG [ser.WinnerEvaluatorService] (ForkJoinPool.commonPool-worker-11) For [12, 13, 14, 15, 16] the winners are: {0=7462703, 1=2303515, 2=225489, 3=8185, 4=102}
225489 8185 102 0
2021-07-08 11:15:51,206 DEBUG [ser.LotteryService] (ForkJoinPool.commonPool-worker-3) Search finished for [5, 3, 4, 2, 1]
2021-07-08 11:15:51,207 DEBUG [ser.LotteryService] (ForkJoinPool.commonPool-worker-11) Search finished for [12, 13, 14, 15, 16]
2021-07-08 11:15:51,208 DEBUG [ser.WinnerEvaluatorService] (ForkJoinPool.commonPool-worker-5) For [5, 4, 3, 2, 1] the winners are: {0=7463771, 1=2302757, 2=225152, 3=8219, 4=95}
225152 8219 95 0
2021-07-08 11:15:51,208 DEBUG [ser.LotteryService] (ForkJoinPool.commonPool-worker-5) Search finished for [5, 4, 3, 2, 1]
2021-07-08 11:15:51,210 DEBUG [ser.WinnerEvaluatorService] (ForkJoinPool.commonPool-worker-15) For [27, 77, 41, 29, 30] the winners are: {0=7463297, 1=2303763, 2=224726, 3=8122, 4=85, 5=1}
224726 8122 85 1
2021-07-08 11:15:51,210 DEBUG [ser.LotteryService] (ForkJoinPool.commonPool-worker-15) Search finished for [27, 77, 41, 29, 30]
```

### Terminating
You can stop the application any time by pressing `CTRL / CMD + C`

### Troubleshooting
The application requires a significant amount of allocated memory if the inputs are huge files. That is because the search should be as fast as possible.

If an OutOfMemoryError has been thrown please launch the application with the `-Xmx4096m` argument.

```shell script
java -Xmx4096m -jar build/quarkus-app/quarkus-run.jar input.txt
```

## Related Guides

- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin
