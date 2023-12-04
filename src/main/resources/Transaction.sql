CREATE TABLE IF NOT EXISTS transactions (transaction_id SERIAL PRIMARY KEY,
                                                                       account_id INTEGER NOT NULL,
                                                                                          description TEXT, amount NUMERIC(10,2) NOT NULL,
                                                                                                                                 currency_id INT NOT NULL,
                                                                                                                                                 transaction_date DATE NOT NULL,
                                         FOREIGN KEY (account_id) REFERENCES accounts(id));


INSERT INTO transactions (account_id, description, amount, currency_id, transaction_date)
VALUES (1,
        'Purchase 1',
        100,
        1,
        '2023-01-01'), (2,
                        'Purchase 2',
                        50,
                        2,
                        '2023-01-02'), (3,
                                        'Purchase 3',
                                        200,
                                        1,
                                        '2023-01-03');