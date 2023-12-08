CREATE TABLE IF NOT EXISTS currency_value (id_currency_value SERIAL PRIMARY KEY, -- Changement de type
 source_currency_id INT REFERENCES currencies(id_currency) NOT NULL, -- Changement de type
 destination_currency_id INT REFERENCES currencies(id_currency) NOT NULL, -- Changement de type
 conversion_rate NUMERIC(15,2) NOT NULL,
                               date_effect TIMESTAMP NOT NULL);


INSERT INTO currency_value (source_currency_id, destination_currency_id, conversion_rate, date_effect)
VALUES (1,
        2,
        4500.00,
        '2023-12-01'), (1,
                        2,
                        4600.00,
                        '2023-12-02');