package org.src.core.domain.usecase;

import lombok.RequiredArgsConstructor;
import org.src.core.domain.model.TagManager.ContainerData;
import org.src.core.domain.model.TagManager.RegisteredTagManager;
import org.src.core.domain.port.out.TagManagerWritePort;

@RequiredArgsConstructor
public class CreateTagManagerUseCase {

    private final TagManagerWritePort tagManagerWritePort;

    public RegisteredTagManager create(ContainerData containerData) {
        return RegisteredTagManager.builder()
    }


}
