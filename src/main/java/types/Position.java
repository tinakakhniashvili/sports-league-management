package types;

public enum Position {

    GOALKEEPER(1, false) {
        public int trainingLoad() {
            return 60;
        }
    },

    DEFENDER(4, false) {
        public int trainingLoad() {
            return 75;
        }
    },

    MIDFIELDER(8, true) {
        public int trainingLoad() {
            return 85;
        }
    },

    FORWARD(9, true) {
        public int trainingLoad() {
            return 90;
        }
    };

    private final int typicalNumber;
    private final boolean offensive;

    Position(int typicalNumber, boolean offensive) {
        this.typicalNumber = typicalNumber;
        this.offensive = offensive;
    }

    public int number() {
        return typicalNumber;
    }

    public boolean isOffensive() {
        return offensive;
    }

    public abstract int trainingLoad();
}
