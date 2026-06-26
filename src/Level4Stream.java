import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Level4Stream
{
	static void main(String[] args)
	{
		List<String> paragraphs = Arrays.asList(
				"Java is a programming language.",
				"Java streams are powerful & efficient.",
				"I love coding Java Streams API."
		);

		Map<String, Long> top3Words = paragraphs.stream()
				.flatMap(p -> Arrays.stream(p.toLowerCase().split("\\W+")))
				.filter(word -> !word.isEmpty())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.sorted(Map.Entry.<String, Long>comparingByValue().reversed())
				.limit(3)
				.collect(Collectors.toMap(
						Map.Entry :: getKey,
						Map.Entry :: getValue,
						(oldValue, newValue) -> oldValue,
						LinkedHashMap :: new
				));
		System.out.println(top3Words);
	}
}
