import java.util.*;
import java.util.stream.Collectors;

record DeptEmployee(String name, String department, double salary) {


    @Override
    public String toString() {
        return name + ": ₹" + salary + ")";
    }
}

public class Level3Stream {
    static void main(String[] args) {
        List<DeptEmployee> employees = Arrays.asList(
                new DeptEmployee(" Jagdeep", " IT ", 85000),
                new DeptEmployee(" Hetal", " IT ", 95000),
                new DeptEmployee(" Brijesh", " HR ", 60000),
                new DeptEmployee(" Anamika", " HR ", 75000),
                new DeptEmployee(" Charlie", " FIN ", 110000)
        );

        Map<String, DeptEmployee> topPaidByDept = employees.stream()
                .collect(Collectors.toMap(
                        DeptEmployee::department,
                        e -> e,
                        (e1, e2) -> e1.salary() >= e2.salary() ? e1 : e2
                ));

        System.out.println(topPaidByDept);
    }
}
