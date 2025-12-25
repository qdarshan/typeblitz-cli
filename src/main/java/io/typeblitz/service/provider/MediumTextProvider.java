package io.typeblitz.service.provider;

import io.micronaut.core.io.ResourceResolver;
import io.micronaut.json.JsonMapper;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named("medium")
public class MediumTextProvider extends AbstractTextProvider {

    public MediumTextProvider(ResourceResolver resourceResolver, JsonMapper jsonMapper) {
        super(resourceResolver, jsonMapper);
    }

    @Override
    protected String getFileName() {
        return "medium.json";
    }
}