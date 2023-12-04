CREATE TABLE IF NOT EXISTS currencies (currency_id SERIAL PRIMARY KEY,
                                                                  currency_name VARCHAR(255) NOT NULL);


INSERT INTO currencies (currency_name)
VALUES ('Euro'), ('Ariary');

