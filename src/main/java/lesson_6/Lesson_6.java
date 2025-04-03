package lesson_6;

import java.time.LocalDate;

public class Lesson_6 {

    public static void main(String[] args) {
        ProductCatalog Product = new ProductCatalog("Консоль", LocalDate.of(2024, 12, 18), "PlayStation", "Япония", 50000, false);
        Product.printProductInfo();
        ProductCatalog[] productArray = new ProductCatalog[5];
        productArray[0] = new ProductCatalog("Геймпад", LocalDate.of(2024, 7, 12), "DualSense", "Китай", 8000, true);
        productArray[1] = new ProductCatalog("Телефон", LocalDate.of(2015, 9, 25), "Apple", "Тайланд", 60000, false);
        productArray[2] = new ProductCatalog("Наушники", LocalDate.of(2025, 3, 8), "JBL", "Китай", 7000, true);
        productArray[3] = new ProductCatalog("Ноутбук", LocalDate.of(2018, 1, 7), "HP", "Китай", 45000, false);
        productArray[4] = new ProductCatalog("Телевизор", LocalDate.of(2022, 6, 2), "LG", "Китай", 30000, true);

        System.out.println("/nТовар в наличии");
        for (int a = 0; a < productArray.length; a++) {
            productArray[a].printProductInfo();
        }

        Park park = new Park("Парк Кристины");
        park.addAttraction("Японский сад", "09:00 - 18:00", 500);
        park.addAttraction("Катера и катамараны", "11:00 - 16:00", 250);
        park.addAttraction("Кольцо обозрения", "09:00 - 17:00", 1000);
        park.printInfo();
    }
}
