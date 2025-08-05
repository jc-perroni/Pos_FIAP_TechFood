package com.posfiap.techfood.core.domain.errors;

import java.util.List;

public record ValidationErrorDTO(List<String> errors, int status) {
}
