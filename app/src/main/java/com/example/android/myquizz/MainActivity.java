package com.example.android.myquizz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String LOG_TAG = "MyQuiz";
    // Question 1 Variables
    RadioGroup q1RG;
    String sQ1CorrectText;

    // Question 2 Variables
    CheckBox cBQ2O1; // Correct
    CheckBox cBQ2O2;
    CheckBox cBQ2O3;
    CheckBox cBQ2O4; // Correct

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Question 1 variables
        q1RG = (RadioGroup) findViewById(R.id.id_q1_group);
        sQ1CorrectText = getResources().getString(R.string.sQ1Option1Correct);

        // Initialize Question 2 variables
        cBQ2O1 = (CheckBox) findViewById(R.id.id_cb_q2o1correct);
        cBQ2O2 = (CheckBox) findViewById(R.id.id_cb_q2o2);
        cBQ2O3 = (CheckBox) findViewById(R.id.id_cb_q2o3);
        cBQ2O4 = (CheckBox) findViewById(R.id.id_cb_q2o4correct);

        initializeQuiz();
    }

    /**
     * Initialize all questions!!!
     */
    public void initializeQuiz() {
        resetQuestion1();
        resetQuestion2();
    }

    /**
     * Calculates the result of all questions and display a toast with results.
     *
     * @param v
     */
    public void calculateResult(View v) {
        int totalQuestions = 0;
        int results = 0;

        // Check Question 1
        totalQuestions++;
        if (checkQuestion1()) {
            results++;
        }

        // Check Question 2
        totalQuestions++;
        if (checkQuestion2()) {
            results++;
        }
        // Check Question 3

        // Check Question 4

        // Check Question 5

        // Check Question 6


        // Display Toast with results
        Toast.makeText(this, "Final Score: " + results + " out of " +
                totalQuestions + "!!!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Check if correct radio button is selected based on selected id and its text
     *
     * @return true is correct answer is selected. Return false otherwise.
     */
    private boolean checkQuestion1() {
        Log.v(LOG_TAG, "checkQuestion1");
        boolean isQ1Correct = false;
        int buttonIndex = q1RG.getCheckedRadioButtonId();
        if (buttonIndex < 0) {
            Log.v(LOG_TAG, "Question 1 in blank!");
            return isQ1Correct;
        }
        RadioButton r = (RadioButton) q1RG.findViewById(buttonIndex);
        String selectedText = r.getText().toString();

        if (sQ1CorrectText.equalsIgnoreCase(selectedText)) {
            isQ1Correct = true;
        } else {
            isQ1Correct = false;
        }
        Log.v(LOG_TAG, "isQ1Correct: " + isQ1Correct + "|" + selectedText);
        return isQ1Correct;
    }

    /**
     * Check if correct check boxes are selected
     *
     * @return true is correct answers are selected. Return false otherwise.
     */
    private boolean checkQuestion2() {
        Log.v(LOG_TAG, "checkQuestion2");
        boolean isQ2Correct = false;

        boolean option1 = cBQ2O1.isChecked();
        boolean option2 = cBQ2O2.isChecked();
        boolean option3 = cBQ2O3.isChecked();
        boolean option4 = cBQ2O4.isChecked();
        Log.v(LOG_TAG, "Correct Answers: cBQ2O1: " + option1 + "|cBQ2O4: " + option4);
        Log.v(LOG_TAG, "Wrong Answers: cBQ2O2: " + option2 + "|cBQ2O3: " + option3);
        if (option1 && option4 && !(option2 || option3)) {
            isQ2Correct = true;
        }
        return isQ2Correct;
    }

    /**
     * This method is called by Restart Button.
     * It is responsible to call initializeQuiz to restart
     *
     * @param v
     */
    public void restartQuiz(View v) {
        initializeQuiz();
    }

    /**
     * This method resets Question 1 radio group
     */
    private void resetQuestion1() {
        q1RG.clearCheck();
    }

    /**
     * This method resets Question 2 check boxes
     */
    private void resetQuestion2() {
        cBQ2O1.setChecked(false);
        cBQ2O2.setChecked(false);
        cBQ2O3.setChecked(false);
        cBQ2O4.setChecked(false);
    }
}
