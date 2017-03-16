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


* Configure Vault CLI
```bash
$ export VAULT_TOKEN='root-vault-token' && \
  export VAULT_ADDR='http://0.0.0.0:8200'
```

* Mount the PostgreSQL Secret Backend
```bash
$ vault mount postgresql
```

* Give Vault the connection information for PostgreSQL
```bash
$ vault write postgresql/config/connection \
  connection_url="postgresql://root:postgresql-root-password@postgresql:5432/postgres?sslmode=disable"
```

* Establish the lease timeframe for the PostgreSQL `ALL` permission
```bash
$ vault write postgresql/config/lease \
  lease=1m \
  lease_max=5m
```

* Configure the SQL commands to provision a new account and revoke an account
```bash
$ vault write postgresql/roles/all \
  sql="CREATE USER '{{name}}'@'%' IDENTIFIED BY '{{password}}';GRANT ALL ON *.* TO '{{name}}'@'%';" \
  revocation_sql="DROP USER '{{name}}'@'%';"
```
