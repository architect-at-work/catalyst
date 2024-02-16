# Service

## Tools Integrated

1. Flyway for database migration
2. Test Container For integration testing

## Steps to follow after cloning this for new service

1. Find `schemaName` and replace with `$service-schema-name`
2. Create or Update global ~/.gradle/gradle.properties with username and password for package manager
    ```shell
    # Warning: Don't put any quotes when you are setting gradle.properties
    gpr.user=<<github_username>>
    gpr.key=<<github_token_to_read_package>>
    ```

### Developer Tools

1. Install Docker
    ```shell
    brew install docker
    brew install colima
    brew install docker-compose
    colima start
    sudo ln -s $HOME/.colima/docker.sock /var/run/docker.sock
    ``` 

2. To create migration file run

   ```shell
   ./migration-file-generator.sh {description}
   #./migration-file-generator.sh create_user_table
   ```

3. Running the app in `local` profile using gradle
   ```shell
   ./gradlew bootRun  --args='--spring.profiles.active=local'
   ```

4. Code Formatter

    1. Download the plugin
       from [spring-javaformat-intellij-idea-plugin-0.0.41.jar](https://repo1.maven.org/maven2/io/spring/javaformat/spring-javaformat-intellij-idea-plugin/0.0.41/).
       One with size 14711484
    2. Open Intellij -> Settings -> Plugins -> Click on 3 dots after Installed -> Install plugin from Disk -> Select the
       aforementioned jar
    3. Restart the Intellij
    4. From now on, CMD + Option + L or Auto Format would format the files automatically

   > Note **./gradlew build** would fail, if format is wrong, however **./gradlew format** would auto format files for
   > you :)

5. Gradle Tree
    1. To see the tasks and dependencies tree, run
       ```shell
       ./gradlew tree
       ```

6. To run as docker container
    ```shell
    ./gradlew :service:bootjar
   docker build -t myorg/myservice .
   docker run -p 9000:9000 myorg/myapp -e "JAVA_OPTS=-Xmx128m" --DB_HOST=host.docker.internal --DB_USER=<<username>> --DB_PASSWORD=<<password>> --server.port=9000
    ```