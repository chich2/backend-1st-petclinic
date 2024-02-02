package dev.spring.petclinic.dto;

import dev.spring.petclinic.model.Owner;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class OwnerRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private List<PetResponse> pets;

    @lombok.Builder
    private OwnerRequest(Long id, String firstName, String lastName, String address, String city, String telephone, List<PetResponse> pets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
    }

    public static OwnerResponse modify(Owner owner, Owner newOwner){
        final Long id = owner.getId();
        final List<PetResponse> pets = owner.getPets().stream()
                .map(PetResponse::from)
                .collect(Collectors.toList());
        return new OwnerRequest(id, newOwner.getFirstName(), newOwner.getLastName(), newOwner.getAddress(), newOwner.getCity(), newOwner.getTelephone(), pets);
    }
}