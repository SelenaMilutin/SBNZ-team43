package com.ftn.sbnz.model.contract.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LabeledGraphDTO {

    private String label;
    private ArrayList<GraphDTO> graphDTOS;
}
