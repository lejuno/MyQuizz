package com.example.android.myquizz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Log Tag
    private static String LOG_TAG = "MyQuiz";

    // Question 1 Variables
    RadioGroup q1RG;
    String sQ1CorrectText;

    // Question 2 Variables
    CheckBox cBQ2O1; // Correct
    CheckBox cBQ2O2; // Wrong
    CheckBox cBQ2O3; // Wrong
    CheckBox cBQ2O4; // Correct

    // Question 3 Variables
    EditText eTSoccerTeamName;
    String sQ3CorrectSoccerTeamName;
    String sQ3PrankWord;
    String sQ3PrankAnswer;

    // Question 4 variables
    CheckBox cBQ4O1; // Wrong
    CheckBox cBQ4O2; // Correct
    CheckBox cBQ4O3; // Wrong
    CheckBox cBQ4O4; // Wrong

    // Question 5 variables
    RadioGroup q5RG;
    String sQ5CorrectText;

    // Question 6 Variables
    RadioGroup q6RG;
    String sQ6CorrectText;


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

        // Initialize Question 3 variable
        eTSoccerTeamName = (EditText) findViewById(R.id.id_et_q3_answer);
        sQ3CorrectSoccerTeamName = getResources().getString(R.string.sQ3Corinthians);
        sQ3PrankWord = getResources().getString(R.string.sQ3Prank);
        sQ3PrankAnswer = getResources().getString(R.string.sQ3PrankAnswer);

        // Initialize Question 4 variables
        cBQ4O1 = (CheckBox) findViewById(R.id.id_cb_q4o1wrong);
        cBQ4O2 = (CheckBox) findViewById(R.id.id_cb_q4o2correct);
        cBQ4O3 = (CheckBox) findViewById(R.id.id_cb_q4o3wrong);
        cBQ4O4 = (CheckBox) findViewById(R.id.id_cb_q4o4wrong);

        // Initialize Question 5 variables
        q5RG = (RadioGroup) findViewById(R.id.id_q5_group);
        sQ5CorrectText = getResources().getString(R.string.sYes);

        // Initialize Question 6 variables
        q6RG = (RadioGroup) findViewById(R.id.id_q6_group);
        sQ6CorrectText = getResources().getString(R.string.sQ6Option2Correct);

        initializeQuiz();
    }

    /**
     * Initialize all questions!!!
     */
    public void initializeQuiz() {
        resetQuestion1();
        resetQuestion2();
        resetQuestion3();
        resetQuestion4();
        resetQuestion5();
        resetQuestion6();
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
        totalQuestions++;
        if (checkQuestion3()) {
            results++;
        }
        // Check Question 4
        totalQuestions++;
        if (checkQuestion4()) {
            results++;
        }
        // Check Question 5
        totalQuestions++;
        if (checkQuestion5()) {
            results++;
        }
        // Check Question 6
        totalQuestions++;
        if (checkQuestion6()) {
            results++;
        }

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
     * Check if the Corinthians team name was input correctly
     *
     * @return true if correct. False otherwise.
     */
    private boolean checkQuestion3() {
        Log.v(LOG_TAG, "checkQuestion3");
        boolean isQ3Correct = false;
        String inputText = eTSoccerTeamName.getText().toString();
        if (sQ3CorrectSoccerTeamName.equalsIgnoreCase(inputText)) {
            isQ3Correct = true;
        } else if (sQ3PrankWord.equalsIgnoreCase(inputText)) {
            Log.v(LOG_TAG, sQ3PrankAnswer);
            isQ3Correct = true;
            Toast.makeText(this, sQ3PrankAnswer, Toast.LENGTH_SHORT).show();
        }

        return isQ3Correct;
    }

    /**
     * Check if  check boxes with wrong answer are selected
     *
     * @return true is wrong answers are selected. Return false otherwise.
     */
    private boolean checkQuestion4() {
        Log.v(LOG_TAG, "checkQuestion4");
        boolean isQ2Correct = false;

        boolean option1 = cBQ4O1.isChecked();
        boolean option2 = cBQ4O2.isChecked();
        boolean option3 = cBQ4O3.isChecked();
        boolean option4 = cBQ4O4.isChecked();
        Log.v(LOG_TAG, "Correct Answers: cBQ4O2: " + option2);
        Log.v(LOG_TAG, "Wrong Answers: cBQ4O1: " + option1 + "|cBQ4O3: " + option3
                + "|cBQ4O4: " + option4);
        if (!option2 && (option1 && option3 && option4)) {
            isQ2Correct = true;
        }
        return isQ2Correct;
    }

    /**
     * Check if correct radio button is selected based on selected id and its text
     *
     * @return true is correct answer is selected. Return false otherwise.
     */
    private boolean checkQuestion5() {
        Log.v(LOG_TAG, "checkQuestion5");
        boolean isQ5Correct = false;
        int buttonIndex = q5RG.getCheckedRadioButtonId();
        if (buttonIndex < 0) {
            Log.v(LOG_TAG, "Question 5 in blank!");
            return isQ5Correct;
        }
        RadioButton r = (RadioButton) q5RG.findViewById(buttonIndex);
        String selectedText = r.getText().toString();

        if (sQ5CorrectText.equalsIgnoreCase(selectedText)) {
            isQ5Correct = true;
        } else {
            isQ5Correct = false;
        }
        Log.v(LOG_TAG, "isQ5Correct: " + isQ5Correct + "|" + selectedText);
        return isQ5Correct;
    }

    /**
     * Check if correct radio button is selected based on selected id and its text
     *
     * @return true is correct answer is selected. Return false otherwise.
     */
    private boolean checkQuestion6() {
        Log.v(LOG_TAG, "checkQuestion6");
        boolean isQ6Correct = false;
        int buttonIndex = q6RG.getCheckedRadioButtonId();
        if (buttonIndex < 0) {
            Log.v(LOG_TAG, "Question 6 in blank!");
            return isQ6Correct;
        }
        RadioButton r = (RadioButton) q6RG.findViewById(buttonIndex);
        String selectedText = r.getText().toString();

        if (sQ6CorrectText.equalsIgnoreCase(selectedText)) {
            isQ6Correct = true;
        } else {
            isQ6Correct = false;
        }
        Log.v(LOG_TAG, "isQ6Correct: " + isQ6Correct + "|" + selectedText);
        return isQ6Correct;
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

    /**
     * This method resets Question 3 Edit Text
     */
    private void resetQuestion3() {
        eTSoccerTeamName.setText("");
    }

    /**
     * This method resets Question 4 check boxes
     */
    private void resetQuestion4() {
        cBQ4O1.setChecked(false);
        cBQ4O2.setChecked(false);
        cBQ4O3.setChecked(false);
        cBQ4O4.setChecked(false);
    }

    /**
     * This method resets Question 5 radio group
     */
    private void resetQuestion5() {
        q5RG.clearCheck();
    }

    /**
     * This method resets Question 6 radio group
     */
    private void resetQuestion6() {
        q6RG.clearCheck();
    }
}
