PORTS:
customer-service: 1111
postgres-customer: 5433
postgres-notification: 5434
notification-service: 2222
zookeeper: 3333
kafka-service: 4444



docker exec -it customer_service_db psql -U customer_service_user -d customer_service_db
sudo service postgresql status

docker compose up -d

sudo systemctl status docker
sudo systemctl stop docker
sudo systemctl start docker
