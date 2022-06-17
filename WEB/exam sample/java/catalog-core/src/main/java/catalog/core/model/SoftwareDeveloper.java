package catalog.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SoftwareDeveloper extends BaseEntity<Long> {
    private String name;
    private String skills;
    private int age;
}
