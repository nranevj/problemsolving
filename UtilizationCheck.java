import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;

public class UtilizationCheck {
    public int finalInstances(int instances, int[] averageUtil) {
        if(averageUtil == null || averageUtil.length == 0) return instances;
        int actiontimer = 0;
        double higherlimit = 2* Math.pow(10,8);
        
        for(int i=0;i<averageUtil.length;i++){
            actiontimer--;
            
            if(actiontimer <= 0 && averageUtil[i] < 25 && instances > 1){
                instances = instances % 2 == 0 ? instances: instances + 1;
                instances /= 2;
                actiontimer = 11;
            }
            else if(actiontimer <= 0 && averageUtil[i] > 60 && instances * 2 <= higherlimit){
                instances *= 2;
                actiontimer = 11;
            }
        }
    
        return instances;
    }
    
}
