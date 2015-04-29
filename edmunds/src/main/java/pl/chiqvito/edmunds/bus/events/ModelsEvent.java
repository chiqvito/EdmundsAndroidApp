package pl.chiqvito.edmunds.bus.events;

import pl.chiqvito.edmunds.sdk.dto.vehicle.response.ModelsDTO;

public class ModelsEvent extends Event {

    private ModelsDTO models;

    public ModelsEvent(ModelsDTO models) {
        this.models = models;
    }

    public ModelsDTO getModels() {
        return models;
    }
    
}
