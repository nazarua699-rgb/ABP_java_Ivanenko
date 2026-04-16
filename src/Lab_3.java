//Завдання 1
interface Run{
    void transportName();
    int getSpeed();
    int getMass();
    int getPassengers();
    int getWheels();
        }
        //Завдання 2
        abstract class Machine implements Run {
            public static final int MAX_SPEED = 200;
            public static final int MIN_SPEED = 0;
            public static final int MAX_PASSANGERS = 100;
            public static final int MIN_PASSANGERS = 0;

            protected String name;
            protected int currentspeed;

            public Machine(String name, int speed) {
                this.name = name;
                this.currentspeed = speed;
            }

            @Override
            public void transportName() {
                System.out.println("Назва транспортного засобу: " + name);
            }
        }
            //Завдання 3
            abstract class Trailer extends Machine {
                public Trailer(String name, int speed) {
                    super(name, speed);
                }
            }

            class SelfPropelledTrailer extends Trailer {
                private int mass;
                private int passengers;
                private int wheels;

                public SelfPropelledTrailer(String name, int speed, int mass, int passengers, int wheels) {
                    super(name, speed);
                    this.mass = mass;
                    this.passengers = passengers;
                    this.wheels = wheels;
                }

                @Override
                public int getSpeed() {
                    return this.currentspeed;
                }

                @Override
                public int getMass() {
                    return this.mass;
                }

                @Override
                public int getPassengers() {
                    return this.passengers;
                }

                @Override
                public int getWheels() {
                    return this.wheels;
                }

                public void printInfo() {
                    transportName();
                    System.out.println("Швидкість: " + getSpeed() + " км/год");
                    System.out.println("Маса: " + getMass() + " кг");
                    System.out.println("Кількість пасажирів: " + getPassengers());
                    System.out.println("Кількість коліс: " + getWheels());
                    System.out.println("Тип: Самохідний трейлер ");
                    System.out.println("---------------------------------");
                }
            }

            class TowedTrailer extends Trailer {
                private int mass;
                private int passengers;
                private int wheels;

                public TowedTrailer(String name, int speed, int mass, int passengers, int wheels) {
                    super(name, speed);
                    this.mass = mass;
                    this.passengers = passengers;
                    this.wheels = wheels;
                }

                @Override
                public int getSpeed() {
                    return this.currentspeed;
                }

                @Override
                public int getMass() {
                    return this.mass;
                }

                @Override
                public int getPassengers() {
                    return this.passengers;
                }

                @Override
                public int getWheels() {
                    return this.wheels;
                }

                public void printInfo() {
                    transportName();
                    System.out.println("Швидкість: " + getSpeed() + " км/год");
                    System.out.println("Маса: " + getMass() + " кг");
                    System.out.println("Кількість пасажирів: " + getPassengers());
                    System.out.println("Кількість коліс: " + getWheels());
                    System.out.println("Тип: причіпний трейлер ");
                }
            }
            //Завдання 4
            public class Lab_3 {
                public static void main(String[] args) {
                    SelfPropelledTrailer selfPropelled = new SelfPropelledTrailer("Самохідний трейлер", 100, 5000, 4, 4);
                    TowedTrailer towed = new TowedTrailer("Причіпний трейлер", 60, 3000, 2, 2);

                    selfPropelled.printInfo();
                    towed.printInfo();
                }
            }