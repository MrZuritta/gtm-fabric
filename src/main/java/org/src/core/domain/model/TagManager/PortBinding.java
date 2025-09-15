package org.src.core.domain.model.TagManager;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PortBinding {
    int hostPort;       // Ignorado si network=HOST
    int containerPort;  // Puerto interno del contenedor
    String protocol;    // "tcp" | "udp"
}
