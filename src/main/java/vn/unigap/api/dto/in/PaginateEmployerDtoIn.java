package vn.unigap.api.dto.in;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaginateEmployerDtoIn {

    @NotNull(message = "Page is required")
    @Min(value = 1, message = "Page must be greater than or equal to 1")
    Integer page = 1;

    @NotNull(message = "Page size is required")
    @Min(value = 1, message = "Page size must be greater than or equal to 1")
    @Max(value = 500, message = "Page size must be less than or equal to 500")
    Integer pageSize = 10;
}
