CREATE TABLE IF NOT EXISTS transactions (id_transaction SERIAL PRIMARY KEY,
                                                                       label VARCHAR(100) NOT NULL,
                                                                                          transaction_type VARCHAR(150) NOT NULL,
                                                                                                                        transaction_date TIMESTAMP DEFAULT current_timestamp NOT NULL,
                                                                                                                                                                             transaction_time TIME, -- Ajout de la colonne transaction_time
 amount NUMERIC(15,2) NOT NULL,
                      account_id INT REFERENCES accounts(id_account));


INSERT INTO transactions (label, transaction_type, amount, account_id)
VALUES ('Salaire',
        'Crédit',
        1000.00,
        1), ('Dépense quotidienne',
             'Débit',
             50.00,
             2);

