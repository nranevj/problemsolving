import java.util.*;

public class TwoSumPairs {

  //Returns unique pairs that add up to the target provided
  public static List<List<Integer>> twoSumPairs(int arr[], int target){
    List<List<Integer>> result = new ArrayList<>();
    Map<Integer, Integer> count = new HashMap<>();

    for(int i=0;i<arr.length;i++){
      count.put(arr[i],count.getOrDefault(arr[i], 0)+1);
    }

    for(int i=0;i<arr.length && count.size()>0;i++){
      //We have found a pair, add it to the result and decrement the count of each number from the HashMap
      if(count.containsKey(arr[i]) &&count.containsKey(target-arr[i])){
        result.add(Arrays.asList(arr[i], target - arr[i]));
        count.put(arr[i], count.get(arr[i])-1);
        count.put(target - arr[i], count.get(target - arr[i])-1);
        if(count.get(arr[i])<=0){count.remove(arr[i]);}
        if(count.get(target-arr[i])<=0){count.remove(target-arr[i]);}
      }
    }
    return result;
  }
  
  public static void main(String args[]){
	/*
	//You may use the following to test this code
	
	int[] arr= {1, 5, 7, -1, 5};
    int target = 6;
	
    for(List<Integer> pair: twoSumPairs(arr, target)){
      System.out.println(pair.get(0)+" "+pair.get(1));
    }
	*/
  }
}