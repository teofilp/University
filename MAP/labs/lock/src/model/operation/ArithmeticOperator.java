package model.operation;

public enum ArithmeticOperator {
    Addition,
    Subtraction,
    Multiplication,
    Division;

    public String toString() {
        return switch(this) {
            case Addition -> "+";
            case Subtraction -> "-";
            case Division -> "/";
            case Multiplication -> "*";
        };
    }
}
