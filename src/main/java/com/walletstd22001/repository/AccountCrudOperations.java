package com.walletstd22001.repository;

import com.walletstd22001.model.Accounts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountCrudOperations implements CrudOperations<Accounts, String> {
    private Connection connection;

    @Override
    public void insert(Accounts account) {
        String sql = "INSERT INTO accounts(" +
                "name_account, " +
                "current_balance, " +
                "currency_id) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, account.getName_account());
            statement.setBigDecimal(2, account.getCurrent_balance());
            statement.setLong(3, account.getCurrency_id());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating account failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId_account(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating account failed, no ID obtained.");
                }
            }

            System.out.println("Entity inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Accounts> getAll() {
        List<Accounts> allAccounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                allAccounts.add(new Accounts(
                        result.getLong("id_account"),
                        result.getString("name_account"),
                        result.getBigDecimal("current_balance"),
                        result.getLong("currency_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAccounts;
    }

    @Override
    public Accounts getById(String id) {
        // Assurez-vous que votre méthode getById prend un paramètre de type String si
        // nécessaire
        String sql = "SELECT * FROM accounts WHERE id_account = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Accounts(
                        result.getLong("id_account"),
                        result.getString("name_account"),
                        result.getBigDecimal("current_balance"),
                        result.getLong("currency_id"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public void updateById(String id, String password) {
        String sql = "UPDATE accounts SET password = ? WHERE id_account = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, password);
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Accounts getById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public Accounts updateById(int id, String entityToUpdate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }
}
