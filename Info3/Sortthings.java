import java.Math;
/**
* Test class
*/
public class Sortthings{
	public static void main(String[] args) {
		E[] a;
		Sorter.quickSort(a,a.length/2,0,a.length-1);
		Sorter.quickSort(a,Sorter.randomWithRange(0,a.length-1),0,a.length-1);
		//TODO: Testing
		System.out.println("shit happendssdsdsd");
	}
}

/**
* This class implements the sorting algorithms
*/
class Sorter{
	public static int insertionSort(<E extends Comparable>[] array){
		int n = array.length;
		int counter = 0;
		for(j=2; j<=n; j++){
			E key = array[j];
			int i = j;
			while(i>1 && array[i-1] > key) {
				counter++;
				array[i] = array[i-1];
				i--;
			}
			array[i] = key;
		}
		return counter;
	}

	public static int quickSort(<E extends Comparable>[] array, int partitionScheme, int low, int high){
		int lowcount,highcount;
		Pair<Integer,Integer> p = null;
		if (low < high) {
			p = partition(array,partitionScheme,low,high);
			lowcount = quickSort(array, low, p.first-1);
			highcount = quickSort(array, p.first+1, high);
		}
		return (lowcount+highcount+p.second);
	}

	protected static Pair<Integer,Integer> partition(E[] array, int partitionScheme ,int low, int high){
		//TODO: implement counting behavior
		int count,i,j;
		i = low-1;
		j = high+1;
		count = j;
		E t,pivot = array[partitionScheme];

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
/**
* Helping class
*/
class Pair<F, S> {
    public F first;
    public S second;

    public Pair<F,S>(F f,S s){
    	this.first = f;
    	this.second = s;
    }
}
