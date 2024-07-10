package vn.unigap.api.dto.in;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateEmployerDtoIn {
    @NotBlank(message = "Please provide email address")
    @Email(message = "Please provide valid email address")
    @Size(max = 255, message = "Email must be less than 255 characters")
    String email;

    @NotBlank(message = "Please provide name")
    @Size(max = 255, message = "Name must be less than 255 characters")
    String name;

    @NotNull(message = "Please provide province")
    @Min(value = 1, message = "Invalid provinceId")
    Integer provinceId;
}
