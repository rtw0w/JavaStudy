import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StudentManagement {
    public static void removeLowAchievers(Set<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3);
    }

    public static void promoteStudents(Set<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3) {
                student.setCourse(student.getCourse() + 1);
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты " + course + " курса:");
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }

    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();

        Map<String, Integer> grades1 = new HashMap<>();
        grades1.put("Стрельба", 5);
        grades1.put("Честь", 5);
        grades1.put("Совесть", 5);
        Student student1 = new Student("Артур Морган", "Группа A", 1, grades1);

        Map<String, Integer> grades2 = new HashMap<>();
        grades2.put("Стрельба", 4);
        grades2.put("Честь", 3);
        grades2.put("Совесть", 2);
        Student student2 = new Student("Джон Марстон", "Группа B", 2, grades2);

        Map<String, Integer> grades3 = new HashMap<>();
        grades3.put("Стрельба", 4);
        grades3.put("Честь", 4);
        grades3.put("Совесть", 4);
        Student student3 = new Student("Сэди Адлер", "Группа C", 1, grades3);

        Map<String, Integer> grades4 = new HashMap<>();
        grades4.put("Стрельба", 3);
        grades4.put("Честь", 2);
        grades4.put("Совесть", 3);
        Student student4 = new Student("Мика Белл", "Группа D", 3, grades4);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        System.out.println("Исходный список студентов:");
        for (Student student : students) {
            System.out.println(student);
        }

        removeLowAchievers(students);
        System.out.println("Список студентов после удаления двоечников:");
        for (Student student : students) {
            System.out.println(student);
        }

        promoteStudents(students);
        System.out.println("Список студентов после перевода на следующий курс:");
        for (Student student : students) {
            System.out.println(student);
        }

        printStudents(students, 2);
    }
}
