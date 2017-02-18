# Spring Boot JPA with Vault

## Vault Setup
1. Open an interactive prompt from the Vault container
```bash
$ docker-compose exec vault /bin/sh
```

* Configure Vault CLI
```bash
$ export VAULT_TOKEN='root-vault-token' && \
  export VAULT_ADDR='http://0.0.0.0:8200'
```

* Mount the MySQL Secret Backend
```bash
$ vault mount postgresql
```

* Give Vault the connection information for PostgreSQL
```bash
$ vault write postgresql/config/connection \
  connection_url="postgresql://root:postgresql-root-password@postgresql:5432/postgres"
```

* Establish the lease timeframe for the MySQL `ALL` permission
```bash
$ vault write postgresql/config/lease \
  lease=1m \
  lease_max=5m
```

* Configure the SQL commands to provision a new account and revoke an account
```bash
$ vault write postgres/roles/all \
  sql="CREATE USER '{{name}}'@'%' IDENTIFIED BY '{{password}}';GRANT ALL ON *.* TO '{{name}}'@'%';" \
  revocation_sql="DROP USER '{{name}}'@'%';"
```
