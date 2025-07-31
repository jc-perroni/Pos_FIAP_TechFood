package com.posfiap.techfood.infra.models.dto.errors;

import java.util.List;

public record ValidationErrorDTO(List<String> errors, int status) {
}
