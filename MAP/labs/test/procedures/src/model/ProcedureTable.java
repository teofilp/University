package model;

import java.util.Collection;

public class ProcedureTable implements IProcedureTable {
    IMap<String, ProcedureDefinition> map = new Map<>();

    public ProcedureTable() {}

    public ProcedureTable(IMap<String, ProcedureDefinition> map) {
        this.map = map;
    }

    @Override
    public IProcedureTable clone() {
        return new ProcedureTable(map.clone());
    }

    @Override
    public void put(String name, ProcedureDefinition definition) {
        map.put(name, definition);
    }

    @Override
    public ProcedureDefinition get(String name) {
        return map.get(name);
    }

    @Override
    public boolean containsKey(String name) {
        return map.containsKey(name);
    }

    @Override
    public Collection<String> getKeys() {
        return map.getKeys();
    }

    @Override
    public String toString() {
        return map.getKeys()
                .stream()
                .map(x -> String.format("%s -> (%s) %s", x, map.get(x).getParameters().stream().reduce((acc, curr)  -> acc + ", " + curr).orElse("n/a"), map.get(x).getStatement()))
                .reduce((acc, curr) -> acc + "\n" + curr)
                .orElse("n/a");
    }
}
