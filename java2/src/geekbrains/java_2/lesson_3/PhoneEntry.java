package geekbrains.java_2.lesson_3;

public class PhoneEntry {
    private String email;
    private String lastName;
    private String phone;

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        int result;
        result = lastName != null ? lastName.hashCode() : 0;

        return result;
    }

    public String getPhone() {
        return phone;
    }


    public PhoneEntry(String phone, String lastName, String email) {
        this.email = email;

        this.lastName = lastName;
        this.phone = phone;
    }
}
