export VAULT_TOKEN='root-vault-token' && \
  export VAULT_ADDR='http://0.0.0.0:8200'

vault mount mysql

# Give Vault the MySQL connection information
vault write mysql/config/connection \
  connection_url="root:root@tcp(mysql:3306)/"

# Establish the lease timeframe
vault write mysql/config/lease \
  lease=30s \
  lease_max=1m

# Configure the SQL commands to provision a new account and revoke an account
vault write mysql/roles/all \
  sql="CREATE USER '{{name}}'@'%' IDENTIFIED BY '{{password}}';GRANT ALL PRIVILEGES ON *.* TO '{{name}}'@'%';" \
  revocation_sql="DROP USER '{{name}}'@'%';"
