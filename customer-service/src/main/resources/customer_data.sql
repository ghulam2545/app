DROP TABLE IF EXISTS customer_tb;

CREATE TABLE customer_tb (
     customer_id SERIAL PRIMARY KEY,
     first_name VARCHAR(50) NOT NULL,
     last_name VARCHAR(50) NOT NULL
);

INSERT INTO customer_tb (first_name, last_name) VALUES
('John', 'Doe'),
('Alice', 'Smith'),
('Bob', 'Johnson'),
('Emma', 'Williams'),
('Michael', 'Brown');
