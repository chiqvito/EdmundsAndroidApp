package pl.chiqvito.edmunds.bus.events;

import pl.chiqvito.edmunds.Constants;
import pl.chiqvito.edmunds.sdk.dto.vehicle.response.ModelsDTO;

public class ModelsEvent extends Event {

    private ModelsDTO models;

    public ModelsEvent(ModelsDTO models) {
        this.models = models;
    }

    @Override
    public String key() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(Constants.DELIMITER);
        sb.append(getModels().getModelsCount());
        return sb.toString();
    }

    public ModelsDTO getModels() {
        return models;
    }

}
