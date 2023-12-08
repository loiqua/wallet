package com.walletstd22001.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.walletstd22001.model.Currency;

public class CurrencyCrudOperations implements CrudOperations<Currency, Integer> {
    private Connection connection;

    public CurrencyCrudOperations(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Currency currency) {
        String sql = "INSERT INTO currencies(name, code) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, currency.getName());
            statement.setString(2, currency.getCode());
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
                        result.getInt("currencyId"),
                        result.getString("name"),
                        result.getString("code")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCurrencies;
    }

    @Override
    public Currency getById(Integer id) {
        String sql = "SELECT * FROM Currency WHERE currencyId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return new Currency(
                        result.getInt("currency_id"),
                        result.getString("name"),
                        result.getString("code"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public Currency updateById(Integer id, Currency entityToUpdate) {
        String sql = "UPDATE Currency SET name = ?, code = ? WHERE currencyId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entityToUpdate.getName());
            statement.setString(2, entityToUpdate.getCode());
            statement.setInt(3, id);
            statement.executeUpdate();
            return getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}