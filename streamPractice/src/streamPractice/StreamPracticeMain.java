package streamPractice;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//https://medium.com/javarevisited/must-know-java-8-stream-interview-questions-for-java-developers-series-16-9d0579623a70

public class StreamPracticeMain  {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(10,15,8,8,49,25,98,32, 10, 49);
        int []arr= new int[] {10,15,8,8,49,25,98,32, 10, 49};
        List<String> listString = Arrays.asList("hellooo", "this", "is" ,"listttttt");
// 1. find even numbers in list;
		List<Integer> evenList= findEven(list);
	

//	2. find all numbers starting with 1;
	   List<Integer> sOne= findStartOne(list);
	   
//	3. find unique
	   List<Integer> unique = findUnique(list);
	
//	4. find duplicates
	  List<Integer> duplicates= findDuplicates(list);
	  duplicates = findDuplicatesEfficient(list);
	  System.out.println(duplicates);
	  
//	5. findFirst;
	  findFirst(list);
	  
//	6. findTotalElements
	  System.out.println(findTotalElements(list));
	  
//	7. find first duplicate
	   System.out.println(findFirstDuplicate(list));
	   
//	8. sort the list;
	   System.out.println(sort(list));
	   
//	9. sort in dec order
	   System.out.println(sortRev(list));

// 10 Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
	   System.out.println(isDuplicate(list));
	   
// 11. convert the int[] to arraylist using stream;	  
	   List<Integer> dList = convertArrayToList(arr);
	  
// 12. convert the String object into upper case
	   System.out.println(getAllUpperCase(listString));
	   
//	13.  How to convert a List of objects into a Map by considering duplicated keys and store them in sorted order?
	   Map<Integer, Long> convertedMap= getMapByList(list);
	   
//	14 . How to find only duplicate elements with its count from the String ArrayList in Java8?
	   findDuplicateAndCount(list);
	   
//	   15. find max and second max characters string  from the list
	   System.out.println(findSmax(listString));


	   
	  
	  
	  
	  
	  
	  
	  
	  
	}
	private static String findSmax(List<String> list) {
		return list.stream().collect(Collectors.groupingBy(String::length, ()->new TreeMap<>(Collections.reverseOrder()), Collectors.toList()))
				.entrySet().stream().skip(1).findFirst().get().getValue().get(0);
		
		
//		final MaxSmax maxObj=new MaxSmax();
////		list.stream().collect(Collectors.toMap(Function.identity(), String::length)).entrySet().stream().forEach(e->{
////			if(e.getVa>max) {
////				
////			}
////		});
//		return null;
	}
	
	private static void findDuplicateAndCount(List<Integer> list) {
	    HashSet<Integer> set = new HashSet<>();
	    
		list.stream().filter(e-> !set.add(e)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		
	}

	private static Map<Integer, Long> getMapByList(List<Integer> list) {
		return list.stream().collect((Collectors.groupingBy(Function.identity(),Collectors.counting())));
	}

	private static List<String> getAllUpperCase(List<String> listString) {
		return listString.stream().map(String::toUpperCase).toList();
	}

	private static List<Integer> convertArrayToList(int[] arr) {
		
		return Arrays.stream(arr).boxed().toList();
	}

	private static boolean isDuplicate(List<Integer> list) {
		HashSet<Integer> set = new HashSet<>();
		return list.stream().anyMatch(e-> !set.add(e));
	}

	private static List<Integer> sortRev(List<Integer> list) {
		return list.stream().sorted(Collections.reverseOrder()).toList();
	}

	private static List<Integer> sort(List<Integer> list) {
		return list.stream().sorted().toList();
		
	}

	private static int findFirstDuplicate(List<Integer> list) {
		HashSet<Integer> set =new HashSet<>();
		 return list.stream().filter(e-> !set.add(e)).findFirst().get();
	}

	private static int findTotalElements(List<Integer> list) {
//		return list.stream().collect(Collectors.counting()).intValue();
		return (int)list.stream().count();
	}
	
	private static void findFirst(List<Integer> list) {
		list.stream().findFirst().ifPresentOrElse(System.out::println, ()->{System.out.println("Empty lis");});
		
	}

	private static List<Integer> findDuplicatesEfficient(List<Integer> list) {
		HashSet<Integer> set = new HashSet<>();
		return list.stream().filter(e-> !set.add(e)).collect(Collectors.toSet()).stream().toList();
	}
	private static List<Integer> findDuplicates(List<Integer> list) {
		return list.stream().collect(Collectors.groupingBy(n->n, Collectors.counting())).entrySet().stream()
		.filter(e->e.getValue()>1).map(e->e.getKey()).collect(Collectors.toList());
		
	}
	private static List<Integer> findUnique(List<Integer> list) {
		return list.stream().collect(Collectors.toSet()).stream().toList();
		
	}


	private static List<Integer> findStartOne(List<Integer> list) {
        return list.stream().filter((e)->  e.toString().charAt(0)=='1').toList();
		
	}

	public static List<Integer> findEven(List<Integer> list){
//		why toList is directly applied here
//		 because of IntStream;
		return list.stream().filter((e)-> e%2==0).toList();
		
	}

}

class MaxSmax{
	
	int max=0;
	int smax=0;
	String maxS="";
	String smaxS="";
}