package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PetFood {
    @JsonProperty("id")
    private Long id;

    private String name;
    private double weight;
    private String status;

    public PetFood() {}

    public PetFood(Long id, String name, double weight, String status) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("weight")
    public double getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetFood)) return false;
        PetFood petFood = (PetFood) o;
        return Objects.equals(this.id, petFood.id)  &&
                Double.compare(petFood.weight, weight) == 0 &&
                name.equals(petFood.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id) + name.hashCode();
        long temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PetFood{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", status=" + status +
                '}';
    }
}
