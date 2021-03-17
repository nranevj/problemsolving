import java.util.*;
import java.io.*;
import java.lang.*;

public class Solution {
    public int cutOffRank(int cutOffRank, int num, int[] scores) {
        int[] score = new int[101];
        int rank = 1, result = 0;
        
        for(int i=0;i<scores.length;i++){
            score[scores[i]]++;
        }
        
        for(int i=score.length-1;i>=1;i--){
            if(rank > cutOffRank) break;
            result += score[i];
            rank += score[i];
        }
        return result;
    }
}

//https://aonecode.com/amazon-online-assessment-cutoff-ranks
//Brute force solution is sort the array in descending order, and then go through the array from left to right, where we will update the rank and result
//depending on how many scores are same. If at any point the rank is more than the cut off rank, we will stop there itself and return the result
//This takes O(n log n) time

//Better solution is counting sort
//We are given that the scores are in the range 0-100, that means we can create a bucket or an array to count the scores
//Then, we can just go from right to left, and keep adding the contents of the bucket to rank and the result until rank is less than or equal to cut off rank
//This takes O(n) time
