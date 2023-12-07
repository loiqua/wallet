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
                "transactionId, " +
                "label, " +
                "amount, " +
                "dateTime, " +
                "transactionType) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, transaction.getTransactionId());
            statement.setString(2, transaction.getLabel());
            statement.setDouble(3, transaction.getAmount());
            statement.setDate(4, new java.sql.Date(transaction.getDateTime().getTime()));
            statement.setString(5, transaction.getTransactionType());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transaction.setTransactionId(generatedKeys.getInt(1));
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
                        result.getInt("transactionId"),
                        result.getString("label"),
                        result.getDouble("amount"),
                        result.getDate("dateTime"),
                        result.getString("transactionType")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTransactions;
    }

    @Override
    public Transaction getById(Integer id) {
        String sql = "SELECT * FROM transactions WHERE transactionId = " + id;

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                return new Transaction(
                        result.getInt("transactionId"),
                        result.getString("label"),
                        result.getDouble("amount"),
                        result.getDate("dateTime"),
                        result.getString("transactionType"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public Transaction updateById(Integer id, Transaction updatedTransaction) {
        String sql = "UPDATE transactions SET " +
                "label = ?, " +
                "amount = ?, " +
                "dateTime = ?, " +
                "transactionType = ? " +
                "WHERE transactionId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, updatedTransaction.getLabel());
            statement.setDouble(2, updatedTransaction.getAmount());
            statement.setDate(3, new java.sql.Date(updatedTransaction.getDateTime().getTime()));
            statement.setString(4, updatedTransaction.getTransactionType());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getById(id);
    }
}
