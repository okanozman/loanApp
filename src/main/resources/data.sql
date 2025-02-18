CREATE TABLE customer (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255),
                          surname VARCHAR(255),
                          credit_limit DOUBLE
);

INSERT INTO customer (name, surname, credit_limit) VALUES ('John', 'Doe', 10000);
INSERT INTO customer (name, surname, credit_limit) VALUES ('Jane', 'Smith', 15000);
INSERT INTO customer (name, surname, credit_limit) VALUES ('Hanry', 'Gatwick', 55000);
INSERT INTO customer (name, surname, credit_limit) VALUES ('Oliver', 'Twist', 65000);
INSERT INTO customer (name, surname, credit_limit) VALUES ('John', 'Travolta', 75000);
INSERT INTO customer (name, surname, credit_limit) VALUES ('Mary', 'Klein', 35000);
INSERT INTO customer (name, surname, credit_limit) VALUES ('Andreas', 'Shevshenko', 25000);
INSERT INTO customer (name, surname, credit_limit) VALUES ('Peter', 'Joshua', 55000);