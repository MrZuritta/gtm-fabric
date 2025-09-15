package org.src.application.controller.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.src.application.controller.dto.CreateTagManagerRequest;
import org.src.core.domain.model.TagManager.ContainerData;

@Component
@RequiredArgsConstructor
public class CreateTagManagerDTOConverter {

    public ContainerData toContainerData(CreateTagManagerRequest request) {
        return ContainerData.builder()
                .name(request.getName())
                .containerConfig(request.getContainerConfig())
                .workspace(request.getWorkspace())
                .vm(request.getVm())
                .creator(request.getCreator())
                .build();
    }
}
