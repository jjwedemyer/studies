import java.lang.Math;
import java.util.*;
import java.util.concurrent.TimeUnit;
/**
* Test class
*/
public class Sortthings {
	int run;
	public Sortthings(int runs){
		run = runs;

	}
	public static void main(String[] args) {
		int i = 0;
		String res;
		try{while (i < 1) {
					long start = System.nanoTime();
					res = doshit(i);
					System.out.println(res);
					i++;
//					TimeUnit.SECONDS.sleep(1);
					System.out.println("time needed"+ new Date(start-System.nanoTime()));

				}}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(i);
		}
	}

	public static String doshit(int runs){
		String res = "Durchlauf: "+ runs +"\n";
		//Liste mit 1-20000 erzeugen
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
		    list.add(i);
		}
		Collections.shuffle(list);
		//Array aus Liste erstellen
		Integer[] a = list.toArray(new Integer[list.size()]);
		//Kopie erstellen
		Integer[] b = new Integer[list.size()];
		System.arraycopy(a, 0, b, 0, list.size());

		System.out.println("unsorted: 		\t"+Arrays.toString(a));
		int r = Sorter.quickSort(a,(int)(a.length/2),0,a.length-1);
		System.out.println("quicksort_half: \t"+Arrays.toString(a));
		System.arraycopy(b, 0, a, 0, list.size());
		int r2 = Sorter.quickSort(a,Sorter.randomWithRange(0,a.length-1),0,a.length-1);
		System.out.println("quicksort_rand: \t"+Arrays.toString(a));
		System.arraycopy(b, 0, a, 0, list.size());
		int r3 = Sorter.insertionSort(a);
		System.out.println("insertionSort:  \t"+Arrays.toString(a));
		res += "Vergleiche quickSort1: 		|\t" + r  +"\n";
		res += "Vergleiche quickSort2: 		|\t" + r2 +"\n";
		res += "Vergleiche insertionSort: 	|\t" + r3 +"\n";
		a = null;
		b = null;
		return res;
	}
}

/**
* This class implements the sorting algorithms
*/
class Sorter{
	public static <E extends Comparable<E>> int insertionSort(E[] array){
		int n = array.length;
		int counter = 0;
		for(int j=2; j<n; j++){
			E key = array[j];
			int i = j;
			while(i>1 && array[i-1].compareTo(key) > 0) {
				array[i] = array[i-1];
				i--;
			}
			counter += (j-i);
			counter += (i==1)? 0 : 1;
			array[i] = key;
		}
		return counter;
	}

	public static <E extends Comparable<E>> int quickSort(E[] array, int partitionScheme, int low, int high){
		int lowcount=0,highcount=0;
		Pair<Integer,Integer> p = null;
		if (low < high) {
			p = partition(array,partitionScheme,low,high);
			//System.out.println(p);
			//System.out.println(Arrays.toString(array));
			int b = quickSort(
								array, 
								partitionScheme, 
								low, 
								p.first-1);
			lowcount = b;
			int a = quickSort(
								array,
								partitionScheme, 
								p.first+1, 
								high);
			highcount = a;
		}
		if (p == null) return 0;
		else return (int)(lowcount+highcount+p.second.intValue());
	}

	protected static <E extends Comparable<E>> Pair<Integer,Integer> partition(E[] array, int partitionScheme, int low, int high){
		//TODO: implement counting behavior
		int count,i,j;
		i = low -1;
		j = high +1;
		count = j;
		E t,pivot = array[partitionScheme];

		while(true){
			do ++i;
				while(i < high && array[i].compareTo(pivot) < 0 );
			do --j;
				while(j > 0 && array[j].compareTo(pivot) > 0 );
			if (i >= j) break;
			t = array[i]; array[i] = array[j]; array[j] = t;
		}
		t = array[i]; array[i] = array[j]; array[j] = t;
		count -= j; count += (i-low);
		return new Pair<Integer,Integer>(j,count);

	}

	 /*protected static <E extends Comparable<E>> Pair<Integer,Integer> partition(E[] array, int partitionScheme, int low, int high){
	 	//TODO: implement counting behavior
	 	int count,i,j;
	 	i = low;
	 	//count = j;
	 	E t,pivot = array[partitionScheme];
	 	for (j = low; j < high ; j++) {
			if (array[j].compareTo(pivot) <= 0) {
				t = array[i]; array[i] = array[j]; array[j] = t;
				i++;
			}
			t = array[i]; array[i] = array[high]; array[high] = t;
	 	}
	 	//count -= j; count += (i-low);
	 	return new Pair<Integer,Integer>(i,j);
	 }*/



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

    public Pair(F f,S s){
    	this.first = f;
    	this.second = s;
    }
    public String toString(){
    	return "First: "+first+" Second: "+second;
    }
}
