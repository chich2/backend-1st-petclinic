package dev.spring.petclinic.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pets")
@Lazy
public class Pet extends BaseEntity {

    private String name;

    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    public Pet(Long id) {
        super(id);
    }


    public Pet(String name, LocalDate birthDate, Owner owner, PetType petType) {
        this.name = name;
        this.birthDate = birthDate;
        this.owner = owner;
        this.petType = petType;
    }

    public Pet(Long id, String name, LocalDate birthDate, Owner owner, PetType petType) {
        super(id);
        this.name = name;
        this.birthDate = birthDate;
        this.owner = owner;
        this.petType = petType;
    }
}