package geekbrains.java_1.lesson_4.HomeWork;

import com.sun.org.apache.xml.internal.utils.StringBufferPool;

/**
 * Created by ashikov on 20.07.2016.
 */
public class Employee {
    public static int count_employees;
    private String fio;
    private String position;
    private String email;
    private String phone;
    private int pay;
    private int age;

    public Employee(){
        // Определяем дефолтные значения
        fio="Иванов Иван Иванович";
        position="Младший научный сотрудник";
        email="junior@world.yes";
        phone="123";
        pay=10;
        age=21;
        count_employees++;
    }

    public Employee(String fio, String position, String email, String phone, int pay, int age){
        this.fio=fio;
        this.position=position;
        this.email=email;
        this.phone=phone;
        this.pay=pay;
        this.age=age;
        count_employees++;
    }

    public void EmployeeShow(){
        System.out.println(fio + " " + position + " " + email + " телефон:"+phone+ " ЗП:"+pay+ " Возраст:"+age);
    }

    public String getFio() {
        return fio;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getPay() {
        return pay;
    }

    public int getAge() {
        return age;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
