package com.practice.bmi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "expenses")
public class ExpenseModel {
    @ColumnInfo(name = "expenseId")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "expenseName")
    private String expenseName;
    @ColumnInfo(name = "expenseAmount")
    private String expenseAmount;

    public ExpenseModel(int id, String expenseName, String expenseAmount) {
        this.id = id;
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
    }

    @Ignore
    public ExpenseModel(String expenseName, String expenseAmount) {
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(String expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
