package com.walletstd22001.repository;

import com.walletstd22001.model.Accounts;
import com.walletstd22001.model.Transaction;
import com.walletstd22001.model.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountCrudOperations implements CrudOperations<Accounts, Integer> {
    private Connection connection;

    public AccountCrudOperations(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Accounts account) {
        String sql = "INSERT INTO accounts(" +
                "accountName, " +
                "balance, " +
                "lastUpdateDate, " +
                "currency_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, account.getAccountName());
            statement.setDouble(2, account.getBalance());
            statement.setDate(3, account.getLastUpdateDate());
            statement.setInt(4, account.getCurrency().getCurrencyId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating account failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setAccountId(generatedKeys.getInt(1));
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
                Accounts account = new Accounts(0, sql, 0, null, null, null, sql);
                account.setAccountId(result.getInt("accountId"));
                account.setAccountName(result.getString("accountName"));
                account.setBalance(result.getDouble("balance"));
                account.setLastUpdateDate(result.getDate("lastUpdateDate"));
                // Ajoutez la logique pour récupérer les autres champs tels que transactionList, currency, accountType
                allAccounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAccounts;
    }

    @Override
    public Accounts getById(Integer id) {
        String sql = "SELECT * FROM accounts WHERE accountId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Accounts account = new Accounts(id, sql, id, null, null, null, sql);
                account.setAccountId(result.getInt("accountId"));
                account.setAccountName(result.getString("accountName"));
                account.setBalance(result.getDouble("balance"));
                account.setLastUpdateDate(result.getDate("lastUpdateDate"));
                // Ajoutez la logique pour récupérer les autres champs tels que transactionList, currency, accountType
                return account;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public Accounts updateById(Integer id, Accounts updatedAccount) {
        String sql = "UPDATE accounts SET accountName = ?, balance = ?, lastUpdateDate = ?, currency_id = ? WHERE accountId = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, updatedAccount.getAccountName());
            statement.setDouble(2, updatedAccount.getBalance());
            statement.setDate(3, updatedAccount.getLastUpdateDate());
            statement.setInt(4, updatedAccount.getCurrency().getCurrencyId());
            statement.setInt(5, id);
            statement.executeUpdate();
    
            System.out.println("Entity updated successfully");
            
            // Retourner l'objet Accounts mis à jour
            return getById(id); // Supposant que vous avez une méthode getById pour récupérer un account par son identifiant
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
