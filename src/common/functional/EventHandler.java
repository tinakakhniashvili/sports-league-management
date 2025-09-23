package common.functional;

@FunctionalInterface
public interface EventHandler<E> {
    void handle(E event);
    default EventHandler<E> andThen(EventHandler<E> next) {
        return e -> { this.handle(e); next.handle(e); };
    }
}
