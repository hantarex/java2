package geekbrains.java_1.lesson_4.HomeWork;


public class HomeWork {
    public static void main(String[] args) {
        Employee[] employees=new Employee[5];
        for (int i = 0; i <3 ; i++) {
            employees[i]=new Employee();
        }
        employees[3]=new Employee("Сидоров Антон Сергеевич","Бухгалтер","buh@world.yes","122",50,22);
        employees[4]=new Employee("Перцов Сергей Васильевич","Охраник","chop@world.yes","102",20,49);

        employees[0].setAge(44);
        employees[2].setAge(41);

        System.out.println("Наши сотрудники ("+Employee.count_employees+"):");
        for (int i = 0; i <employees.length ; i++) {
            employees[i].EmployeeShow();
        }

        System.out.println();
        System.out.println("Сотрудники старше 40 лет:");
        for (int i = 0; i <employees.length ; i++) {
            if(employees[i].getAge()>40) employees[i].EmployeeShow();
        }

    }
}
