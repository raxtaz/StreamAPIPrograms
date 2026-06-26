import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

public class StreamApiPractice
{

	// ============= PROGRAM 1: FILTER & MAP =============
	static class Program1_FilterMap
	{
		public static void main(String[] args)
		{
			System.out.println("=== PROGRAM 1: FILTER & MAP ===\n");

			List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

			// Filter even numbers and square them
			List<Integer> result = numbers.stream()
					.filter(n -> n % 2 == 0)
					.map(n -> n * n)
					.collect(Collectors.toList());

			System.out.println("Original list: " + numbers);
			System.out.println("Even numbers squared: " + result);

			// Another example: Extract strings and convert to uppercase
			List<String> words = Arrays.asList("hello", "world", "stream", "api");
			List<String> upperWords = words.stream()
					.filter(w -> w.length() > 3)
					.map(String :: toUpperCase)
					.collect(Collectors.toList());

			System.out.println("\nOriginal words: " + words);
			System.out.println("Uppercase words (length > 3): " + upperWords);
		}
	}

	// ============= PROGRAM 2: REDUCE & AGGREGATION =============
	static class Program2_ReduceAggregation
	{
		public static void main(String[] args)
		{
			System.out.println("\n=== PROGRAM 2: REDUCE & AGGREGATION ===\n");

			List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

			// Sum using reduce
			int sum = numbers.stream()
					.reduce(0, Integer :: sum);
			System.out.println("Sum using reduce: " + sum);

			// Product using reduce
			int product = numbers.stream()
					.reduce(1, (a, b) -> a * b);
			System.out.println("Product using reduce: " + product);

			// Max and Min
			Optional<Integer> max = numbers.stream()
					.reduce(Integer :: max);
			Optional<Integer> min = numbers.stream()
					.reduce(Integer :: min);

			System.out.println("Max value: " + max.orElse(0));
			System.out.println("Min value: " + min.orElse(0));

			// Using collectors for aggregation
			IntSummaryStatistics stats = numbers.stream()
					.collect(Collectors.summarizingInt(Integer :: intValue));

			System.out.println("\nUsing IntSummaryStatistics:");
			System.out.println("Count: " + stats.getCount());
			System.out.println("Sum: " + stats.getSum());
			System.out.println("Average: " + stats.getAverage());
			System.out.println("Max: " + stats.getMax());
			System.out.println("Min: " + stats.getMin());
		}
	}

	// ============= PROGRAM 3: GROUPBY & COUNTING =============
	static class Program3_GroupByAndCounting
	{
		public static void main(String[] args)
		{
			System.out.println("\n=== PROGRAM 3: GROUPBY & COUNTING ===\n");

			// Group by first character and count
			List<String> words = Arrays.asList("apple", "apricot", "banana", "cherry", "avocado", "blueberry");

			Map<Character, Long> countByFirstChar = words.stream()
					.collect(Collectors.groupingBy(
							w -> w.charAt(0),
							Collectors.counting()
					));

			System.out.println("Words grouped by first character (with count):");
			countByFirstChar.forEach((k, v) -> System.out.println(k + " -> " + v));

			// Group by length
			Map<Integer, List<String>> groupByLength = words.stream()
					.collect(Collectors.groupingBy(String :: length));

			System.out.println("\nWords grouped by length:");
			groupByLength.forEach((k, v) -> System.out.println("Length " + k + " -> " + v));

			// Group by and map values
			Map<Character, Set<String>> groupByCharAsSet = words.stream()
					.collect(Collectors.groupingBy(
							w -> w.charAt(0),
							Collectors.toSet()
					));

			System.out.println("\nWords grouped by first character (as Set):");
			groupByCharAsSet.forEach((k, v) -> System.out.println(k + " -> " + v));

			// Partition by length > 5
			Map<Boolean, List<String>> partitioned = words.stream()
					.collect(Collectors.partitioningBy(w -> w.length() > 5));

			System.out.println("\nPartitioned by length > 5:");
			System.out.println("Length > 5: " + partitioned.get(true));
			System.out.println("Length <= 5: " + partitioned.get(false));
		}
	}

	// ============= PROGRAM 4: DISTINCT, SORTED & LIMIT =============
	static class Program4_DistinctSortedLimit
	{
		public static void main(String[] args)
		{
			System.out.println("\n=== PROGRAM 4: DISTINCT, SORTED & LIMIT ===\n");

			List<Integer> numbers = Arrays.asList(5, 2, 8, 2, 9, 1, 5, 5, 3, 7);

			// Distinct and sorted
			List<Integer> result1 = numbers.stream()
					.distinct()
					.sorted()
					.collect(Collectors.toList());

			System.out.println("Original: " + numbers);
			System.out.println("Distinct and sorted: " + result1);

			// Sorted in reverse order with limit
			List<Integer> top3Desc = numbers.stream()
					.distinct()
					.sorted(Comparator.reverseOrder())
					.limit(3)
					.collect(Collectors.toList());

			System.out.println("Top 3 (descending): " + top3Desc);

			// Skip and limit
			List<Integer> skipAndLimit = numbers.stream()
					.distinct()
					.sorted()
					.skip(2)
					.limit(3)
					.collect(Collectors.toList());

			System.out.println("Skip 2, limit 3: " + skipAndLimit);

			// Strings: Distinct and case-insensitive sort
			List<String> words = Arrays.asList("apple", "APPLE", "banana", "BANANA", "cherry", "Apple");

			List<String> distinctWords = words.stream()
					.distinct()
					.sorted(String.CASE_INSENSITIVE_ORDER)
					.collect(Collectors.toList());

			System.out.println("\nOriginal words: " + words);
			System.out.println("Distinct (case-insensitive sorted): " + distinctWords);
		}
	}

