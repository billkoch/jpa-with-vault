# Spring Boot JPA with Vault

## Spin up Vault and MySQL
```bash
$ docker-compose up -d
```

> Ensure MySQL has finished spinning up before attempting to setup Vault
```bash
mysql_1  | 2017-03-23T05:13:40.277916Z 0 [Note] Beginning of list of non-natively partitioned tables
mysql_1  | 2017-03-23T05:13:40.285691Z 0 [Note] End of list of non-natively partitioned tables
mysql_1  | 2017-03-23T05:13:40.285861Z 0 [Note] mysqld: ready for connections.
mysql_1  | Version: '5.7.17'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server (GPL)
```

## Vault Setup
Vault must be configured to interact with MySQL.  The interaction steps are codified in `vault/setup.sh` (which gets mounted in the Vault container as `/tmp/setup.sh`):
```bash
$ docker-compose exec vault /bin/sh /tmp/setup.sh
```

> Now vault is setup to provision and remove of credentials on the fly.

## Run Spring Boot
```bash
$ ./gradlew bootRun
```
