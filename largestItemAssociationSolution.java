import java.util.*;

class PairString{
	String first;
	String second;
	
	public PairString(String first, String second){
		this.first = first;
		this.second = second;
	}
}

class largestItemAssociationSolution{

	//You may add your test cases here in the form of PairString objects
	//and add them to the List<PairString>
	public static void main(String args[]){
		List<PairString> itemAssociation = new ArrayList();
		itemAssociation.add(new PairString("item1", "item2"));
		itemAssociation.add(new PairString("item2", "item3"));
		itemAssociation.add(new PairString("item2", "item4"));
		itemAssociation.add(new PairString("item5", "item6"));
		itemAssociation.add(new PairString("item6", "item7"));
		List<String> result = largestItemAssociation(itemAssociation);
		result.forEach(System.out::println);
	}
	
	public static List<String> largestItemAssociation(List<PairString> itemAssociation){
		Map<String, Set<String>> itemAssociationMap = new HashMap();
		
		//Form an adjacency list
		for(PairString ps: itemAssociation){
			Set<String> tempAssociationSet = itemAssociationMap.getOrDefault(ps.first, new HashSet());
			tempAssociationSet.add(ps.second);
			itemAssociationMap.put(ps.first,tempAssociationSet);
			tempAssociationSet = itemAssociationMap.getOrDefault(ps.second, new HashSet());
			tempAssociationSet.add(ps.first);
			itemAssociationMap.put(ps.second,tempAssociationSet);		
		}

		List<String> result = new LinkedList();
	
		//For every item as a start, do a Depth First search for associations
		for(String item: itemAssociationMap.keySet()){
			List<String> tempresult = DFS_iterative(item, itemAssociationMap, new HashSet(), new LinkedList(), new LinkedList());

      if(doesAreplaceB(tempresult, result)){
        result = getClonedList(tempresult);
      }
    }
		return result;
	}
	
	public static List<String> DFS_iterative(String item, Map<String, Set<String>> itemAssociationMap, Set<String> visitedItem, List<String> result, List<String> tempresult){
		Stack<String> stack = new Stack();
		stack.push(item);
		
		while(!stack.isEmpty()){
			String currentItem = stack.peek();
			if(visitedItem.contains(currentItem)){
				stack.pop();
				if(tempresult.size()>0) tempresult.remove(tempresult.size()-1);
				continue;
			}
			
			visitedItem.add(currentItem);
			tempresult.add(currentItem);
			boolean hasAllAssociationsVisited = true;
			
			for(String eachItem: itemAssociationMap.get(currentItem)){
				if(!visitedItem.contains(eachItem)){
					stack.push(eachItem);
					hasAllAssociationsVisited = false;
				}
			}
			
			//Means we have reached the end of an association
			//We need to store the association or compare it with the result association
			if(hasAllAssociationsVisited){
				if(doesAreplaceB(tempresult, result)){
				  result = getClonedList(tempresult);
				}
			}
		}
    return result;
	}

  private static List<String> getClonedList(List<String> sourceList){
      List<String> targetList = new LinkedList();
      for(String item: sourceList){
        targetList.add(item);
      }
      return targetList;
  }
	
	private static boolean doesAreplaceB(List<String> A, List<String> B){
		if(A.size() > B.size()) return true;
		if(A.size() == B.size()){
			for(int i=0;i<A.size();i++){
				int res = A.get(i).compareTo(B.get(i));
				if(res<0) return true;
				if(res>0) break;
			}
		}
		return false;
	}
}
