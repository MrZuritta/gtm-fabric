package org.src.application.controller.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Base64;

@Value
@Builder
public class CreateTagManagerRequest {
    String name;
    Base64 containerConfig;
    Integer workspace;
    Integer vm;
    Integer creator;
}
