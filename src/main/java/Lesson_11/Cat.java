package Lesson_11;

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
