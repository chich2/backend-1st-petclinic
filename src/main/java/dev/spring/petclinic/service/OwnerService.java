package dev.spring.petclinic.service;

import dev.spring.petclinic.model.Owner;

import java.util.List;

public interface OwnerService {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

    Owner findById(Long ownerId);

    List<Owner> findAll();

    Owner save(Owner owner);
}