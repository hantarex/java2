package geekbrains.java_2.lesson_3;

import java.util.HashMap;

public class PhoneBook {
    HashMap<String,PhoneEntry> phone = new HashMap<>();
    private PhoneEntry phoneEntry;
    public boolean addEntry(String phone,String lastName, String email){
        phoneEntry = new PhoneEntry(phone,lastName,email);
        this.phone.put(phoneEntry.getLastName(),phoneEntry);
        return true;
    }
    public String getPbyLN(String lastName){
        try {
            return phone.get(lastName).getPhone();
        } catch (NullPointerException e) {
            return "Ничего не найдено";
        }
    }
    public String getEbyLN(String lastName){
        try {
            return phone.get(lastName).getEmail();
        } catch (NullPointerException e) {
            return "Ничего не найдено";
        }
    }
}
