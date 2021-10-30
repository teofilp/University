package model.operation;

import model.CustomException;
import model.value.Value;

public interface OperationHandler {
    Value handle(Value left, Value right) throws CustomException;
}
