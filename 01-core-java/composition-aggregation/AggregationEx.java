public class AggregationEx {
    
}

class Company {
    private Employee employee; // Company has an Employee (Aggregation)
    private String name;
    public Company(String name, Employee employee) {
        this.name = name;
        this.employee = employee; // Company is associated with an Employee, but does not create it
    }
    public String getName() {
        return name;
    }

}
class Employee{
    String name;
   public Employee(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
/* Employee created independently
Company just references it
Employee lifecycle is independent */