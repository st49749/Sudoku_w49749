package Utilities;

public class RandomGenerator {
    public static int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }
}
