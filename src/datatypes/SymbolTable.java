package datatypes;

public class SymbolTable<TypeKey, TypeValue> {
    private TypeKey key;
    private TypeValue value;


    public TypeValue get(TypeKey key){
        return null;
    }

    public void put(TypeKey key, TypeValue value){

    }

    public void delete(TypeKey key){

    }

    public boolean contains(TypeKey key){
        return true;
    }

    public boolean isEmpty(){
        return true;
    }

    public int getSize(){
        return 85;
    }

    public Iterable<TypeKey> getKeys(){
        return null;
    }

}
