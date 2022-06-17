package catalog.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project extends BaseEntity<Long> {
    public String name, description, members;

    @ManyToOne()
    @JoinColumn(name="managerId")
    private SoftwareDeveloper manager;
}
