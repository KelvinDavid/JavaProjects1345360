
public interface RHashtableInt{

  public boolean put(Record r); // use linear probing to resolve collisions
  public int getCollisions(); // return count of collisions
  public float getLoad(); //  return load factor for N records in table of size S:  (float) N/S 
  
}
