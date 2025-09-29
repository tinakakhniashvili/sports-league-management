package types;

public enum Severity {

    LOW(1), MEDIUM(2), HIGH(3), CRITICAL(5);

    private final int weight;

    Severity(int weight) {
        this.weight = weight;
    }

    public int weight() {
        return weight;
    }

    public Severity combine(Severity other) {
        int sum = this.weight + other.weight;
        if (sum >= 7) return CRITICAL;
        if (sum >= 5) return HIGH;
        if (sum >= 3) return MEDIUM;
        return LOW;
    }
}
