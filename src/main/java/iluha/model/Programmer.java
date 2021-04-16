package iluha.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Programmer {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private List<Device> devices;
}
