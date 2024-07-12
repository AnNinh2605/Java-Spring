package vn.unigap.api.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IdEmployerDtoIn {
    @NotNull(message = "Id employer is required")
    Long id;
}
