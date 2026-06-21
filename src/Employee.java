class Employee
{
	private int id;
	private String name;
	private double salary;

	public Employee(int id, String name, double salary)
	{
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public double getSalary()
	{
		return salary;
	}

	@Override
	public String toString()
	{
		return id + " " + name + " " + salary;
	}
}

public void main(String[] args)
{
	Employee emp1 = new Employee(4, "Jacob", 3000);
	Employee emp2 = new Employee(6, "Frank", 50000);
	Employee emp3 = new Employee(7, "Anna", 35000);
	Employee emp4 = new Employee(8, "Fred", 35000);
	Employee emp5 = new Employee(9, "Maria", 75000);
	Employee emp6 = new Employee(10, "Anthony", 100000);

	List<Integer> nums = Arrays.asList(1, 2, 2, 3, 3, 4, 5, 6);

	//1. Even Number
	nums.stream()
			.filter(n -> n % 2 == 0)
			.forEach(System.out :: println);

	//2. Odd Number
	nums.stream()
			.filter(n -> n % 2 != 0)
			.forEach(System.out :: println);

	//3. Square Every Number
	nums.stream()
			.map(n -> n * n)
			.forEach(System.out :: println);

	//4. Sort Number
	nums.stream()
			.sorted()
			.forEach(System.out :: println);

	//5. Sort Descending
	nums.stream()
			.sorted(Comparator.reverseOrder())
			.forEach(System.out :: println);

	//6. Find Maximum
	nums.stream()
			.max(Integer :: compareTo)
			.ifPresent(System.out :: println);

	//7. Find Minimum
	nums.stream()
			.min(Integer :: compareTo)
			.ifPresent(System.out :: println);

	//8. Count Elements
	long count = nums.stream().count();

	//9. Sum of Numbers
	int sum = nums.stream()
			.mapToInt(Integer :: intValue)
			.sum();

	//10. Average
	double avg = nums.stream()
			.mapToInt(Integer :: intValue)
			.average()
			.getAsDouble();

	//11. Remove Duplicates

	nums.stream()
			.distinct()
			.forEach(System.out :: println);

	//12.
}

