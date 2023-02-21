package uz.sodiqdev.sajara.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpouseWithChildredDto {

    private Integer id;

    private String firstName;

    private List<PersonDtoForJson> children;

}
