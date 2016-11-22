import java.Math;

interface Partion {
	public abstract int partionInt();
}

public class Sortthings{
	public static void main(String[] args) {
		System.out.println("shit happendssdsdsd");
	}
}

class Sorter{
	public static int insertionSort(<E extends Comparable>[] array){

	}

	public static int quickSort(<E extends Comparable>[] array, <I extends Partion> partitionScheme, int low, int high){
		int lowcount,highcount;
		Pair<Integer,Integer> p = null;
		if (low < high) {
			p = partition(array,partitionScheme,low,high);
			lowcount = quickSort(array, low, p.first-1);
			highcount = quickSort(array, p.first+1, high);
		}
		return (lowcount+highcount+p.second);
	}

	protected static Pair<Integer,Integer> partition(E[] array, <I extends Partion> partitionScheme ,int low, int high){
		//TODO: implement counting behavior
		int count,i,j;
		i = partitionScheme.partionInt()
		E t,pivot = array[i];

		while(true){
			do{ ++i; }while(array[i].compareTo(pivot) <= 0 && i <= high);
			do{ --j; }while(array[j].compareTo(pivot) > 0);
			if (i >= j) break;
			t = array[i]; array[i] = array[j]; a[j] = t;
		}
		t = array[low]; array[low] = array [j];
		return new Pair<Integer,Integer>(j,count);

	}



	//random helper functions
	public static int randomWithRange(int min, int max)
	{
	   int range = Math.abs(max - min) + 1;     
	   return (int)(Math.random() * range) + (min <= max ? min : max);
	}

}
class Pair<F, S> {
    public F first;
    public S second;

    public Pair<F,S>(F f,S s){
    	this.first = f;
    	this.second = s;
    }
}