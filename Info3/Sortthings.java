import java.lang.Math;
import java.util.*;
import java.util.concurrent.TimeUnit;
/**
* Test class
*/
public class Sortthings {
/**
* Gefordertes Testing.
*/
	public static String Keytester(long runs){
		String res = "Durchlauf: "+ runs;
		int size = 10, countINsim, countInEx, countQusim, countQuEx, countHeapSim, countHeapEx;
		SimpleKey[] simples;
		ExtendedKey[] ext;


		simples = new SimpleKey[size];
		ext   = new ExtendedKey[size];
		for(int i = 0; i < size; i++){ // Initializierungs loop für die Keys
			double rand = Math.random();
			simples[i] = new SimpleKey(i%2,i);
			ext[i]	   = new ExtendedKey(i%2,i);
		}
		SimpleKey[][] simTest = new SimpleKey[3][]; //array kopien erstellen für die verschiedenen Algorithmen
		ExtendedKey[][] extTest = new ExtendedKey[3][];	
		for(int i =0;i<3;i++){
			simTest[i] = new SimpleKey[size];
			System.arraycopy(simples,0,simTest[i],0,size);
		}
		for(int i = 0;i<3;i++) {
			extTest[i] = new ExtendedKey[size];
			System.arraycopy(ext,0,extTest[i],0,size);
		}
		//System.out.println(Arrays.deepToString(simTest));
		// sort methods
		//simplekeys
		countINsim 	 = Sorter.insertionSort(simTest[0]);
		countQusim 	 = Sorter.quickSort(simTest[1],0,simTest[1].length,false);
		countHeapSim = Sorter.heapsort(simTest[2]);

		//extendedKeys
		countInEx 	 = Sorter.insertionSort(extTest[0]);
		countQuEx	 = Sorter.quickSort(extTest[1],0,extTest[1].length,false);
		countHeapEx	 = Sorter.heapsort(extTest[2]);

		res += "\ninsertionSort auf SimpleKey:\t"+ countINsim;
		res += "\nQuickSort auf SimpleKey:\t"+ countQusim;
		res += "\nHeapSort auf SimpleKey:\t\t"+ countHeapSim;

		res += "\n\ninsertionSort auf ExtendedKey:\t"+ countInEx;
		res += "\nQuickSort auf ExtendedKey:\t"+ countQuEx;
		res += "\nHeapSort auf ExtendedKey:\t"+ countHeapEx;

		return res;
	}

	public static void main(String[] args) {
		long i = 0,avg=0,loop=Long.parseLong(args[0]);
		String res;
		while (i < loop) {
					long start = System.nanoTime();
					res = Keytester(i); // Lässt loop oft die Testmethode laufen
					long diff = ((System.nanoTime()-start)/1000);
					System.out.println(res);
					i++;
//					TimeUnit.SECONDS.sleep(1);
					avg += diff;
					System.out.println("time needed:\t\t"+diff);
					System.out.println("\n\n");

				}
				System.out.println("AVG: \t\t"+(avg/loop)); // AVG time in Milliseconds
	}

	/**
	* Irellevant
	*/
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

		System.out.println("unsorted:       \t"+Arrays.toString(a));
		int r = Sorter.quickSort(a,0,a.length,false);
		System.out.println("quicksort_half: \t"+Arrays.toString(a));
		System.arraycopy(b, 0, a, 0, list.size());
		int r2 = Sorter.quickSort(a,0,a.length,true);
		System.out.println("quicksort_rand: \t"+Arrays.toString(a));
		System.arraycopy(b, 0, a, 0, list.size());
		int r3 = Sorter.insertionSort(a);
		System.out.println("insertionSort:  \t"+Arrays.toString(a));
		res += "Vergleiche quickSort1: 		|\t" + r  +"\n";
		res += "Vergleiche quickSort2: 		|\t" + r2 +"\n";
		res += "Vergleiche insertionSort: 	|\t" + r3 +"\n";
		int r4 = Sorter.heapsort(a);
		System.out.println("heapsort:      \t"+Arrays.toString(a));
		System.arraycopy(b, 0, a, 0, list.size());
		res += "Vergleiche heaposrt :		|\t" + r  +"\n";
		a = null;
		b = null;
		return res;
	}
}

/**
* This class implements the sorting algorithms
*/
class Sorter{

	/**
	*	Heapsort nach Skriptum
	*/
	public static <E extends Comparable<E>> int heapsort(E[] array){
		int heapsize,count = 0;
		if (array.length -1 < 2) return 0;
		for (int i = (array.length/2)-1; i >= 0; i--) {
			count += reheap(array,i,array.length);
		}
		heapsize = array.length;
		while (heapsize >= 2) {
			exch(array,0,heapsize-1);
			heapsize--;
			count += reheap(array,0,heapsize);
		}
		return count;
	}

