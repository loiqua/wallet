CREATE TABLE IF NOT EXISTS transactions (transaction_id SERIAL PRIMARY KEY,
                                                                       account_id INTEGER NOT NULL,
                                                                                          description TEXT, amount NUMERIC(10,2) NOT NULL,
                                                                                                                                 currency_id INT NOT NULL,
                                                                                                                                                 transaction_date DATE NOT NULL,
                                         FOREIGN KEY (account_id) REFERENCES accounts(id_account));

-- Modification de la définition de la table transactions pour autoriser NULL dans account_id

ALTER TABLE transactions
ALTER COLUMN account_id
DROP NOT NULL;

-- Puis, vous pouvez réessayer votre insertion avec des valeurs NULL

INSERT INTO transactions (account_id, description, amount, currency_id, transaction_date)
VALUES (4,
        'Purchase 1',
        100.50,
        1,
        '2023-01-01'), (4,
                        'Purchase 2',
                        75.25,
                        1,
                        '2023-01-02'), (5,
                                        'Expense 1',
                                        50.00,
                                        2,
                                        '2023-01-03'), (5,
                                                        'Purchase 3',
                                                        120.75,
                                                        2,
                                                        '2023-01-04'), (NULL,
                                                                        'Withdrawal 1',
                                                                        30.00,
                                                                        1,
                                                                        '2023-01-05');

