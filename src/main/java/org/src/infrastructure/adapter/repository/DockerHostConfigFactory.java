package org.src.infrastructure.adapter.repository;

import java.util.ArrayList;
import java.util.List;

import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.InternetProtocol;
import com.github.dockerjava.api.model.Ports;
import org.src.infrastructure.adapter.repository.dto.DockerConfig;

public class DockerHostConfigFactory {


    public static HostConfig from(DockerConfig cfg) {
        HostConfig host = HostConfig.newHostConfig()
                .withNetworkMode(cfg.getNetworkMode());
// binds
        if (cfg.getBinds() != null && !cfg.getBinds().isEmpty()) {
            List<Bind> bindList = new ArrayList<>();
            for (String b : cfg.getBinds()) {
                bindList.add(Bind.parse(b));
            }
            host.withBinds(bindList);
        }

// ports (sólo si BRIDGE)
        if (cfg.getPorts() != null && !cfg.getPorts().isEmpty()) {
            Ports portBindings = new Ports();
            List<ExposedPort> exposed = new ArrayList<>();
            for (DockerConfig.PortBinding p : cfg.getPorts()) {
                InternetProtocol proto = "udp".equalsIgnoreCase(p.getProtocol()) ? InternetProtocol.UDP : InternetProtocol.TCP;
                ExposedPort ep = new ExposedPort(p.getContainerPort(), proto);
                exposed.add(ep);
                portBindings.bind(ep, Ports.Binding.bindPort(p.getHostPort()));
            }
            host.withPortBindings(portBindings);
        }

        return host;
    }
}
