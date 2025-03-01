package com.practice.bmi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ExpenseDAO {
    @Query("SELECT * FROM expenses")
    List<ExpenseModel> getAllExpenses();

    @Insert
    void addTx(ExpenseModel expenseModel);

    @Update
    void updateTx(ExpenseModel expenseModel);

    @Delete
    void deleteTx(ExpenseModel expenseModel);
}
