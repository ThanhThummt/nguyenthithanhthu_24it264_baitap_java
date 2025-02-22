package c6_7;

abstract public class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public  abstract void greets();
}
