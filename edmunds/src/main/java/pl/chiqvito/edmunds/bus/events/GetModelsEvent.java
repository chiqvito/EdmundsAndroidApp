package pl.chiqvito.edmunds.bus.events;

import pl.chiqvito.edmunds.sdk.dto.vehicle.response.MakeDTO;

public class GetModelsEvent extends Event {

    private MakeDTO make;

    public GetModelsEvent(MakeDTO make) {
        this.make = make;
    }

    public MakeDTO getMake() {
        return make;
    }

}
