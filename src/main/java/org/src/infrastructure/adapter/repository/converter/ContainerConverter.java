package org.src.infrastructure.adapter.repository.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.src.core.domain.model.TagManager.TagManager;
import org.src.infrastructure.adapter.repository.dto.DockerConfig;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ContainerConverter {

    static private final String CONTAINER_CONFIG = "CONTAINER_CONFIG";
    static private final String RUN_AS_PREVIEW_SERVER = "RUN_AS_PREVIEW_SERVER";
    static private final String PREVIEW_SERVER_URL = "PREVIEW_SERVER_URL";
    static private final String TRUE = "true";

    public DockerConfig toDockerConfig(TagManager spec) {
        Map<String, String> env = new LinkedHashMap<>();
        env.put(CONTAINER_CONFIG, spec.getContainerConfigB64());

        switch (spec.getType()) {
            case PREVIEW -> env.put(RUN_AS_PREVIEW_SERVER, TRUE);
            case TAGGING -> env.put(PREVIEW_SERVER_URL, spec.getTagging().getPreviewServerUrl());
        }

        return DockerConfig.builder()
                .image(spec.getImage())
                .name(spec.getName())
                .env(env)
                .labels(spec.getLabels())
                .networkMode(spec.getNetworkMode().name().toLowerCase())

                .ports(spec.getPortBindings().stream()
                        .map(pb -> DockerConfig.PortBinding.builder()
                                .hostPort(pb.getHostPort())
                                .containerPort(pb.getContainerPort())
                                .protocol(pb.getProtocol())
                                .build())
                        .toList())

                .binds(spec.getVolumes().stream()
                        .map(v -> v.getHostPath() + ":" + v.getContainerPath() + (v.isReadOnly() ? ":ro" : ":rw"))
                        .toList())

                .healthcheck(spec.getHealthcheck() != null ? DockerConfig.HealthCheck.builder()
                        .path(spec.getHealthcheck().getPath())
                        .intervalSeconds((int) spec.getHealthcheck().getInterval().getSeconds())
                        .timeoutSeconds((int) spec.getHealthcheck().getTimeout().getSeconds())
                        .retries(spec.getHealthcheck().getRetries())
                        .build() : null)
                .build();
    }

}
