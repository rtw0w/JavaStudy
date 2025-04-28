import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ТелефонныйСправочник {
    private Map<String, List<String>> phoneBook;

    public ТелефонныйСправочник() {
        this.phoneBook = new HashMap<>();
    }

    public void add(String фамилия, String номерТелефона) {
        if (phoneBook.containsKey(фамилия)) {
            phoneBook.get(фамилия).add(номерТелефона);
        } else {
            List<String> номера = new ArrayList<>();
            номера.add(номерТелефона);
            phoneBook.put(фамилия, номера);
        }
    }

    public List<String> get(String фамилия) {
        return phoneBook.getOrDefault(фамилия, new ArrayList<>());
    }

    public void main() {
        add("Иванов", "8-950-700-74-85");
        add("Петров", "8-916-800-11-11");
        add("Иванов", "8-924-489-58-89");
        add("Сидоров", "8-916-589-93-64");

        System.out.println("Номера телефонов для Иванова: " + get("Иванов"));
        System.out.println("Номера телефонов для Петрова: " + get("Петров"));
        System.out.println("Номера телефонов для Сидорова: " + get("Сидоров"));
    }

    public static void main(String[] args) {
        ТелефонныйСправочник справочник = new ТелефонныйСправочник();
        справочник.main();
    }
}
