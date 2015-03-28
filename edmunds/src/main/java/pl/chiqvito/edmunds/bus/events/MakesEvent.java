package pl.chiqvito.edmunds.bus.events;

import pl.chiqvito.edmunds.sdk.dto.vehicle.response.MakesDTO;

public class MakesEvent extends Event {

    private MakesDTO makes;

    public MakesEvent(MakesDTO makes) {
        this.makes = makes;
    }

    public MakesDTO getMakes() {
        return makes;
    }
}
