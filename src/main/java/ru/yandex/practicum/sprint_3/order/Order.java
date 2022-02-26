package ru.yandex.practicum.sprint_3.order;


import ru.yandex.practicum.sprint_3.order.OrderBase;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Order {


    /*private static final String[] color = {"BLACK", "GREY"};

    public static Order generate() {
        return new Order(Generator.INSTANCE.getFaker().name().firstName(),
                Generator.INSTANCE.getFaker().name().lastName(),
                Generator.INSTANCE.getFaker().address().fullAddress(),
                String.valueOf(new Random().nextInt(237)+1),
                Generator.INSTANCE.getFaker().numerify("+7 9## ### ## ##"),
                new Random().nextInt(20)+1,
                new SimpleDateFormat("yyyy-MM-dd")
                        .format(Generator.INSTANCE.getFaker().date().future(365, TimeUnit.DAYS)),
                Generator.INSTANCE.getFaker().harryPotter().spell(),
                List.of(generateColor()));
    }

    public static Order generate(List<String> colors) {
        Order order = generate();
        order.setColor(colors);
        return order;
    }
    private static String generateColor() {
        return color[new Random().nextInt(color.length)];
    }*/

    public String firstName;
    public String lastName;
    public String address;
    public int metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    public String color;


    public Order(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, String color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public Order() {

    }
    public void greatedOrder() {
        Order order = new Order("Qfhgf", "Egjghfj", "fdh dfhs", 2, "+79991111111", 2, "2020-01-20", "ewttewtew", "GREY");
    }
}
