package Lesson_11;

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
