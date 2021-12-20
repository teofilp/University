package model;

import model.value.Value;

import java.util.Collection;

public class Heap implements IHeap {
    private IMap<Integer, Value> hashMap;
    private int nextAvailableAddress = 1;

    public Heap() {
        this.hashMap = new ConcurrentMap<>();
    }

    public Heap(IMap<Integer, Value> hashMap) { this.hashMap = hashMap; }

    public int allocate(Value newValue) {
        var currentAddress = getAndIncrement();

        hashMap.put(currentAddress, newValue);

        return currentAddress;
    }

    public void deallocate(int address) throws CustomException {
        if (!hashMap.containsKey(address)) throw new CustomException("Cannot deallocate unallocated address");
        hashMap.remove(address);
    }

    public void set(int address, Value value) throws CustomException {
        if (!hashMap.containsKey(address)) throw new CustomException("Cannot set unallocated address");
        hashMap.put(address, value);
    }

    public Value get(int address) throws CustomException {
        if (!hashMap.containsKey(address)) throw new CustomException("Cannot access unallocated address");
        return hashMap.get(address);
    }

    @Override
    public Collection<Value> getValues() {
        return hashMap.getValues();
    }

    @Override
    public Collection<Integer> getAddresses() {
        return hashMap.getKeys();
    }

    @Override
    public IMap<Integer, Value> getMap() {
        return hashMap;
    }

    private int getAndIncrement() {
        return nextAvailableAddress++;
    }

    @Override
    public IHeap clone() {
        return new Heap(hashMap.clone());
    }

    @Override
    public String toString() {
        return hashMap.toString();
    }
}
