package com.solvd.sportsleaguemanagement.types;

public enum MatchPhase {

    PREPARATION(false) {
        public MatchPhase next() {
            return KICK_OFF;
        }
    },

    KICK_OFF(true) {
        public MatchPhase next() {
            return FIRST_HALF;
        }
    },

    FIRST_HALF(true) {
        public MatchPhase next() {
            return HALF_TIME;
        }
    },

    HALF_TIME(false) {
        public MatchPhase next() {
            return SECOND_HALF;
        }
    },

    SECOND_HALF(true) {
        public MatchPhase next() {
            return FULL_TIME;
        }
    },

    FULL_TIME(false) {
        public MatchPhase next() {
            return FULL_TIME;
        }
    };

    private final boolean onField;

    MatchPhase(boolean onField) {
        this.onField = onField;
    }

    public boolean isOnField() {
        return onField;
    }

    public abstract MatchPhase next();
}
