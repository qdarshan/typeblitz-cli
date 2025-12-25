package io.typeblitz.service.provider;

import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import io.micronaut.core.type.Argument;
import io.micronaut.json.JsonMapper;
import io.typeblitz.model.Text;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractTextProvider implements TextProvider {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractTextProvider.class);

    protected List<Text> texts = new ArrayList<>();

    private final ResourceResolver resourceResolver;
    private final JsonMapper jsonMapper;

    protected AbstractTextProvider(ResourceResolver resourceResolver, JsonMapper jsonMapper) {
        this.resourceResolver = resourceResolver;
        this.jsonMapper = jsonMapper;
    }

    protected abstract String getFileName();

    @Override
    public String generateText(long targetLength) {
        if (texts.isEmpty()) {
            return "Error: Data not loaded for " + getFileName();
        }

        StringBuilder buffer = new StringBuilder();
        while (buffer.length() < targetLength) {
            int index = ThreadLocalRandom.current().nextInt(texts.size());
            String segment = texts.get(index).getSentence();

            if (!buffer.isEmpty()) {
                buffer.append(" ");
            }
            buffer.append(segment);
        }
        return buffer.toString();
    }

    @PostConstruct
    public void load() {
        String path = "classpath:data/" + getFileName();

        Optional<InputStream> stream = resourceResolver
                .getLoader(ClassPathResourceLoader.class)
                .flatMap(loader -> loader.getResourceAsStream(path));

        if (stream.isPresent()) {
            try (InputStream inputStream = stream.get()) {
                this.texts = jsonMapper.readValue(inputStream, Argument.listOf(Text.class));
                LOG.info("Loaded {} items from {}", texts.size(), path);
            } catch (IOException e) {
                LOG.error("Failed to parse {}", path, e);
                throw new RuntimeException("Data corruption in " + path, e);
            }
        } else {
            LOG.error("File not found: {}", path);
        }
    }
}
