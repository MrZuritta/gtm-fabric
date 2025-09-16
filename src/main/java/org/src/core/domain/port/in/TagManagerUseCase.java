package org.src.core.domain.port.in;

import org.src.core.domain.model.TagManager.ContainerData;
import org.src.core.domain.model.TagManager.RegisteredTagManager;

public interface TagManagerUseCase {
    RegisteredTagManager create(ContainerData containerData);
}
