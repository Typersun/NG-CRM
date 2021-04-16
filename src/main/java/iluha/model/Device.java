package iluha.model;

import lombok.*;

import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    private int id;
    private String device;
    private Programmer programmer;
}
