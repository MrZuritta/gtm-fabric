package org.src.core.domain.model.TagManager;

import lombok.Builder;
import lombok.Value;
import org.src.core.domain.model.TagManager.enums.Status;

import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Value
@Builder
public class ContainerData {
    Integer id;
    String name;
    String image;
    Status status;
    Base64 containerConfig;
    Integer workspace;
    Integer vm;
    Integer creator;
    List<Integer> hostPorts;       // si BRIDGE; si HOST, documenta puertos esperados
    Map<String, String> labels;
    Instant createdAt;
    Instant startedAt;
}
