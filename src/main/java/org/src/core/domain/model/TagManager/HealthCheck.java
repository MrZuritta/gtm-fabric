package org.src.core.domain.model.TagManager;

import lombok.Value;
import lombok.Builder;

@Value
@Builder
public class HealthCheck {
    String path;
    java.time.Duration interval;
    java.time.Duration timeout;
    int retries;
}
