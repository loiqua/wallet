CREATE TABLE IF NOT EXISTS transfer_history ( id_transfer UUID PRIMARY KEY,
                                                                       debit_transaction_id UUID REFERENCES transactions(id_transaction) NOT NULL,
                                                                                                                                         credit_transaction_id UUID REFERENCES transactions(id_transaction) NOT NULL,
                                                                                                                                                                                                            transfer_date TIMESTAMP DEFAULT current_timestamp NOT NULL,
                                                                                                                                                                                                                                                              transaction_time TIME -- Ajout de la colonne transaction_time
);


INSERT INTO transactions (label, transaction_type, amount, account_id)
VALUES ('Salaire',
        'Crédit',
        1000.00,
        1), ('Dépense quotidienne',
             'Débit',
             50.00,
             2);

