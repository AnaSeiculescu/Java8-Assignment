## steps to run

From the root folder, run the following in the CLI
1. `mvn package`
2. `java -cp .\target\Java8-Assignment-1.0-SNAPSHOT.jar org.example.App`

You should see the app's output in the console.
<br><br>
If You want to run the command application with command line arguments,
simply add them at the end of your execution command like so:
```shell
java -cp .\target\Java8-Assignment-1.0-SNAPSHOT.jar org.example.App first.arg.here I_AM_THE_SECOND_ARGUMENT
```