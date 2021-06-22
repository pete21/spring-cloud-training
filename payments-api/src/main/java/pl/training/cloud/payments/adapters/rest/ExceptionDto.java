package pl.training.cloud.payments.adapters.rest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ExceptionDto {

    @NonNull
    private String description;
    @NonNull
    private LocalDateTime timestamp;

}
