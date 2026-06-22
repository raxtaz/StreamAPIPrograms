import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Level1Stream
{
	// Level 1: Beginner – Basic Filtering, Transforming, and Collection
	public static void main(String[] args)
	{
		List<String> names = Arrays.asList("Alisha", "Bijoy", "Anamika", "ChandrA", "Aman", "Daman");

		List<String> filteredNames = names.stream()
				.filter(name -> !name.startsWith("A"))
				.map(String :: toUpperCase)
				.collect(Collectors.toList());

		System.out.println((filteredNames));
	}
}
