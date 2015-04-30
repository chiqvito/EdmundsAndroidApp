package pl.chiqvito.edmunds.bus.events;

import pl.chiqvito.edmunds.sdk.dto.vehicle.response.YearDTO;

public class GetPhotosEvent extends Event {

    private YearDTO year;

    public GetPhotosEvent(YearDTO year) {
        this.year = year;
    }

    public YearDTO getYear() {
        return year;
    }

    @Override
    public String key() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        return sb.toString();
    }
}
