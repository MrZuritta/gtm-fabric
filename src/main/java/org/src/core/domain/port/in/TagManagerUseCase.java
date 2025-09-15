package org.src.core.domain.port.in;

import org.src.application.controller.dto.CreateTagManagerRequest;
import org.src.core.domain.model.TagManager.ContainerData;
import org.src.core.domain.model.TagManager.TagManager;

import java.util.Base64;

public interface TagManagerUseCase {
    TagManager create(ContainerData containerData);
}
