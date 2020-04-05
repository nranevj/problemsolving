
//There are three solutions to this problem:
//Brute Force: Takes O(n^2) time
//Divide and Conquer: Takes O(nlogn) time
//Kadane's algorithm (DP solution): Takes O(n) time

class MSSSolution{
	
	//Brute Force is just taking one number at a time, then comparing it with maxSum
	//Then, take size two window, take sum of them, and slide. Keep maintaing the maxSum as you slide
	//This way, first time you'll go n (arrayLength) times from left to right, then n-1...then 1
	//Hence the O(n^2) time
	
	//Divide and Conquer recursive solution
	//This is similar to merge sort where we divide the array into two parts recursively until size 1 reached
	//Once returned, we get the sum from left...mid and mid+1...right
	//We determine the max of leftsum, rightsum, and leftsum + rightsum
	//We determine the max of above, and MSSleft, MSSright
	//Input: array of numbers, 0, arrLength - 1
	public static int MaximumSubArraySum(int arr[], int left, int right){
		if(left >= right) return arr[left];
		int mid = left + (right-left)/2;
		int MSSleft = MaximumSubArraySum(arr, left, mid);
		int MSSright = MaximumSubArraySum(arr, mid+1, right);
		int leftsum = Integer.MIN_VALUE, rightsum = Integer.MIN_VALUE;
		int tempsum = 0;
		
		for(int i=left;i<=mid;i++){
			tempsum += arr[i];
			leftsum = Math.max(tempsum, leftsum);
		}

		tempsum = 0;
		for(int i=mid+1;i<=right;i++){
			tempsum += arr[i];
			rightsum = Math.max(tempsum, rightsum);
		}

		int sum = Math.max(leftsum, rightsum);
		int maxMSS = Math.max(MSSleft, MSSright);
		return Math.max(Math.max(maxMSS, sum), leftsum + rightsum);
	}
	
	//Kadane's algorithm is a specific algorithm/solution to this problem that runs in O(n) time
	//Take 0th index number as the maxSum initially
	//Start from the 1st index, and check whether the previous element is greater than 0
	//If yes, then it is going to increase total value. Do arr[currentindex] = arr[currentindex] + arr[currentindex-1];
	//If no, then keep arr[currentindex] as it is
	//Update maxSum as max of maxSum & maxarr[currentindex]
	//return maxSum
	public static int maxSubArraySum(int[] nums) {
		int maxSum = nums[0];
		
		for(int i=1;i<nums.length;i++){
			if(nums[i-1] > 0){nums[i] = nums[i] + nums[i-1];}
			maxSum = Math.max(maxSum, nums[i]);
		}
		return maxSum;
	}
	
	public static void main(String args[]){
		int arr[] = new int[]{5,-1,3,2,0};
		//You may call the functions as follows
		//int maxSum = MaximumSubArraySum(arr,0,arr.length-1);
		//int maxSum2 = maxSubArraySum(arr);
	}
}
