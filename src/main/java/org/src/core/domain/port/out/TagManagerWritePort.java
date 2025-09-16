package org.src.core.domain.port.out;

import org.src.core.domain.model.TagManager.ContainerData;
import org.src.core.domain.model.TagManager.TagManager;

public interface TagManagerWritePort {

    TagManager createAndStart(TagManager containerData);

}
