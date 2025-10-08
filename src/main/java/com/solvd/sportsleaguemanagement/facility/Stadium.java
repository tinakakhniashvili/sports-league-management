package com.solvd.sportsleaguemanagement.facility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Objects;

public class Stadium extends Facility {

    private static final Logger LOGGER = LogManager.getLogger(Stadium.class);
    private static final int MIN_CAPACITY = 1000;

    private int capacity;
    private String surfaceType;
    private float pitchSlopePercent;
    private char sectorLetter;

    static {
        LogManager.getLogger(Stadium.class).debug("Stadium class loaded");
    }

    public Stadium(Integer id, String name, String location, int capacity, String surfaceType,
                   float pitchSlopePercent, char sectorLetter) {
        super(id, name, location);
        if (!isValidCapacity(capacity)) throw new IllegalArgumentException("Invalid capacity");
        this.capacity = capacity;
        this.surfaceType = Objects.requireNonNull(surfaceType, "surfaceType cannot be null");
        this.pitchSlopePercent = pitchSlopePercent;
        this.sectorLetter = sectorLetter;
    }

    @Override
    public BigDecimal getSeatFeePerTicket() {
        boolean premiumSurface = "hybrid".equalsIgnoreCase(surfaceType) || "natural".equalsIgnoreCase(surfaceType);
        return new BigDecimal(capacity >= 30000 || premiumSurface ? "3.50" : "3.00");
    }

    public static boolean isValidCapacity(int capacity) {
        return capacity >= MIN_CAPACITY;
    }

    public void hostMatch() {
        LOGGER.info("{} stadium is hosting a match with capacity {}.", getName(), capacity);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (!isValidCapacity(capacity)) throw new IllegalArgumentException("Invalid capacity");
        this.capacity = capacity;
    }

    public String getSurfaceType() {
        return surfaceType;
    }

    public void setSurfaceType(String surfaceType) {
        this.surfaceType = Objects.requireNonNull(surfaceType, "surfaceType cannot be null");
    }

    public float getPitchSlopePercent() {
        return pitchSlopePercent;
    }

    public void setPitchSlopePercent(float pitchSlopePercent) {
        this.pitchSlopePercent = pitchSlopePercent;
    }

    public char getSectorLetter() {
        return sectorLetter;
    }

    public void setSectorLetter(char sectorLetter) {
        this.sectorLetter = sectorLetter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stadium stadium)) return false;
        if (!super.equals(o)) return false;
        return capacity == stadium.capacity && Objects.equals(surfaceType, stadium.surfaceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), capacity, surfaceType);
    }

    @Override
    public String toString() {
        return String.format("%s, capacity=%d, surfaceType='%s', pitchSlopePercent=%.2f, sectorLetter=%c",
                super.toString(), capacity, surfaceType, pitchSlopePercent, sectorLetter);
    }
}
