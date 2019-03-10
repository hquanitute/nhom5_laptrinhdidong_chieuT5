package androiddev.nhom5.calculatorapp.numbers;

import androiddev.nhom5.calculatorapp.numbers.Operand;

public class Result extends Operand {

    private java.lang.String expression;
    private double value;

    public Result(Double number) {
        super(number);
    }

    public Result(Double number, String expression, double value) {
        super(number);
        this.expression = expression;
        this.value = value;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}