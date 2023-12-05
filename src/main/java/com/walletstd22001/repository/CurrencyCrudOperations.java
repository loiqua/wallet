package com.walletstd22001.repository;

import com.walletstd22001.model.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyCrudOperations implements CrudOperations<Currency, String> {
    private Connection connection;

    public CurrencyCrudOperations(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Currency currency) {
        String sql = "INSERT INTO currencies(currency_name) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, currency.getCurrency_name());
            statement.executeUpdate();
            System.out.println("Entity inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Currency> getAll() {
        List<Currency> allCurrencies = new ArrayList<>();
        String sql = "SELECT * FROM currencies";

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                allCurrencies.add(new Currency(
                        result.getLong("currency_id"),
                        result.getString("currency_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCurrencies;
    }

    @Override
    public Currency getById(String id) {
        // La méthode getById avec String n'est pas implémentée, vous pouvez laisser non
        // implémentée ou lancer une exception non supportée.
        throw new UnsupportedOperationException("Unimplemented method 'getById' with String");
    }

    @Override
    public void updateById(String id, Currency entityToUpdate) {
        // La méthode updateById avec String n'est pas implémentée, vous pouvez laisser
        // non implémentée ou lancer une exception non supportée.
        throw new UnsupportedOperationException("Unimplemented method 'updateById' with String");
    }

    @Override
    public Currency getById(int id) {
        String sql = "SELECT * FROM currencies WHERE currency_id = " + id;

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                return new Currency(
                        result.getLong("currency_id"),
                        result.getString("currency_name"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public Currency updateById(int id, String currency_name) {
        String sql = "UPDATE currencies SET currency_name = ? WHERE currency_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, currency_name);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getById(id);
    }
}
