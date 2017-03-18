# Spring Boot JPA with Vault

## Spin up Vault and PostgreSQL
```bash
$ docker-compose up -d
```

## Vault Setup
Vault must be configured to inteact with PostgreSQL.  The interaction steps are codified in `vault/setup.sh` (which gets mounted in the Vault container as `/tmp/setup.sh`):
```bash
$ docker-compose exec vault /bin/sh /tmp/setup.sh
```

> Now vault is setup to provision and remove of credentials on the fly.

## Run Spring Boot
```bash
$ ./gradlew bootRun
```
