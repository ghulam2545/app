PORT:
customer-service: 8089

docker exec -it customer_service_db psql -U customer_service_user -d customer_service_db
sudo service postgresql status

docker compose up --build -d
