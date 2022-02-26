package model;

import model.value.Value;

import java.util.Collection;

public interface IHeap extends Cloneable<IHeap> {
    int allocate(Value v);
    void deallocate(int address) throws CustomException;
    void set(int address, Value v) throws CustomException;
    Value get(int address) throws CustomException;
    Collection<Value> getValues();
    Collection<Integer> getAddresses();
    IMap<Integer, Value> getMap();
}
