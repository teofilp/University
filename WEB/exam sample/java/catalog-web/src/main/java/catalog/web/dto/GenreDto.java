package catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class GenreDto extends BaseDto{
    private String name;

    @Override
    public String toString() {
        return String.format("{ id = %d, name = %s }", getId(), getName());
    }
}
