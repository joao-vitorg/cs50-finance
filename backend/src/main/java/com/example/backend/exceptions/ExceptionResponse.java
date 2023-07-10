package com.example.backend.exceptions;

import java.io.Serializable;
import java.time.Instant;

public record ExceptionResponse(Instant timestamp, String message, String datails) implements Serializable {
}
