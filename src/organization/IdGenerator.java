package organization;

public final class IdGenerator {

    private static int counter = 1;

    private IdGenerator() {}

    public static int nextId() {
        return counter++;
    }
}
