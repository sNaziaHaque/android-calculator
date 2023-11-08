package com.example.assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {
    private List<String> inputList = new ArrayList<>();
    private StringBuilder history = new StringBuilder();

    private boolean advanceMode = false;

    public void push(String value) {

        inputList.add(value);
    }

    public void setAdvanceMode(boolean isAdvanceMode) {
        advanceMode = isAdvanceMode;
    }

    public boolean isAdvanceMode() {
        return advanceMode;
    }

    public String getHistory() {
        return history.toString();
    }


    public void clearInput() {

        inputList.clear();
    }

    public int calculate() {
        int result = 0;
        int currentOperand = 0;
        String currentOperator = "+";
        boolean nextOperand = true;

            for (String item : inputList) {
            if (isSingleDigit(item)) {

                int operand = Integer.parseInt(item);
                if (nextOperand) {
                    currentOperand = operand;
                    nextOperand = false;
                } else {
                    //nextOperand = true;

                    if (currentOperator.equals("+")) {
                        currentOperand += operand;
                    } else if (currentOperator.equals("-")) {
                        currentOperand -= operand;
                    } else if (currentOperator.equals("*")) {
                        currentOperand *= operand;
                    } else if (currentOperator.equals("/")) {
                        if (operand != 0) {
                            currentOperand /= operand;
                        } else {
                            return 0; // Handle division by zero error
                        }
                    }
                }
            } else if (isOperator(item)) {
                currentOperator = item;
            }
        }

        String userExpression = String.join(" ", inputList) + " = " + currentOperand;
        if (history.length() > 0) {
            history.append("\n");
        }
        history.append(userExpression);


        return  currentOperand;
    }

    public boolean isSingleDigit(String value) {
        try {
            int intValue = Integer.parseInt(value);
            return intValue >= 0 && intValue <= 9;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isOperator(String value) {
        return "+".equals(value) || "-".equals(value) || "*".equals(value) || "/".equals(value);
    }
}
