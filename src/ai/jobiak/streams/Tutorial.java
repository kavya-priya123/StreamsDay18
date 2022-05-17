package ai.jobiak.streams;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Tutorial {

public static void main(String[] args) throws IOException {

System.out.println("---------------Example 1----------");
IntStream.range(1, 10).forEach(System.out::print);
System.out.println();
;

System.out.println("---------------Example 1.1----------");

IntStream.range(1, 10).skip(5).forEach(x -> System.out.println(x));

System.out.println("---------------Example 2----------");

/*
* IntStream.range(1, 10) .skip(4) .sum() // terminal statement cannot
* be followed by anything .forEach(System.out::print);
*/

Integer in = IntStream.range(1, 10).skip(4).sum(); // or
System.out.println(IntStream.range(1, 10).skip(4).sum());
//System.out.println(in);

System.out.println("---------------Example 3----------");

Stream.of("Mango", "Berry", "Tango", "Cherry", "Leach", "blackberry").sorted().findFirst()
.ifPresent(System.out::print);
System.out.println();

System.out.println("---------------Example 4----------");

Stream.of("Mango", "Berry", "Tango", "Cherry", "Leach", "blackberry").map(String::toLowerCase).sorted()
.filter(x -> x.endsWith("go"))
//.ifPresent(System.out::print);
.sorted().forEach(System.out::print);
System.out.println();

System.out.println("---------------Example 5----------");

String colors[] = { "orange", "pink", "yellow", "green", "blue", "black" };
Stream<String> stream = Arrays.stream(colors);
Arrays.stream(colors).filter(x -> x.startsWith("b")).sorted().forEach(System.out::print);

System.out.println();

System.out.println("---------------Example 6----------");

Arrays.stream(new int[] { 2, 4, 6, 8 }).map(x -> x * x).average().ifPresent(System.out::print);

System.out.println("\n---------------Example 7----------");

List<String> people = Arrays.asList("Tom", "dick", "harry", "whinny", "poo", "bugs");

people.stream().map(String::toLowerCase).forEach(System.out::println);

System.out.println("\n ---------------Example 8 Reading from files using Stream----------");

Stream<String> bands = Files.lines(Paths.get("bands.txt"));

bands.sorted().filter(x -> x.length() > 1).forEach(System.out::println);

bands.close();

System.out.println("\n---------------Example 9 Reading from files using Stream and collecting----------");

@SuppressWarnings("unchecked")
List<String> list = (List<String>) Files.lines(Paths.get("bands.txt")).filter(x -> x.contains("es"))
.collect(Collectors.toList());

list.forEach(System.out::println);

System.out.println(
"\n---------------Example 10 Reading from , seperated file using Stream and using count func ----------");

Stream<String> stream2 = Files.lines(Paths.get("data2.txt"));
int rowCount = (int) stream2.map(x -> x.split(",")).filter(x -> x.length == 3).count();
System.out.println(rowCount);

System.out.println(
"\n---------------Example 11 Reading from , seperated file using Stream and compare the value of col 2 > 15----------");

Stream<String> rows = Files.lines(Paths.get("data2.txt"));
rows.map(x -> x.split(",")).filter(x -> x.length == 3).filter(x -> Integer.parseInt(x[2]) > 15)
.forEach(x -> System.out.println(x[0] + ":" + x[1] + " : " + x[2]));

System.out.println(
"\n---------------Example 12 Reading from , seperated file using Stream and compare the value of col 2 > 15 and store in Map----------");

stream = Files.lines(Paths.get("data2.txt"));
Map<String, Integer> map = new HashMap<>();
map = stream.map(x -> x.split(",")).filter(x -> x.length == 3).filter(x -> Integer.parseInt(x[2]) > 15)
.collect(Collectors.toMap(x -> x[0], x -> Integer.parseInt(x[2])));
stream.close();

for (String key : map.keySet()) {

System.out.println(key + " and " + map.get(key));
}

// TO CONVERT A MAP TO STREAM<Map.Entry<K,V>>

Stream mapStream = map.entrySet().stream();

mapStream.forEach(System.out::println);
/*
* b=20 c=19 d=18
*/
// To convert a Map to Key stream

Stream keyStream = map.keySet().stream();
keyStream.forEach(System.out::println);

// To convert a Map to values stream

Stream valueStream = map.values().stream();
valueStream.forEach(System.out::println);

System.out.println("\n---------------Example 13 Reduction sum----------");
Double total = Stream.of(9.3, 1.5, 4.8).reduce(0.0, (Double a, Double b) -> (a + b));
System.out.println(total);

System.out.println("\n---------------Example 14 Reduction summary statistics----------");


IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10).summaryStatistics();
System.out.println(summary);

}

}