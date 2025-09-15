package org.src.core.domain.model.TagManager;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VolumeMount {
    String hostPath;
    String containerPath;
    boolean readOnly;
}
