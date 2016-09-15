package geekbrains.java_2.lesson_3;

import java.util.*;

public class MainClass {
    private static String text="наказывается штрафом в размере до трехсот тысяч рублей или в размере заработной платы или иного дохода осужденного за период до двух лет, либо обязательными работами на срок до четырехсот восьмидесяти часов, либо исправительными работами на срок до двух лет, либо принудительными работами на срок до пяти лет с ограничением свободы на срок до одного года или без такового, либо лишением свободы на срок до пяти лет с ограничением свободы на срок до одного года или без такового.";
    public static void main(String[] args) {
        HashSetWords<String> items= new HashSetWords<>(Arrays.asList(text.split("[\\s'.,]")));
        System.out.println("Слово встречается:");
        System.out.println(items.freq);
        System.out.println("Список слов:");
        System.out.println(items);

//        Вторая часть!!!!!

        String select;
        String lastName;
        String find;

        PhoneBook phonebook=new PhoneBook();
        phonebook.addEntry("+79087118901","Petrov","petrov@mail,ru");
        phonebook.addEntry("+79087118902","Petrov1","petrov1@mail,ru");
        phonebook.addEntry("+79087118903","Petrov2","petrov2@mail,ru");
        phonebook.addEntry("+79087118904","Petrov3","petrov3@mail,ru");
        phonebook.addEntry("+79087118905","Petrov4","petrov4@mail,ru");
        phonebook.addEntry("+79087118906","Petrov6","petrov6@mail,ru");

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Какой поиск использовать по базе телефона?");
            System.out.println("1) Поиск телефона по Фамилии");
            System.out.println("2) Поиск email по Фамилии");
            System.out.println("3) Выход");
            select=sc.nextLine();
            switch (select){
                case "1":
                    System.out.println("Введите фамилию для поиска телефона:");
                    lastName=sc.nextLine();
                    find=phonebook.getPbyLN(lastName);
                    System.out.println("Найден телефон: " + find);
                    break;
                case "2":
                    System.out.println("Введите фамилию для поиска email:");
                    lastName=sc.nextLine();
                    find=phonebook.getEbyLN(lastName);
                    System.out.println("Найден email: " + find);
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Нет выбраного пункта.");
            }

        } while (!select.equals("3"));
        System.out.println("Выход");
    }
}

class HashSetWords<E> extends HashSet<E>{
    private transient HashMap<E,Object> map;
    Map<String, Integer> freq = new HashMap<String, Integer>(); // кол-во повторений слов
    HashSetWords(Collection<? extends E> c) {
        map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
        boolean modified = false;
        for (E e : c) {
            if(e.equals("")) continue;
            int count = freq.containsKey(e) ? freq.get(e) : 0;
            freq.put((String)e, count + 1);
            if (add(e))
                modified = true;
        }
    }
}
