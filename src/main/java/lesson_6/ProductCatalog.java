package lesson_6;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProductCatalog {
    private String название;
    private LocalDate датаПроизводства;
    private String производитель;
    private String странаПроисхождения;
    private double цена;
    private boolean забронирован;

    public ProductCatalog(String название, LocalDate датаПроизводства, String производитель, String странаПроисхождения, double цена, boolean забронирован) {
        this.название = название;
        this.датаПроизводства = датаПроизводства;
        this.производитель = производитель;
        this.странаПроисхождения = странаПроисхождения;
        this.цена = цена;
        this.забронирован = забронирован;
    }
    public void printProductInfo() {
        System.out.println("Название: " + название);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        String formattedDate = датаПроизводства.format(formatter);
        System.out.println("Дата производства: " + formattedDate);
        System.out.println("Производитель: " + производитель);
        System.out.println("Страна происхождения: " + странаПроисхождения);
        System.out.println("Цена: " + String.format("%.2f", цена));
        System.out.println("Забронирован: " + (забронирован ? "Да" : "Нет"));
    }
}
