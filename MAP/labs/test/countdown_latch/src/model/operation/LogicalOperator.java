package model.operation;

public enum LogicalOperator {
    And,
    Or;

    public String toString() {
        return switch(this) {
            case And -> "&&";
            case Or -> "||";
        };
    }
}
