package com.posfiap.techfood.infrastructure.models.dto.errors;

import java.util.List;

public record ValidationErrorDTO(List<String> errors, int status) {
}
