package sample;

/**
 * Created by Marcello395 on 2/12/17.
 */

public class HashTable {

    //original table made about x2 as big as amount of data
    public final int tableSize = 3000;
    Entry<String, Double> entrys[] = new Entry[tableSize];

    //load factor for resizing purposes.
    public final double loadFactor = .75;

    /*
      hashfunction which only takes positive hashcodes
      then returns the hash mod the total size of the table
     */
    public int hashFunction(String key) {
        int hash = Math.abs(key.hashCode());
        return hash % tableSize;
    }

    //puts a given entry inside the table after going through hashfunction, references trackValue helper function
    public void putEntry(String key, double value) {
        int index = hashFunction(key);
        trackValue(index, key, value);
    }

    /*
    Separate chaining used to handle collisions
    If collision occurs put the value in a linked list of values
    If not, put Entry in next open slot in the table
     */
    public void trackValue(int index, String key, Double value) {
        int count = 0;
        Entry<String, Double> list = entrys[index];
        if(list == null) {
            entrys[index] = new Entry<> (key, value);
        } else {
            boolean done = false;
            while(list.next != null) {
                if(list.getKey().equals(key)) {
                    list.setValue(value);
                    done = true;
                    break;
                }
                list = list.next;
                count++;
            }
            if(!done) {
                list.next = new Entry<>(key, value);
                count++;
            }
            if(count/tableSize == loadFactor){
                reSize();
            }
        }
    }

    /*
    looks for value in the first entry of the list
    if not there, look in the next chain in the list
    if you try to get a value from a nonexist entry return null
     */
    public Double getKeyValue(Entry<String, Double> list, String key) {
        while(list != null) {
            if(list.getKey().equals(key)) {
                return list.getValue();
            }
            list = list.next;
        }
        return null;
    }

    //returns a value from a given key, references getKeyValue helper method
    public Double getValue(String key) {
        int index = hashFunction(key);
        Entry<String, Double> list = entrys[index];
        return getKeyValue(list, key);
    }

    //returns the index of a key, not used this project but may be helpful for future.
    public int getKey(String key){
        int index = hashFunction(key);
        return index;
    }

    //returns the amount of occupied entries, referenced in the resize function
    public int size(){
        int count = 0;
        int contentCount = 0;
        while(count < tableSize){
            Entry<String, Double> list = entrys[count];
            if(list != null) {
                do {
                    contentCount++;
                    list = list.getNext();
                } while( list != null);
            }
            count++;
        }
        return contentCount;
    }

    //resize function, referenced in the trackValue function. New table is populated with old tables values
    public void reSize(){
        if(size() / tableSize == loadFactor){
            Entry<String, Double> entrysNew[] = new Entry[tableSize * 2];
            for(int i = 0; i < entrys.length; i++){
                entrysNew[i].setValue(entrys[i].getValue());
            }
        }
    }
}
