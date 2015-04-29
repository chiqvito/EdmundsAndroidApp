package pl.chiqvito.edmunds.bus.events;

import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.MakeDTO;

public class GetModelsEvent extends Event {

    private MakeDTO make;

    public GetModelsEvent(MakeDTO make) {
        this.make = make;
    }

    @Override
    public String key() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(Constants.DELIMITER);
        sb.append(getMake().getNiceName());
        return sb.toString();
    }

    public MakeDTO getMake() {
        return make;
    }

}
