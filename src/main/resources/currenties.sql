CREATE TABLE IF NOT EXISTS currencies (id_currency SERIAL PRIMARY KEY,
                                                                  currency_name VARCHAR(50) NOT NULL,
                                                                                            currency_code VARCHAR(25) NOT NULL);


INSERT INTO currencies (currency_name, currency_code)
VALUES ('Euro',
        'EUR'), ('Ariary',
                 'MGA');

