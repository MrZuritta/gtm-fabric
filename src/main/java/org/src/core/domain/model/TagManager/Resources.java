package org.src.core.domain.model.TagManager;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Resources {
    Integer cpuShares;
    Integer memoryMB;
}
