package types;

public enum PaymentStatus {

    PENDING {
        public PaymentStatus markPaid() {
            return PAID;
        }
    },

    PAID {
        public PaymentStatus markPaid() {
            return PAID;
        }
    },

    FAILED {
        public PaymentStatus markPaid() {
            return FAILED;
        }
    },

    REFUNDED {
        public PaymentStatus markPaid() {
            return REFUNDED;
        }
    };

    public abstract PaymentStatus markPaid();

    public boolean isFinal() {
        return this == PAID || this == REFUNDED;
    }
}
