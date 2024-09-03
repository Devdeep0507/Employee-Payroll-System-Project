package project;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    // Abstract method to be implemented by subclasses
    public abstract double calculateSalary();

    //Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    //Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem {
    private List<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
        }
    }

    public void displayEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}

public class projectt {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayrollSystem payrollSystem = new PayrollSystem();

        System.out.println("Enter the number of employees:");
        int numEmployees = scanner.nextInt();

        for (int i = 0; i < numEmployees; i++) {
            System.out.println("Enter employee type (1 for Full-time, 2 for Part-time):");
            int employeeType = scanner.nextInt();

            System.out.println("Enter employee name:");
            String name = scanner.next();

            System.out.println("Enter employee ID:");
            int id = scanner.nextInt();

            if (employeeType == 1) {
                System.out.println("Enter monthly salary:");
                double monthlySalary = scanner.nextDouble();
                FullTimeEmployee emp = new FullTimeEmployee(name, id, monthlySalary);
                payrollSystem.addEmployee(emp);
            } else if (employeeType == 2) {
                System.out.println("Enter hours worked:");
                int hoursWorked = scanner.nextInt();
                System.out.println("Enter hourly rate:");
                double hourlyRate = scanner.nextDouble();
                PartTimeEmployee emp = new PartTimeEmployee(name, id, hoursWorked, hourlyRate);
                payrollSystem.addEmployee(emp);
            }
        }

        System.out.println("Initial Employee Details:");
        payrollSystem.displayEmployees();

        System.out.println("\nEnter the ID of the employee to remove:");
        int idToRemove = scanner.nextInt();
        payrollSystem.removeEmployee(idToRemove);

        System.out.println("\nRemaining Employee Details:");
        payrollSystem.displayEmployees();
    }
}




