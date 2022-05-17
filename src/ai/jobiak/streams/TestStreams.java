package ai.jobiak.streams;
import java.util.*; 
import java.util.function.*;
import java.util.stream.Collectors;
public class TestStreams 
{
	public static void main(String[] args)
	{
		ArrayList<String> list=new ArrayList<>();
		list.add("Hello");
		list.add("HOla");
		list.add("Bonjure");
		list.add("Buenos dias");
		//How do we access the list ? We access the list by getting the Iterator
	/*	Iterator<String> iter=list.iterator();
		while(iter.hasNext())
		{
			String element=iter.next();
			System.out.println(element);
		}
		//for(String str:list){ System.out.println(str);}
	*/	
		//Java8 Streams is all about Collections
		//How do I access the elements of the collection with Streams ? 
		//we got the elements directly, without opening means explicit navigation and streams will do implicit navigation. 
		
		list.stream().forEach(System.out::println); //Method Reference
		
		//A Stream has three Operations 1.filter(Predicate Interface) 2.map function(Function Interface) and 3.terminal operation(Consumer Interface)
		Consumer<String> consumer=(String str)->{System.out.println(str);};
		list.stream().forEach(consumer);	//What code to be executed(sysout(str);) is passed as a parameter i.e. consumer
		//The Responsibility of a Consumer is to accept a String and a method &use it for printing.
		// eg : I want to paint a piece of wall.For that what do I need ? I need color,brush,cloth.
	
		long count=list.stream().count();	//It gives number of elements in the list
		System.out.println(count);
		
		//new way of writing loops,new way of giving conditions,new way of writing functions
		//What is e? e is supplied by the stream implicitly.Only one element is passed to the filter at a time. 
		//filter --> predicate filters some data based on conditions
		List<String> letterBList=list.stream().filter(e->(e.charAt(0)=='B')).collect(Collectors.toList());
		System.out.println(letterBList);	//lambda -->predicate
	
		//Predicate as a Functional Interface
		//testB is a Predicate and Predicate is a filter
		Predicate<String> testB =(String str)->{return (str.charAt(0)=='B');};
		letterBList = list.stream().filter(testB).collect(Collectors.toList());
		System.out.println(letterBList);

		
		//get the Strings with length > 4
		List<String> list2=list.stream().filter(e->(e.length()>4)).collect(Collectors.toList());
		System.out.println(list2);	
		
		//map function --> which will carry out to change/transform the original data
		list.stream().map(e->e.toUpperCase()).forEach(consumer);
		
		Function<String,String> upperCaseFunction=(e)->{return e.toUpperCase();};
		list.stream().map(upperCaseFunction).forEach(consumer);
		
		//get the strings with only first 3 letters
		list.stream().map(e->e.substring(0,3)).forEach(consumer);
		
		
	}
}
