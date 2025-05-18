package br.com.ifba.infrastructure.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapperUtil {

    private static final ModelMapper MODEL_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();
        MODEL_MAPPER.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public static <D, T> D map(final T source, final Class<D> destinationType) {
        return MODEL_MAPPER.map(source, destinationType);
    }

    public static <D, T> List<D> mapList(final List<T> sourceList, final Class<D> destinationType) {
        return sourceList.stream()
                .map(entity -> MODEL_MAPPER.map(entity, destinationType))
                .collect(Collectors.toList());
    }
}
