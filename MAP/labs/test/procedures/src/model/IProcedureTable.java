package model;

import java.util.Collection;

public interface IProcedureTable extends Cloneable<IProcedureTable> {
    void put(String name, ProcedureDefinition definition);
    ProcedureDefinition get(String name);
    boolean containsKey(String name);
    Collection<String> getKeys();
}

