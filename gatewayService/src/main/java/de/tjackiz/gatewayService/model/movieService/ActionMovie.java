package de.tjackiz.gatewayService.model.movieService;

import java.util.Objects;

public class ActionMovie extends AbstractMovie {

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
