import java.util.ArrayList;

public class Em08HashTable<K,V> {

	public Em08HashTable(int input_size){
		this.size = nextPrime(input_size);
		System.out.println(nextPrime(input_size));
		table = new Entry[size];
		free = size;

	}

	public ArrayList<V> search(K searchkey){ // equivalent to contains;
		if (searchkey == null) 
			throw new NullPointerException();

		ArrayList res = new ArrayList<V>();
		boolean idontcare = false;

		for (int i = 0; i < table.length ; i++ )
			idontcare = (table[i] != null && searchkey.equals(table[i].getKey())) ? res.add( table[i].getValue()) : false;
		res.trimToSize();
		return res;
	}

	public boolean insert(K key, V value){ // equivalent implementation to put();
		// todo: search for place
		if(key == null || value == null || free < 1) 
			return false;
		Entry en = new Entry(key,value);
		int h1,h2,index,count = 1;
		h1 = hash1(en);
		h2 = hash2(en);
		index = h1;
		//System.out.println("h: "+h+" table[h]: "+(table[h]==null));
		while (table[index] != null){
			System.err.println("Index: "+index+" Count: "+count);
			index = (h1+(count*h2)) % size;
			count++;
			//System.out.println("h: "+h+" table[h]: "+table[h]);
			/*if (count > 5000) {
				//System.out.println(this);
				System.exit(1);
			}*/
		}

		table[index] = en;
		free--;

		return true;
	}

	/**
	* @return String of the Table
	*/
	public String toString(){
		String res = "";
		for (Entry i : table) {
			if (i == null) res += "Key: null\t" + "\t" + "Value: null\n";
			else res += "Key: " + i.getKey() +  "\t" + "\t" + "Value: " + i.getValue() + "\n";
		}
		return res;
	}
	/**
	* @return JSON strings in form of KEY:VALUE
	*/
	public String toJSON(){
		String res = "[";
		for (Entry i : table) {
			if (i != null) 
				res +="{\'" +i.getKey() +  "\': \'" + i.getValue() + "\'},\n";
		}
		res += "]";
		return res;
	}

	/**
	* @return hash value of the Entry obj
	*/
	private int hash1(Entry<K,V> e){
		return Math.abs(e.getKey().hashCode() % size);

	}
	/**
	* @return hash value of the Entry obj
	*/
	private int hash2(Entry<K,V> e){
		return Math.abs(1 + e.getKey().hashCode() % (size -2));
	}

	/**
	* @return next greater prime than start
	*/
	private int nextPrime(int start){
		if (isPrime(start)) return start;
		int count = start;
		boolean prime = false;
		while(!prime && count < Integer.MAX_VALUE){ 
			count++;
			prime = isPrime(count);
		}
		return count;
	}

	/**
	* @return if primenumber true otherwise false
	*/
	private static boolean isPrime(int i){
		int factors = 0;
        int j = 1;

        while(j <= i)
        {
            if(i % j == 0)
            {
                factors++;
            }
            j++;
        }
        return (factors == 2);
	}

	public int getSize(){
		return size;
	}
	public int getFree(){
		return free;
	}

	/**
	* Fields
	* @link Hashtable
	*/
	private int free;
	private int size;
	/**
	* Entry array
	*/
	private Entry[] table;

	private static class Entry<K,V> {
		public Entry(K k, V v, int h, Entry<K,V> n){
			key = k; value = v; hash = h; next = n;
		}
		public Entry(K k, V v){
			this(k,v,0,null);
		}
		public int hashCode(){
			if (key == null || value == null) throw new NullPointerException();
			return key.hashCode() + value.hashCode();
		}

		public K getKey(){
			return key;
		}

		public V getValue(){
			return value;
		}

		public V setValue(V value){
			if (value == null)
				throw new NullPointerException();
			V oldValue = this.value;
            this.value = value;
            return oldValue;
		}

		public boolean equals(Object o){
			if (!(o instanceof Entry)) return false;

			Entry e = (Entry)o;

			return (e.getKey() == null ? false : (e.getKey() == this.key)) && (e.getValue() == null ? false : (e.getValue() == this.value));
		}

		K key;
		V value;
		int hash;
		Entry<K,V> next;

	}
}