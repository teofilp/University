package Model;

public abstract class Animal {
    public static int instances = 0;

    public Animal() throws Exception {
        instances++;
        if (instances > 5) {
            throw new Exception("to many animals");
        }
    }

    public abstract void makeSound();
}
