package contracts;

import java.time.LocalDateTime;

public interface Schedulable {

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    String getTitle();

}