	// ============= PROGRAM 5: FLATMAP =============
	static class Program5_FlatMap
	{
		public static void main(String[] args)
		{
			System.out.println("\n=== PROGRAM 5: FLATMAP ===\n");

			// Flatten nested lists
			List<List<Integer>> nestedNumbers = Arrays.asList(
					Arrays.asList(1, 2, 3),
					Arrays.asList(4, 5),
					Arrays.asList(6, 7, 8, 9)
			);

			List<Integer> flatList = nestedNumbers.stream()
					.flatMap(List :: stream)
					.collect(Collectors.toList());

			System.out.println("Nested list: " + nestedNumbers);
			System.out.println("Flattened: " + flatList);

			// Flatten and filter
			List<Integer> filteredFlat = nestedNumbers.stream()
					.flatMap(List :: stream)
					.filter(n -> n > 3)
					.collect(Collectors.toList());

			System.out.println("Flattened and filtered (> 3): " + filteredFlat);

			// Flatten strings and convert to uppercase
			List<List<String>> nestedWords = Arrays.asList(
					Arrays.asList("hello", "world"),
					Arrays.asList("stream", "api"),
					List.of("java")
			);

			List<String> flatWords = nestedWords.stream()
					.flatMap(List :: stream)
					.map(String :: toUpperCase)
					.collect(Collectors.toList());

			System.out.println("\nNested words: " + nestedWords);
			System.out.println("Flattened and uppercase: " + flatWords);

			// FlatMap with multiple values per element
			List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
			List<Integer> multiplied = numbers.stream()
					.flatMap(n -> Arrays.asList(n, n * 2).stream())
					.collect(Collectors.toList());

			System.out.println("\nOriginal: " + numbers);
			System.out.println("Each number mapped to [n, n*2]: " + multiplied);
		}
	}

	// ============= BONUS: PRACTICAL INTERVIEW EXAMPLES =============
	static class BonusPrograms
	{

		static class Employee
		{
			int id;
			String name;
			String department;
			double salary;

			Employee(int id, String name, String department, double salary)
			{
				this.id = id;
				this.name = name;
				this.department = department;
				this.salary = salary;
			}

			@Override
			public String toString()
			{
				return String.format("(id=%d, name=%s, dept=%s, sal=%.2f)",
						id, name, department, salary);
			}
		}

		public static void main(String[] args)
		{
			System.out.println("\n=== BONUS: PRACTICAL EXAMPLES ===\n");

			List<Employee> employees = Arrays.asList(
					new Employee(1, "Alice", "IT", 75000),
					new Employee(2, "Bob", "HR", 60000),
					new Employee(3, "Charlie", "IT", 80000),
					new Employee(4, "Diana", "Finance", 70000),
					new Employee(5, "Eve", "IT", 65000),
					new Employee(6, "Frank", "HR", 62000)
			);

			// 1. Get all IT employees
			System.out.println("1. All IT employees:");
			employees.stream()
					.filter(e -> "IT".equals(e.department))
					.forEach(System.out :: println);

			// 2. Get average salary by department
			System.out.println("\n2. Average salary by department:");
			employees.stream()
					.collect(Collectors.groupingBy(
							e -> e.department,
							Collectors.averagingDouble(e -> e.salary)
					))
					.forEach((dept, avg) -> System.out.println(dept + " -> " + avg));

			// 3. Find highest paid employee
			System.out.println("\n3. Highest paid employee:");
			employees.stream()
					.max(Comparator.comparingDouble(e -> e.salary))
					.ifPresent(System.out :: println);

			// 4. Get employee names sorted
			System.out.println("\n4. Employee names (sorted):");
			employees.stream()
					.map(e -> e.name)
					.sorted()
					.forEach(System.out :: println);

			// 5. Count employees by department
			System.out.println("\n5. Count by department:");
			employees.stream()
					.collect(Collectors.groupingBy(
							e -> e.department,
							Collectors.counting()
					))
					.forEach((dept, count) -> System.out.println(dept + " -> " + count));

			// 6. Salary increment (map and new list)
			System.out.println("\n6. Employees with 10% salary increase:");
			employees.stream()
					.peek(e -> e.salary *= 1.10)
					.forEach(System.out :: println);
		}
	}

	// ============= MAIN METHOD =============
	static void main(String[] args)
	{
		Program1_FilterMap.main(null);
		Program2_ReduceAggregation.main(null);
		Program3_GroupByAndCounting.main(null);
		Program4_DistinctSortedLimit.main(null);
		Program5_FlatMap.main(null);
		BonusPrograms.main(null);
	}
}
