import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Level2Stream
{
	public static void main(String[] args)
	{
		List<List<String>> customerOrders = Arrays.asList(
				Arrays.asList("Laptop", "Mouse"),
				Arrays.asList("Keyboard", "Mouse", "Monitor"),
				Arrays.asList("Laptop", "Headphones")
		);

		List<String> uniqueSortedItems = customerOrders.stream()
				.flatMap(List :: stream) // Flattens Stream<List<String>> to Stream<String>
				.distinct() // Removes duplicate elements
				.sorted() // Sorts elements natural order
				.collect(Collectors.toList());

		System.out.println(uniqueSortedItems);
		// Final Output
	}
}
