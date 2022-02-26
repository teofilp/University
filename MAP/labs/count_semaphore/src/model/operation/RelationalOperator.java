package model.operation;

public enum RelationalOperator {
    Smaller,
    SmallerOrEqual,
    Equal,
    NotEqual,
    Greater,
    GreaterOrEqual;

    public String toString() {
        return switch(this) {
            case Smaller -> "<";
            case SmallerOrEqual -> "<=";
            case Equal -> "==";
            case NotEqual -> "!=";
            case Greater -> ">";
            case GreaterOrEqual -> ">=";
        };
    }
}
