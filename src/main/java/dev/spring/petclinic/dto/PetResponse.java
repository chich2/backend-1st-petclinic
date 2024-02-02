package dev.spring.petclinic.dto;

import dev.spring.petclinic.model.Pet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PetResponse {
    private Long id;
    private String name;

    private PetResponse(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public static PetResponse from(Pet pet) {
        final Long id = pet.getId();
        final String name = pet.getName();
        return new PetResponse(id, name);
    }
}
