package org.src.core.domain.model.TagManager;

import lombok.*;
import org.src.core.domain.model.TagManager.enums.Capability;
import org.src.core.domain.model.TagManager.enums.NetworkMode;
import org.src.core.domain.model.TagManager.enums.Type;


import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Value
@Builder
@With
public class RegisteredTagManager {
    @NonNull Integer tenantId;
    @NonNull Integer id;
    @NonNull String name;
    @NonNull String image;                  // gcr.io/...:stable
    @NonNull Base64 containerConfig;
    @NonNull Integer workspace;
    @NonNull Integer vm;
    @NonNull Integer creator;

    @NonNull Type type;                     // PREVIEW | TAGGING
    TaggingSettings tagging;                // si role=TAGGING
    PreviewSettings preview;                // si role=PREVIEW

    @Singular("requires")
    Set<Capability> requiredCapabilities;   // BIGQUERY_WRITE, FIRESTORE_RW, etc.

    NetworkMode networkMode;                // HOST | BRIDGE
    @Singular("port") List<PortBinding> portBindings;
    @Singular("volume") List<VolumeMount> volumes;
    HealthCheck healthcheck;

    @Singular("label")
    Map<String, String> labels;

    String idempotencyKey;
    String imageDigest;
}
