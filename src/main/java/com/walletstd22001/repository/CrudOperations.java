
package com.walletstd22001.repository;

import java.util.List;

import com.walletstd22001.model.Transaction;

public interface CrudOperations<T, U> {
  void insert(T entity);

  List<T> getAll();

  T getById(U id);

  Transaction updateById(U id, T entityToUpdate);
}
