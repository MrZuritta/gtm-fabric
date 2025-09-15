package org.src.core.domain.port.out;

import org.src.core.domain.model.TagManager.ContainerData;
import org.src.core.domain.model.TagManager.TagManager;

public interface MessageReadRepositoryPort {

    interface CreateTagManagerUseCase {
        ContainerData create(TagManager spec);
    }

    interface EventWriteRepositoryPort {
    }

    interface TagManagerWritePort {
        ContainerData createAndStart(TagManager spec);
    }
}
