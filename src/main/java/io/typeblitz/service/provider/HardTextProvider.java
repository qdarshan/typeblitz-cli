package io.typeblitz.service.provider;

import io.micronaut.core.io.ResourceResolver;
import io.micronaut.json.JsonMapper;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named("hard")
public class HardTextProvider extends AbstractTextProvider {

    public HardTextProvider(ResourceResolver resourceResolver, JsonMapper jsonMapper) {
        super(resourceResolver, jsonMapper);
    }

    @Override
    protected String getFileName() {
        return "hard.json";
    }
}