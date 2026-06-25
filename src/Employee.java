import java.util.*;
import java.util.stream.*;

public record Employee(int id, String name, double salary)
{


	@Override
	public String toString()
	{
		return id + " " + name + " " + salary;
	}

	static void main(String[] args)
	{
		Employee emp1 = new Employee(4, "Jacob", 3000);
		Employee emp2 = new Employee(6, "Frank", 50000);
		Employee emp3 = new Employee(7, "Anna", 35000);
		Employee emp4 = new Employee(8, "Fred", 35000);
		Employee emp5 = new Employee(9, "Maria", 75000);
		Employee emp6 = new Employee(10, "Anthony", 100000);

		List<Employee> employees = Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6);
		List<Integer> nums = Arrays.asList(1, 2, 2, 3, 3, 4, 5, 6);

		System.out.println("1. Even Numbers:");
		nums.stream()
				.filter(n -> n % 2 == 0)
				.forEach(System.out :: println);
		System.out.println("==============\n");

		System.out.println("2. Odd Numbers:");
		nums.stream()
				.filter(n -> n % 2 != 0)
				.forEach(System.out :: println);
		System.out.println("==============\n");

		System.out.println("3. Squares:");
		nums.stream()
				.map(n -> n * n)
				.forEach(System.out :: println);
		System.out.println("==============\n");

		System.out.println("4. Sorted (Ascending):");
		nums.stream()
				.sorted()
				.forEach(System.out :: println);
		System.out.println("==============\n");

		System.out.println("5. Sorted (Descending):");
		nums.stream()
				.sorted(Comparator.reverseOrder())
				.forEach(System.out :: println);
		System.out.println("==============\n");

		System.out.println("6. Maximum:");
		nums.stream()
				.max(Integer :: compareTo)
				.ifPresent(System.out :: println);
		System.out.println("==============\n");

		System.out.println("7. Minimum:");
		nums.stream()
				.min(Integer :: compareTo)
				.ifPresent(System.out :: println);
		System.out.println("==============\n");

		System.out.println("8. Count Elements:");
		long countElements = nums.stream().count();
		System.out.println(countElements);
		System.out.println("==============\n");

		System.out.println("9. Sum of Numbers:");
		int sum = nums.stream()
				.mapToInt(Integer :: intValue)
				.sum();
		System.out.println(sum);
		System.out.println("==============\n");

		System.out.println("10. Average:");
		double avg = nums.stream()
				.mapToInt(Integer :: intValue)
				.average()
				.orElse(0.0);
		System.out.println(avg);
		System.out.println("==============\n");

		System.out.println("11. Distinct Numbers:");
		nums.stream()
				.distinct()
				.forEach(System.out :: println);
		System.out.println("==============\n");

		System.out.println("12. First Element:");
		nums.stream()
				.findFirst()
				.ifPresent(System.out :: println);
		System.out.println("==============\n");

		System.out.println("13. Any Match (Greater than 5):");
		boolean found = nums.stream()
				.anyMatch(n -> n > 5);
		System.out.println(found);
		System.out.println("==============\n");

		System.out.println("14. All Match (All Positive):");
		boolean allPositive = nums.stream()
				.allMatch(n -> n > 0);
		System.out.println(allPositive);
		System.out.println("==============\n");

		System.out.println("15. Highest Salary Employee:");
		Employee highestSalaryEmp = employees.stream()
				.max(Comparator.comparing(Employee :: salary))
				.orElse(null);
		System.out.println(highestSalaryEmp);
		System.out.println("==============\n");

		System.out.println("16. Lowest Salary Employee:");
		Employee lowestSalaryEmp = employees.stream()
				.min(Comparator.comparing(Employee :: salary))
				.get();
		System.out.println(lowestSalaryEmp);
		System.out.println("==============\n");

		System.out.println("17. Employees Sorted by Salary:");
		employees.stream()
				.sorted(Comparator.comparing(Employee :: salary))
				.forEach(System.out :: println);
		System.out.println("==============\n");

		System.out.println("18. Employees Sorted by Name:");
		employees.stream()
				.sorted(Comparator.comparing(Employee :: name))
				.forEach(System.out :: println);
		System.out.println("==============\n");

		System.out.println("19. Total Salary of All Employees:");
		double totalSalary = employees.stream()
				.mapToDouble(Employee :: salary)
				.sum();
		System.out.println(totalSalary);
		System.out.println("==============\n");

		System.out.println("20. Employee Names:");
		employees.stream()
				.map(Employee :: name)
				.forEach(System.out :: println);
		System.out.println("==============\n");

		System.out.println("21. Collected Names into List:");
		List<String> names = employees.stream()
				.map(Employee :: name)
				.toList();
		System.out.println(names);
		System.out.println("==============\n");

		System.out.println("22. Employees Grouped by Salary:");
		Map<Double, List<Employee>> salaryGroups = employees.stream()
				.collect(Collectors.groupingBy(Employee :: salary));
		salaryGroups.forEach((salary, emps) -> System.out.println("Salary " + salary + ": " + emps));
		System.out.println("==============\n");

		System.out.println("23. Total Employee Count:");
		long countEmployee = employees.size();
		System.out.println(countEmployee);
		System.out.println("==============\n");

		System.out.println("24. Employees with Salary Greater than 40000:");
		employees.stream()
				.filter(e -> e.salary() > 40000)
				.forEach(System.out :: println);
		System.out.println("==============\n");

		System.out.println("25. Second-Highest Salary Employee:");
		Employee secondHighestEmp = employees.stream()
				.sorted(Comparator.comparing(Employee :: salary).reversed())
				.skip(1)
				.findFirst()
				.get();
		System.out.println(secondHighestEmp);
		System.out.println("==============\n");

		System.out.println("26. Frequency of Characters in 'banana':");
		String str1 = "banana";
		Map<Character, Long> freq = str1.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(c -> c, Collectors.counting()));
		System.out.println(freq);
		System.out.println("==============\n");

		System.out.println("27. First Non-Repeated Character in 'swiss':");
		String str2 = "swiss";
		Character result = str2.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(
						c -> c,
						LinkedHashMap :: new,
						Collectors.counting()))
				.entrySet()
				.stream()
				.filter(e -> e.getValue() == 1)
				.map(Map.Entry :: getKey)
				.findFirst()
				.orElse(null);
		System.out.println(result);
		System.out.println("==============\n");
	}
}