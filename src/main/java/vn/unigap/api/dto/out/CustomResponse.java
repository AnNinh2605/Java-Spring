package vn.unigap.api.dto.out;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomResponse<T> {
    int errorCode;
    int statusCode;
    String message;
    T object;
}
