export VAULT_TOKEN='root-vault-token' && \
  export VAULT_ADDR='http://0.0.0.0:8200'

vault mount postgresql

# Give Vault the PostgreSQL connection information
vault write postgresql/config/connection \
  connection_url="postgresql://root:postgresql-root-password@postgresql:5432/postgres?sslmode=disable"

# Establish the lease timeframe
vault write postgresql/config/lease \
  lease=1m \
  lease_max=5m

# Configure the SQL commands to provision a new account and revoke an account
vault write postgresql/roles/all \
  sql="CREATE ROLE \"{{name}}\" WITH LOGIN PASSWORD '{{password}}' VALID UNTIL '{{expiration}}'; \
    GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO \"{{name}}\";" \
  revocation_sql="SET ROLE authserver; DROP ROLE "{{name}}";"
