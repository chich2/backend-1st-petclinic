package dev.spring.petclinic.service;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    Impl(Implementation)의 줄임 표현
    OwnerService + Impl -> OwnerService 인터페이스를 구현한 구현체

    예전 네이밍 방식 - IOwnerService

    인터페이스 이름 - OwnerProvider
    구현체 이름 -
        검색 기능 위주의 구현체(OwnerFinder)
        그 외 추가, 수정, 삭제 기능의 구현체(OwnerManager)
 */

@Service // @Component어노테이션의 직관적 네이밍용 어노테이션
@RequiredArgsConstructor
@Qualifier("ownerService")
public class OwnerServiceImpl implements OwnerService {

    // JPA 의존성
    private final OwnerRepository ownerRepository;

    // 전체 Owner 데이터 조회 요청 예시
    public List<Owner> findAll() {
        // 기존 JPA manager.find(), manager.createQuery("select o from Owners as o").getResultList();
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);

        return owners;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }


    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Owner findById(Long ownerId) {
        Optional<Owner> ownerOptional = ownerRepository.findById(ownerId);

        return ownerOptional.orElse(null); // orElseThrow를 통한 적절한 예외 처리 권장
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }
}
