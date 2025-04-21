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
class Dog extends Animal {
    private static int dogCount = 0;
    public Dog(String name) {
        super(name);
        dogCount++;
    }
    @Override
    public void run(int distance) {
        if (distance <= 500) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не смог пробежать " + distance + " м.");
        }
    }
    @Override
    public void swim(int distance) {
        if (distance <= 10) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не смог проплыть " + distance + " м.");
        }
    }
    public static int getDogCount() {
        return dogCount;
    }
}
class Cat extends Animal {
    private static int catCount = 0;
    private boolean isFull = false;
    public Cat(String name) {
        super(name);
        catCount++;
    }
    @Override
    public void run(int distance) {
        if (distance <= 200) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не смог пробежать " + distance + " м.");
        }
    }
    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }
    public void eatFromBowl(Bowl bowl, int portion) {
        if (!isFull && bowl.getFoodAmount() >= portion) {
            bowl.decreaseFood(portion);
            isFull = true;
            System.out.println(name + " поел " + portion + " г. еды.");
        } else if (isFull) {
            System.out.println(name + " уже сыт.");
        } else {
            System.out.println(name + " не хватило еды.");
        }
    }
    public boolean isFull() {
        return isFull;
    }
    public static int getCatCount() {
        return catCount;
    }
}
class Bowl {
    private int foodAmount;
    public Bowl(int foodAmount) {
        this.foodAmount = Math.max(0, foodAmount);
    }
    public int getFoodAmount() {
        return foodAmount;
    }
    public void decreaseFood(int amount) {
        foodAmount = Math.max(0, foodAmount - amount);
    }
    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount += amount;
            System.out.println("Добавлено " + amount + " г. еды в миску.");
        }
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