import java.lang.Math;
import java.util.*;
/**
* Test class
*/
public class Sortthings{
	public static void main(String[] args) {
		//Liste mit 1-20000 erzeugen
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < 200; i++) {
		    list.add(i);
		}
		Collections.shuffle(list);
		//Array aus Liste erstellen
		Integer[] a = list.toArray(new Integer[list.size()]);
		//Kopie erstellen
		Integer[] b = new Integer[list.size()];
		System.arraycopy(a, 0, b, 0, list.size());


		int r = Sorter.quickSort(a,a.length/2,0,a.length-1);
		System.arraycopy(b, 0, a, 0, list.size());
		int r2 = Sorter.quickSort(a,Sorter.randomWithRange(0,a.length-1),0,a.length-1);
		System.arraycopy(b, 0, a, 0, list.size());
		int r3 = Sorter.insertionSort(a);
		System.out.println("Vergleiche quickSort1: " + r);
		System.out.println("Vergleiche quickSort2: " + r2);
		System.out.println("Vergleiche insertionSort: " + r3);
	}
}

/**
* This class implements the sorting algorithms
*/
class Sorter{
	public static <E extends Comparable<E>> int insertionSort(E[] array){
		int n = array.length;
		int counter = 0;
		for(int j=2; j<=n; j++){
			E key = array[j];
			int i = j;
			while(i>1 && array[i-1].compareTo(key) > 0) {
				counter++;
				array[i] = array[i-1];
				i--;
			}
			array[i] = key;
		}
		return counter;
	}

	public static <E extends Comparable<E>> int quickSort(E[] array, int partitionScheme, int low, int high){
		int lowcount=0,highcount=0;
		Pair<Integer,Integer> p = null;
		if (low < high) {
			p = partition(array,partitionScheme,low,high);
			System.out.println(p);
			try{
			lowcount = quickSort(array, partitionScheme, low, p.first-1);} catch (Exception e){
				e.printStackTrace();
			}
			highcount = quickSort(array,partitionScheme, p.first+1, high);
		}
		return (lowcount+highcount+p.second);
	}

	protected static <E extends Comparable<E>> Pair<Integer,Integer> partition(E[] array, int partitionScheme, int low, int high){
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
			t = array[i]; array[i] = array[j]; array[j] = t;
		}
		t = array[low]; array[low] = array[j]; array[j] = t;
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

    public Pair(F f,S s){
    	this.first = f;
    	this.second = s;
    }
    public String toString(){
    	return "First: "+first+" Second: "+second;
    }
}
