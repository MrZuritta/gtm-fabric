package org.src.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.src.application.controller.converter.CreateTagManagerDTOConverter;
import org.src.application.controller.dto.CreateTagManagerRequest;
import org.src.core.domain.model.TagManager.ContainerData;
import org.src.core.domain.port.in.TagManagerUseCase;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/gtm")
@RequiredArgsConstructor
public class TagManagerController {

    private final TagManagerUseCase tagManagerUseCase;
    private final CreateTagManagerDTOConverter createTagManagerDTOConverter;

        @PostMapping("/containers")
        public ResponseEntity<ContainerData> createContainer(@RequestBody CreateTagManagerRequest request) {
            tagManagerUseCase.create(createTagManagerDTOConverter.toContainerData(request));
            return ResponseEntity.noContent().build();
        }
}
