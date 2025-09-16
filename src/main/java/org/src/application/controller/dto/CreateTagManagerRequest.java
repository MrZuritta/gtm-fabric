package org.src.application.controller.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateTagManagerRequest {
    Integer id;
    String name;
    String containerConfig;
    Integer workspace;
    Integer vm;
    Integer creator;
}
