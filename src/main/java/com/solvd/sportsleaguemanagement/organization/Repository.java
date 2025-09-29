package com.solvd.sportsleaguemanagement.organization;

import com.solvd.sportsleaguemanagement.contracts.Identifiable;

import java.util.*;
import java.util.stream.Collectors;

public class Repository<T extends Identifiable> {

    private final Map<String, T> byId = new HashMap<>();

    public void add(T entity) {
        byId.put(entity.getId(), entity);
    }

    public T get(String id) {
        return byId.get(id);
    }

    public boolean isEmpty() {
        return byId.isEmpty();
    }

    public int size() {
        return byId.size();
    }

    public T remove(String id) {
        return byId.remove(id);
    }

    public Collection<T> all() {
        return byId.values();
    }

    public List<T> findByIds(Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return List.of();
        return ids.stream()
                .map(id -> Optional.ofNullable(byId.get(id)))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    public List<String> findAllIds() {
        return byId.keySet().stream()
                .collect(Collectors.toList());
    }
}
