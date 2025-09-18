package organization;

import contracts.Identifiable;
import java.util.*;

public class Repository<T extends Identifiable> {

    private final Map<String, T> byId = new HashMap<>();

    public void add(T entity) { byId.put(entity.getId(), entity); }

    public T get(String id) { return byId.get(id); }

    public boolean isEmpty() { return byId.isEmpty(); }

    public int size() { return byId.size(); }

    public T remove(String id) { return byId.remove(id); }

    public Collection<T> all() { return byId.values(); }

}
