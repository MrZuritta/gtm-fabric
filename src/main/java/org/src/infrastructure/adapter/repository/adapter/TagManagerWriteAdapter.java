package org.src.infrastructure.adapter.repository.adapter;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import lombok.RequiredArgsConstructor;
import org.src.core.domain.model.TagManager.ContainerData;
import org.src.core.domain.model.TagManager.TagManager;
import org.src.core.domain.model.TagManager.enums.Status;
import org.src.core.domain.port.out.MessageReadRepositoryPort;
import org.src.infrastructure.adapter.repository.DockerHostConfigFactory;
import org.src.infrastructure.adapter.repository.converter.ContainerConverter;
import org.springframework.stereotype.Component;
import org.src.infrastructure.adapter.repository.dto.DockerConfig;
import org.src.infrastructure.credentials.CredentialResolver;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TagManagerWriteAdapter implements MessageReadRepositoryPort.TagManagerWritePort {

    private final DockerClient dockerClient;
    private final ContainerConverter converter;
    private final CredentialResolver credentialResolver;
    private final DockerHostConfigFactory dockerHostConfigFactory;

    @Override
    public ContainerData createAndStart(TagManager spec) {
// 1) Mapear spec → config base
        DockerConfig dockerConfig = converter.toDockerConfig(spec);


// 2) Resolver credenciales según capabilities
        credentialResolver.resolve(spec.getRequiredCapabilities()).ifPresent(rc -> {
            dockerConfig.getEnv().putAll(rc.env());
            dockerConfig.getBinds().add(rc.hostPath() + ":" + rc.containerPath() + ":ro");
        });


// 3) Crear contenedor
        List<String> envList = new ArrayList<>();
        dockerConfig.getEnv().forEach((k, v) -> envList.add(k + "=" + v));


        CreateContainerResponse response = dockerClient.createContainerCmd(dockerConfig.getImage())
                .withName(dockerConfig.getName())
                .withEnv(envList)
                .withLabels(dockerConfig.getLabels())
                .withHostConfig(dockerHostConfigFactory.from(dockerConfig))
                .exec();

// 4) Start
        dockerClient.startContainerCmd(response.getId()).exec();


// 5) Devolver handle
        return ContainerData.builder()
                .id(response.getId())
                .name(dockerConfig.getName())
                .image(dockerConfig.getImage())
                .status(Status.RUNNING)
                .labels(dockerConfig.getLabels())
                .hostPorts(dockerConfig.getPorts() == null ? java.util.List.of() : dockerConfig.getPorts().stream().map(DockerConfig.PortBinding::getHostPort).toList())
                .createdAt(Instant.now())
                .startedAt(Instant.now())
                .build();
    }
}

