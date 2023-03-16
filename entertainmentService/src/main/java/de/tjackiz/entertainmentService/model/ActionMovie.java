package de.tjackiz.entertainmentService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

import java.util.Objects;

@Entity
@Table(name = "TABLE_ACTION_MOVIE")
public class ActionMovie extends AbstractMovie {

    @Min(0)
    private int avgBodyCount;

    public int getAvgBodyCount() {
        return avgBodyCount;
    }

    public void setAvgBodyCount(int avgBodyCount) {
        this.avgBodyCount = avgBodyCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ActionMovie that = (ActionMovie) o;
        return avgBodyCount == that.avgBodyCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), avgBodyCount);
    }

    @Override
    public String toString() {
        return "ActionMovie{" +
                "avgBodyCount=" + avgBodyCount +
                '}';
    }
}
