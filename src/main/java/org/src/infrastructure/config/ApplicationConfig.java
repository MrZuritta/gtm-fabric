package org.src.infrastructure.config;

import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "gcp")
public class ApplicationConfig {
    private String projectId;
    private Credentials credentials = new Credentials();

    @Data
    public static class Credentials {
        private List<Bundle> bundles;
    }

    @Data
    public static class Bundle {
        private List<String> provides;
        private String hostPath;
        private String containerPath;     // ruta dentro del contenedor
        private Map<String,String> env;   // envs a inyectar
    }
}
