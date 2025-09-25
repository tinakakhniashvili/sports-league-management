package event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MatchCreatedEvent extends Event {

    private final Match match;
    private final LocalDateTime occurredAt;

    public MatchCreatedEvent(Integer id, Match match, LocalDateTime dateTime) {
        super(id, dateTime);
        this.match = match;
        this.occurredAt = LocalDateTime.now();
    }

    public MatchCreatedEvent(Integer id, Match match) {
        this(id, match, LocalDateTime.now());
    }

    @Override
    public BigDecimal priceMultiplier() {
        return BigDecimal.ONE;
    }

    public Match match() {
        return match;
    }

    public LocalDateTime occurredAt() {
        return occurredAt;
    }
}
