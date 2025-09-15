package organization;

import exception.InvalidIdException;

public final class IdGenerator {

    private static int counter = 1;

    private IdGenerator() {}

    public static synchronized int nextId() {

        if (counter == Integer.MAX_VALUE) {
            throw new InvalidIdException("ID space exhausted.");
        }

        int id = counter++;

        if (id <= 0) {
            throw new InvalidIdException("Generated invalid id: " + id);
        }

        return id;
    }
}
