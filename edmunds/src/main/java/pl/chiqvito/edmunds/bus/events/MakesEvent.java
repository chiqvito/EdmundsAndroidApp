package pl.chiqvito.edmunds.bus.events;

import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.MakesDTO;

public class MakesEvent extends Event {

    private MakesDTO makes;

    public MakesEvent(MakesDTO makes) {
        this.makes = makes;
    }

    @Override
    public String key() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(Constants.DELIMITER);
        sb.append(getMakes().getMakesCount());
        return sb.toString();
    }

    public MakesDTO getMakes() {
        return makes;
    }
}
