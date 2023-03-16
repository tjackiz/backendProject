package de.tjackiz.entertainmentService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

import java.util.Objects;

@Entity
@Table(name = "TABLE_HORROR_MOVIE")
public class HorrorMovie extends AbstractMovie {

    @Min(0)
    private int avgScreamDecibel;

    public int getAvgScreamDecibel() {
        return avgScreamDecibel;
    }

    public void setAvgScreamDecibel(int avgScreamDecibel) {
        this.avgScreamDecibel = avgScreamDecibel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HorrorMovie that = (HorrorMovie) o;
        return avgScreamDecibel == that.avgScreamDecibel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), avgScreamDecibel);
    }

    @Override
    public String toString() {
        return "HorrorMovie{" +
                "avgScreamDecibel=" + avgScreamDecibel +
                '}';
    }
}
