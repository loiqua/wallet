
package com.walletstd22001.repository;

import java.util.List;

public interface CrudOperations<T, U> {
  void insert(T entity);

  List<T> getAll();

  T getById(U id);

  void updateById(U id, T entityToUpdate);
}
