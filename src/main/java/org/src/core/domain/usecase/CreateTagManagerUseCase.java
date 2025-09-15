package org.src.core.domain.usecase;

import org.src.core.domain.model.TagManager.ContainerData;
import org.src.core.domain.model.TagManager.TagManager;
import org.src.core.domain.port.out.MessageReadRepositoryPort;
import org.src.core.domain.port.out.TagManagerReadPort;

import java.util.Base64;

public class CreateTagManagerUseCase {

    private final TagManagerReadPort tagManagerReadPort;
    private final MessageReadRepositoryPort.TagManagerWritePort tagManagerWritePort;

    public CreateTagManagerUseCase(TagManagerReadPort tagManagerReadPort, MessageReadRepositoryPort.TagManagerWritePort tagManagerWritePort) {
        this.tagManagerReadPort = tagManagerReadPort;
        this.tagManagerWritePort = tagManagerWritePort;
    }

    public TagManager create(ContainerData containerData) {
        return tagManagerWritePort.createAndStart();
    }

}
