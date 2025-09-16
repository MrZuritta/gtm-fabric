package org.src.core.domain.usecase.impl;

import org.src.core.domain.model.TagManager.ContainerData;
import org.src.core.domain.model.TagManager.RegisteredTagManager;
import org.src.core.domain.port.in.TagManagerUseCase;
import org.src.core.domain.usecase.CreateTagManagerUseCase;

public class TagManagerImpl implements TagManagerUseCase {

    private final CreateTagManagerUseCase createTagManagerUseCase;

    public TagManagerImpl(CreateTagManagerUseCase createTagManagerUseCase) {
        this.createTagManagerUseCase = createTagManagerUseCase;
    }

    @Override
    public RegisteredTagManager create(ContainerData containerData) {
        return createTagManagerUseCase.create(containerData);
    }
}
