package pl.chiqvito.edmunds.bus.events;

import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.sdk.dto.enums.StateEnum;

public class GetMakesEvent extends Event {

    private Integer year;
    private StateEnum state;

    public GetMakesEvent() {
        this(null, null);
    }

    public GetMakesEvent(Integer year, StateEnum state) {
        this.year = year;
        this.state = state;
    }

    @Override
    public String key() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        if (getState() != null) {
            sb.append(Constants.DELIMITER);
            sb.append(getState());
        }
        if (getYear() != null) {
            sb.append(Constants.DELIMITER);
            sb.append(getYear());
        }
        return sb.toString();
    }

    public Integer getYear() {
        return year;
    }

    public StateEnum getState() {
        return state;
    }
}
