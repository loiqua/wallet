CREATE TABLE IF NOT EXISTS accounts (id_account SERIAL PRIMARY KEY,
                                                               name_account VARCHAR(150),
                                                                            current_balance NUMERIC(15,2),
                                                                                            update_date TIMESTAMP DEFAULT current_timestamp,
                                                                                                                          account_type VARCHAR(150) NOT NULL,
                                                                                                                                                    currency_id INT REFERENCES currencies(id_currency));


INSERT INTO accounts (name_account, current_balance, account_type, currency_id)
VALUES ('Compte Courant',
        1000.00,
        'Banque',
        1), ('Portefeuille',
             500.00,
             'Espèce',
             2);