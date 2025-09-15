package org.src.core.domain.port.out;

import org.src.core.domain.model.TagManager.ContainerData;

import java.util.Optional;

public interface TagManagerReadPort {
    Optional<ContainerData> findByName(String name);
}
