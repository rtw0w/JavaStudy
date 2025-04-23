package Lesson_11;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    protected static int animalCount = 0;
    protected String name;
    public Animal(String name) {
        this.name = name;
        animalCount++;
    }
    public abstract void run(int distance);
    public abstract void swim(int distance);
    public static int getAnimalCount() {
        return animalCount;
    }
}
class Animals {
    public static void main(String[] args) {
        Dog dog = new Dog("Лупа");
        Cat cat = new Cat("Пупа");

        dog.run(150);
        dog.swim(5);

        cat.run(150);
        cat.swim(10);

        System.out.println("Котов: " + Cat.getCatCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Животных: " + Animal.getAnimalCount());

        Bowl bowl = new Bowl(50);
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("Элли"));
        cats.add(new Cat("Дина"));
        cats.add(new Cat("Эбби"));

        for (Cat c : cats) {
            c.eatFromBowl(bowl, 20);
        }
        System.out.println("Осталось еды в миске: " + bowl.getFoodAmount() + " г.");

        for (Cat c : cats) {
            System.out.println(c.name + " сыт: " + c.isFull());
        }
        bowl.addFood(30);
    }
}
