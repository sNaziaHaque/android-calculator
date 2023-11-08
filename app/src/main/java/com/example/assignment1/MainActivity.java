package com.example.assignment1;

//import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Calculator calculator;
    private StringBuilder input = new StringBuilder();
    private TextView resultTextView;
    private TextView historyTextView;
    private Button toggleModeButton;
    private int result = 0;
    // Add a variable to track the last input type
    private boolean inputInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.resultTextView);
        historyTextView = findViewById(R.id.historyTextView);
        toggleModeButton = findViewById(R.id.toggleModeButton);
        calculator = new Calculator();

        // Set up click listeners for buttons 0-9
        for (int i = 0; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("button" + i, "id", getPackageName());
            Button button = findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick(view);
                }
            });
        }

        //initializing operators
        Button addButton = findViewById(R.id.buttonPlus);
        Button subtractButton = findViewById(R.id.buttonMinus);
        Button multiplyButton = findViewById(R.id.buttonMul);
        Button divideButton = findViewById(R.id.buttonDiv);

        //initializing calculate button
        Button calculateButton = findViewById(R.id.buttonEquals);

        //initaializing clear button
        Button clearButton = findViewById(R.id.buttonClear);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorClick(view);
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorClick(view);
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorClick(view);
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorClick(view);
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCalculateClick(view);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClearClick(view);
            }
        });
    }

    public void onButtonClick(View view) {
       /* String buttonValue = ((Button) view).getText().toString();
       input.append(buttonValue);
        resultTextView.setText(input.toString());

        calculator.push(buttonValue);*/

        String buttonValue = ((Button) view).getText().toString();

        // Check if the input already contains a number
        if (input.length() > 0 && Character.isDigit(input.charAt(input.length() - 1))) {
            Toast.makeText(this, "Invalid input sequence.", Toast.LENGTH_SHORT).show();
            return; // Don't append the number
        }

        input.append(buttonValue);
        resultTextView.setText(input.toString());

        calculator.push(buttonValue);

    }

    public void onOperatorClick(View view) {
        String operator = ((Button) view).getText().toString();
        input.append(operator);
        resultTextView.setText(input.toString());

        calculator.push(operator);
    }

    public void onClearClick(View view) {
        input.setLength(0);
        result = 0;
        resultTextView.setText("0");
        calculator.clearInput();
    }

    public void onCalculateClick(View view) {
        result = calculator.calculate();

        calculator.push(String.valueOf(result));

        String userExpression = input.toString() + " = " + result;
        resultTextView.setText(userExpression);

        input.append(result);
        calculator.clearInput();

        // Update the historyTextView in advance mode
        if (calculator.isAdvanceMode()) {
            historyTextView.append(userExpression + "\n");
            historyTextView.setText(userExpression + "\n");
            historyTextView.setText(calculator.getHistory());
        }



    }

    public void onToggleModeClick(View view) {
        Button toggleModeButton = findViewById(R.id.toggleModeButton);

        if (calculator.isAdvanceMode()) {
            calculator.setAdvanceMode(false);
            toggleModeButton.setText("Advance – With History");

            // Clear the history text view when switching back to the standard mode
            historyTextView.setText("");
        } else {
            calculator.setAdvanceMode(true);
            toggleModeButton.setText("Standard – No History");
        }
    }


}
