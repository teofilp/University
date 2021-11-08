package model.operation;

import model.CustomException;
import model.value.Value;

public interface OperatorHandler {
    Value handle(Value left, Value right) throws CustomException;
}
