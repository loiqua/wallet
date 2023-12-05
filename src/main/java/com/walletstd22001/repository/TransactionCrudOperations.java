package com.walletstd22001.repository;

import com.walletstd22001.model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionCrudOperations implements CrudOperations<Transaction, Integer> {
    private Connection connection;

    public TransactionCrudOperations(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Transaction transaction) {
        String sql = "INSERT INTO transactions(" +
                "transaction_date, " +
                "amount, " +
                "description, " +
                "account_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, Date.valueOf(transaction.getTransaction_date()));
            statement.setBigDecimal(2, transaction.getAmount());
            statement.setString(3, transaction.getDescription());
            statement.setLong(4, transaction.getAccount_id());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transaction.setTransaction_id(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Insertion failed, no ID obtained.");
                }
            }

            System.out.println("Entity inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> allTransactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                allTransactions.add(new Transaction(
                        result.getLong("transaction_id"),
                        result.getDate("transaction_date").toLocalDate(),
                        result.getBigDecimal("amount"),
                        result.getString("description"),
                        result.getLong("account_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTransactions;
    }

    @Override
    public Transaction getById(Integer id) {
        String sql = "SELECT * FROM transactions WHERE transaction_id = " + id;

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                return new Transaction(
                        result.getLong("transaction_id"),
                        result.getDate("transaction_date").toLocalDate(),
                        result.getBigDecimal("amount"),
                        result.getString("description"),
                        result.getLong("account_id"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public Transaction updateById(Integer id, Transaction updatedTransaction) {
        String sql = "UPDATE transactions SET " +
                "transaction_date = ?, " +
                "amount = ?, " +
                "description = ?, " +
                "account_id = ? " +
                "WHERE transaction_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(updatedTransaction.getTransaction_date()));
            statement.setBigDecimal(2, updatedTransaction.getAmount());
            statement.setString(3, updatedTransaction.getDescription());
            statement.setLong(4, updatedTransaction.getAccount_id());
            statement.setLong(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getById(id);
    }
}
