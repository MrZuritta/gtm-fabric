package org.src.infrastructure.adapter.repository.dto;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DockerConfig {
    String image;                     // Imagen a usar
    String name;                      // Nombre del contenedor
    Map<String, String> env;          // Variables de entorno
    Map<String, String> labels;       // Labels para identificar
    List<String> binds;               // Montajes host:container:ro/rw
    String networkMode;               // "host" o "bridge"
    List<PortBinding> ports;          // Mapeo de puertos
    HealthCheck healthcheck;          // Config de healthcheck

    @Value
    @Builder
    public static class PortBinding {
        int hostPort;
        int containerPort;
        String protocol; // tcp | udp
    }

    @Value
    @Builder
    public static class HealthCheck {
        String path;           // Ej. "/healthz"
        int intervalSeconds;
        int timeoutSeconds;
        int retries;
    }
}
