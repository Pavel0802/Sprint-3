package ru.yandex.practicum.sprint_3.order;


public class Order {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String color;

    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String color) {
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
    @Override
    public String toString() {
        return "Order {" +
                "firstName:" + firstName + "," +
                "lastName:" + lastName + "," +
                "address:" + address + "," +
                "metroStation:" + metroStation + "," +
                "phone:" + phone + "," +
                "rentTime:" + rentTime + "," +
                "deliveryDate:" + deliveryDate + "," +
                "comment:" + comment + "," +
                "color:" + color + "}";
    }


}
