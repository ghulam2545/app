DROP TABLE IF EXISTS customer_tb;

CREATE TABLE customer_tb (
     customer_id VARCHAR(36) PRIMARY KEY,
     first_name VARCHAR(50) NOT NULL,
     last_name VARCHAR(50) NOT NULL
);

INSERT INTO customer_tb (customer_id, first_name, last_name) VALUES
('a7d1e8b23f4c5d6e7f8a9b0c1d2e3f4a', 'John', 'Doe'),
('b0c1d2e3f4a56b7c8d9e0f1a2b3c4d5e', 'Alice', 'Smith'),
('c4d5e6f7g8h9i0j1k2l3m4n5o6p7q8r9', 'Bob', 'Johnson'),
('d8e9f0a1b2c3d4e5f6g7h8i9j0k1l2m3', 'Emma', 'Williams'),
('e2f3g4h5i6j7k8l9m0n1o2p3q4r5s6t7', 'Michael', 'Brown');