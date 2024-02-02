package dev.spring.petclinic.controller;

import dev.spring.petclinic.dto.OwnerResponse;
import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static dev.spring.petclinic.dto.OwnerResponse.from;

@RequestMapping("/api/owners")
@RestController
@RequiredArgsConstructor
public class OwnerRestController {

    private final OwnerService ownerService;
    @GetMapping
    public List<OwnerResponse> listOwners() {
        List<Owner> owners = ownerService.findAll();

        // 별도의 로직을 통해 List<Owner>를 List<OwnerResponse>로 변환 처리 필요
        List<OwnerResponse> ownerResponse = owners.stream()
                .map(OwnerResponse::from)
                .collect(Collectors.toList());

        return ownerResponse;
    }

    @PostMapping
    public Owner addOwner(@RequestBody Owner owner){
        System.out.println("Owner =" + owner);

        ownerService.save(owner);
        return null;
    }
    @GetMapping(path = "/{id}")
    public OwnerResponse ownerById(@PathVariable Long id){
        Owner owner = ownerService.findById(id);
        OwnerResponse ownerResponse = OwnerResponse.from(owner);
        return ownerResponse;
    }

    @PostMapping(path="/{id}")
    public OwnerResponse modifyOwner(@RequestBody Long id,@RequestBody OwnerRequest newOwner){
        Owner owner = ownerService.findById(id);
        OwnerResponse ownerResponse = OwnerResponse.modify(owner, newOwner);

        return ownerResponse;
    }

}