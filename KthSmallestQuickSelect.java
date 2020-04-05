import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

  //Focusing on kth smallest element problem, it has three solutions:
  //Sort the array and return arr[k-1] takes O(n log n) time
  //Use MaxHeap of size k and go on adding elements (heap will remove elements if size>k,
  //Eventually, after adding all elements, the first element in the maxheap will be the answer

  //One of the common applications of quickselect algorithm:
  //Given an unsorted array and a position, find the element at that position (if array was sorted)
  //Quickselect doesn't care about sorting the whole array
  //It only uses the partitioning step of Quicksort to randomly select a pivot and parition elements around it
  //We move to the left/right part of the array depending on the target index
  //In the worst case, it will take O(n^2) if the pivot always falls at one side, but in the average case it is O(n)
  //Example: kth Smallest/largest element, Find median of unsorted array 

public class Main {

  //Returns the kthSmallestElement in an array
  //Returns Integer's lowest value if k is out of range (this is upto your implementation)
	public static int kthSmallestElement(int[] arr, int k){
		int low = 0, high = arr.length-1;

		//Because we want to find the index 
		k--;

		//Updates the pivot based on whether it matches the k position or not
		//If found, return it
		while(low<=high){
		  int pivot = partitionArray(arr, low, high);
		  if(pivot==k) return arr[pivot];
		  else if(pivot>k) high = pivot-1;
		  else low = pivot+1; 
		}
		//If k is out of range 
		return Integer.MIN_VALUE;
	}
	
	//Partitions the array based on the pivot
	//Smaller/equal elements on the left, larger elements on right
	//return the pivot index
	public static int partitionArray(int[] arr, int low, int high){
		//Randomly select a pivot
		int pivot = ThreadLocalRandom.current().nextInt(low, high+1);

		//Put pivot at the last index
		swap(arr, pivot, high);

		int i=0, j=0;

		//Partition numbers based on whether they are lesser than the pivot
		while(j < high){
		  if(arr[j]<=arr[high]){
			swap(arr, i, j);
			i++;
		  }
		  j++;
		}

		//i is the index at which the pivot has to be placed back
		swap(arr, i, high); 
		return i; 
	}

	private static void swap(int[] arr, int first, int second){
		int temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
	}
  
	public static void main(String[] args) {
		int[] arr = new int[]{12, 3, 5, 7, 4, 19, 26};
		int k = 7;
		//You may use the following code to verify your answer
		//System.out.println(kthSmallestElement(arr, k));
		//Arrays.sort(arr);
		//System.out.println(Arrays.toString(arr));
	}

}
