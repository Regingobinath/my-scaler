package com.myscaler.bms.dtos;

import com.myscaler.bms.models.Show;
import lombok.Data;

@Data
public class CreateShowResponseDTO {
    private ResponseStatus responseStatus;
    private Show show;
}
