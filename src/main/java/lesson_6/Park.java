package lesson_6;


public class Park {
    private String name;

    public Park(String name) {
        this.name = name;
    }

    class Attraction {
        private String attractionName;
        private String workingHours;
        private int price;

        public Attraction(String attractionName, String workingHours, int price) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void printInfo() {
            System.out.println("Аттракцион: " + attractionName);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + price + "Р");
        }
    }

    public void addAttraction(String attractionName, String workingHours, int price) {
        Attraction attraction = new Attraction(attractionName, workingHours, price);
        attraction.printInfo();
    }

    public void printInfo() {
        System.out.println("Название парка: " + name);
    }
}