	/**
	* reheap nach Skriptum
	*/
	public static <E extends Comparable<E>> int reheap(E[] array,int k, int l){
		int maxson,count=0;
		if((2*k +1) >= l) 
			return 0;

		if ((2*k +1) >= l-1)
			maxson = 2*k+1;
		else {
			count++;
			if (array[2*k+1].compareTo(array[2*k+2]) > 0)
				maxson = 2*k+1;
			else maxson = 2*k +2;
		}
		count++;
		if (array[k].compareTo(array[maxson]) >= 0)
			return count;

		exch(array,k,maxson);
		return count + reheap(array,maxson,l);
	}




	/**
	* InsertionSort nach Zettelvorlage
	*/

	public static <E extends Comparable<E>> int insertionSort(E[] array){
		int n = array.length;
		int counter = 0;
		for(int j=1; j<n; j++){
			E key = array[j];
			int i = j;
			while(i>0 && array[i-1].compareTo(key) > 0) {
				array[i] = array[i-1];
				i--;
			}
			counter += (j-i);
			counter += (i==1)? 0 : 1;
			array[i] = key;
		}
		return counter;
	}

	public static <E extends Comparable<E>> int quickSort(E[] array, int low, int high,boolean random){
		if (high-low <= 1) return 0;
		int lowcount=0,highcount=0;
		Pair<Integer> p = null;
		p = partition(array,low,high,random);
		//System.out.println(p);
		//System.out.println(Arrays.toString(array));
		lowcount = quickSort(	array,  
								p.left1, 
								p.right1,
								random);
		highcount = quickSort(	array,
								p.left2, 
								p.right2,
								random);
		return (int)(lowcount+highcount+p.count.intValue());
	}

	/**
	*	nach script
	*/
	protected static <E extends Comparable<E>> Pair<Integer> partition(E[] array, int left, int right,boolean random){
		int count = 0, pivotindex = random ? randomWithRange(left,right) :((left+right)/2);  // erstellen des pivot indexes
		E pivot = array[pivotindex]; // pivot element

		int lBorder = left, rBorder = right-1;
		while(rBorder - lBorder > 0){
			int i, lptr, rptr;
			
			for (i = lBorder; i < pivotindex ; i++ ) {
				if (array[i].compareTo(pivot) > 0)
					break;
			}
			lptr = i;
			count += i - lBorder + 1;
			if (i == pivotindex)
				count--;

			for (i = rBorder; i > pivotindex ; i--) {
				if(array[i].compareTo(pivot) < 0)
					break;
			}
			rptr = i;
			count += rBorder - i +1;
			if (i == pivotindex)
				count--;


			//System.out.println("exch: rptr:"+rptr+" arrayelem[rptr] "+array[rptr]+" lptr:"+lptr+" arrayelem[lptr] "+array[lptr]);
			exch(array,rptr,lptr);

			if (pivotindex == lptr)
				pivotindex = rptr;
			else if(pivotindex == rptr)
				pivotindex = lptr;


			//update borders for searchinterval
			if (lptr < pivotindex) {
				if (pivotindex < rptr) {	//lptr < pindex < rptr
					lBorder = lptr + 1;
					rBorder = rptr - 1;
				}
				if (pivotindex == rptr) {	//lptr < pindex = rptr
					lBorder = lptr + 1;
					rBorder = rptr;
				}
			}
			if (lptr == pivotindex) {
				if (pivotindex < rptr) {	//lptr = pindex < rptr
					lBorder = lptr;
					rBorder = rptr - 1;
				}
				if (pivotindex == rptr) {	//lptr = pindex = rptr
					lBorder = lptr;
					rBorder = rptr;
				}
			}
		}

		int left1, left2, right1, right2;
		if (pivotindex - left < right - pivotindex) {
			left1 = left;
			right1 = pivotindex;
			left2 = pivotindex + 1;
			right2 = right; 
		} 		
		else {
			left1 = pivotindex + 1;
			right1 = right;
			left2 = left;
			right2 = pivotindex;
		}

		return new Pair<Integer>(left1,left2,right1,right2,count);
	}




	 /**
	 * swapping helper nach Sedgewick
	 */
	 private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }



	//random helper functions
	public static int randomWithRange(int min, int max)
	{
		return (int)(max-(Math.random()*((max-min)+1)));

	}

}
/**
* Helping class
*/
class Pair<T> {
    public T left1;
    public T left2;
    public T right1;
    public T right2;
    public T count;


    public Pair(T f,T s, T t, T fr , T fth){
    	this.left1 = f; this.left2 = s; this.right1 = t; this.right2 = fr; this.count = fth;
    }
}
