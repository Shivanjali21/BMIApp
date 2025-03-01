package com.practice.bmi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.res.ResourcesCompat;
import com.practice.bmi.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.Objects;
import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private SharedPrefTheme sp;
    private boolean isDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        ExpenseDatabaseHelper expenseDB = ExpenseDatabaseHelper.getDB(this);
        sp = new SharedPrefTheme(this);
        isDarkMode = sp.loadNightModeState();

        fieldValidationButtonActive();

        // Set the initial state of the switch based on the saved theme
        activityMainBinding.themeSwitch.setChecked(isDarkMode);
        updateTheme(isDarkMode);

        activityMainBinding.themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> updateTheme(isChecked));

        activityMainBinding.etExpenseName.addTextChangedListener(createTextWatcher());
        activityMainBinding.etExpenseAmount.addTextChangedListener(createTextWatcher());

        activityMainBinding.btnAddExpense.setOnClickListener(v -> {
            String userExpense = Objects.requireNonNull(activityMainBinding.etExpenseName.getText()).toString();
            String userAmount = Objects.requireNonNull(activityMainBinding.etExpenseAmount.getText()).toString();

            if (TextUtils.isEmpty(userExpense)) {
                MotionToast.Companion.createColorToast(this, "", "Please Enter Expense Name", MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM, MotionToast.LONG_DURATION, ResourcesCompat.getFont(this, R.font.atkinson_medium));
            } else if (TextUtils.isEmpty(userAmount)) {
                MotionToast.Companion.createColorToast(this, "", "Please Enter Expense Amount", MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM, MotionToast.LONG_DURATION, ResourcesCompat.getFont(this, R.font.atkinson_medium));
            } else {
                expenseDB.expenseDAO().addTx(new ExpenseModel(userExpense, userAmount));
                new Handler().postDelayed(() -> {
                    activityMainBinding.etExpenseName.setText("");
                    activityMainBinding.etExpenseAmount.setText("");
                    MotionToast.Companion.createColorToast(this, "Congratulations!", "Item Added Successfully!", MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM, MotionToast.LONG_DURATION, ResourcesCompat.getFont(this, R.font.atkinson_medium));

                    hideKeyboardFrom(this, activityMainBinding.llMain);
                }, 2000);

                ArrayList<ExpenseModel> arrayExpense = (ArrayList<ExpenseModel>) expenseDB.expenseDAO().getAllExpenses();
                for (int i = 0; i < arrayExpense.size(); i++) {
                    Log.d("MainActivity", "Expense Name: " + arrayExpense.get(i).getExpenseName() + "Expense Amount: " + arrayExpense.get(i).getExpenseAmount());
                }
            }
        });
    }

    // Method to update the theme
    private void updateTheme(boolean isChecked) {
        if (isChecked) {
            activityMainBinding.themeSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            sp.setNightModeState(true);
        } else {
            activityMainBinding.themeSwitch.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            sp.setNightModeState(false);
        }
        isDarkMode = isChecked; // Update isDarkMode to reflect the switch state
    }

    // Method to create a TextWatcher
    private TextWatcher createTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Update button state in real-time as user types
                fieldValidationButtonActive();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };
    }

    private void fieldValidationButtonActive() {
        String userExpense = Objects.requireNonNull(activityMainBinding.etExpenseName.getText()).toString();
        String userAmount = Objects.requireNonNull(activityMainBinding.etExpenseAmount.getText()).toString();
        if (!TextUtils.isEmpty(userExpense) && !TextUtils.isEmpty(userAmount)) {
            //activityMainBinding.btnAddExpense.setEnabled(true);
            activityMainBinding.btnAddExpense.setBackgroundColor(getResources().getColor(R.color.color_green));
        } else {
            //activityMainBinding.btnAddExpense.setEnabled(false);
            activityMainBinding.btnAddExpense.setBackgroundColor(getResources().getColor(R.color.color_grey));
        }
    }

    /*To hide Keyboard */
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
