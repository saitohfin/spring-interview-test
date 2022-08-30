package com.redelles.tasks.configuration;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource createPropertySource(final String name, final EncodedResource resource) throws IOException {
        final YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());

        final Properties properties = factory.getObject();

        return new PropertiesPropertySource(resource.getResource().getFilename(), properties);
    }
}
