package de.tjackiz.gatewayService.model.movieService;

import de.tjackiz.gatewayService.model.common.AbstractBase;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractMovie extends AbstractBase {
    
    private String name;

    private Date releaseDate;

    private Set<Actor> actorSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Actor> getActorSet() {
        return actorSet;
    }

    public void setActorSet(Set<Actor> actorSet) {
        this.actorSet = actorSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractMovie that = (AbstractMovie) o;
        return Objects.equals(name, that.name) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(actorSet, that.actorSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, releaseDate, actorSet);
    }

    @Override
    public String toString() {
        return "AbstractMovie{" +
                "name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", actorSet=" + actorSet +
                '}';
    }
}
