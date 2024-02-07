# Service

# Tools Integrated

1. Flyway for database migration

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