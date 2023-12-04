CREATE TABLE IF NOT EXISTS accounts (id_account SERIAL PRIMARY KEY,
                                                               name_account VARCHAR(255) NOT NULL,
                                                                                         current_balance NUMERIC(10,2) NOT NULL,
                                                                                                                       currency_id INT NOT NULL,
                                     FOREIGN KEY (currency_id) REFERENCES currencies(currency_id));


INSERT INTO accounts (name_account, current_balance, currency_id)
VALUES ('John',
        900,
        1), ('jin',
             300,
             2), ('bo',
                  750,
                  3);

