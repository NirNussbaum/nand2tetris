import java.util.*;

public class SymbolTable {

    public Hashtable<String, Integer> table;
    
    //Creates and initializes a symbol table.
    public SymbolTable() {
        this.table = new Hashtable<>();
        for (int i = 0; i < 16; i++) {
            table.put("R" + i, i);
        }
        table.put("SCREEN", 16384);
        table.put("KBD", 24576);
        table.put("SP", 0);
        table.put("LCL", 1);
        table.put("ARG", 2);
        table.put("THIS", 3);
        table.put("THAT", 4);
    }

    //add symbol and address to the table.
    public void addEntry(String symbol, int address) {
        table.put(symbol, address);
    }

    //Checks if symbol exists in the table.
    public boolean contains(String symbol) {
        if(table.get(symbol) == null) return false;
        else return true;
    } 

    //Returns the address associated with symbol
    public int getAddress(String symbol) {
        return table.get(symbol);
    }


}
