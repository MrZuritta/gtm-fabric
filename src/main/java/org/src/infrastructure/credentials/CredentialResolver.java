package org.src.infrastructure.credentials;

import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.src.core.domain.model.TagManager.enums.Capability;//por que toma esto de la capa?
import org.src.infrastructure.config.ApplicationConfig;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CredentialResolver {

    private final ApplicationConfig cfg;

    public Optional<ResolvedCredential> resolve(Set<Capability> required) {
        // Simplificación: elegimos el primer bundle que cubra TODAS las capacidades.
        // (Recomendado: tener 1 bundle que las cubra para estos contenedores).
        for (ApplicationConfig.Bundle b : cfg.getCredentials().getBundles()) {
            Set<Capability> provides = b.getProvides().stream()
                    .map(Capability::valueOf)
                    .collect(Collectors.toCollection(() -> EnumSet.noneOf(Capability.class)));
            if (provides.containsAll(required)) {
                return Optional.of(new ResolvedCredential(
                        b.getHostPath(),
                        b.getContainerPath(),
                        b.getEnv()
                ));
            }
        }
        return Optional.empty();
    }

    public record ResolvedCredential(
            String hostPath,              // /var/run/creds/sa-writer.json (host)
            String containerPath,         // /var/run/creds/sa-writer.json (dentro del contenedor)
            Map<String,String> env        // GOOGLE_APPLICATION_CREDENTIALS, GOOGLE_CLOUD_PROJECT, etc.
    ) {}
}
