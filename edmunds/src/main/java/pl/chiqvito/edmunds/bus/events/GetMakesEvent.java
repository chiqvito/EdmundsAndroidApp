package pl.chiqvito.edmunds.bus.events;

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

    public Integer getYear() {
        return year;
    }

    public StateEnum getState() {
        return state;
    }
}
