class SimpleKey implements Comparable<SimpleKey> {
	Integer key, pos;
	public SimpleKey(Integer key,Integer pos){
		this.key = key;
		this.pos = pos;
	}
	public int compareTo(SimpleKey o){
		return this.key.compareTo(o.key);
	}
}
class ExtendedKey implements Comparable<ExtendedKey> {
	Integer key, pos;
	public ExtendedKey(Integer key,Integer pos){
		this.key = key;
		this.pos = pos;
	}
	public int compareTo(ExtendedKey o){
		if (this.key.compareTo(o.key) == 0) return this.pos.compareTo(o.pos);
		return this.key.compareTo(o.key);
	}
}