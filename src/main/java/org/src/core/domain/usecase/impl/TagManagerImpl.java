package org.src.core.domain.usecase.impl;

import org.src.core.domain.model.TagManager.ContainerData;
import org.src.core.domain.model.TagManager.TagManager;
import org.src.core.domain.port.in.TagManagerUseCase;
import org.src.core.domain.usecase.CreateTagManagerUseCase;

import java.util.Base64;

public class TagManagerImpl implements TagManagerUseCase {

    private final CreateTagManagerUseCase createTagManagerUseCase;

    public TagManagerImpl(CreateTagManagerUseCase createTagManagerUseCase) {
        this.createTagManagerUseCase = createTagManagerUseCase;
    }

    @Override
    public TagManager create(ContainerData containerData) {
        return createTagManagerUseCase.create(containerData);
    }
}
