package dev.spring.petclinic.controller;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller // 스프링 빈으로 등록
@RequestMapping("/owners") // 요청 경로 맵핑, localhost:8080/owners로 요청할 경우 OwnerController가 담당
@RequiredArgsConstructor
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;


    // owner 추가, 갱신 기능 구현을 위한 html 파일 경로
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    // GET: localhost:8080/owners/find
    @RequestMapping(path = "/find")
    public String findOwners(Model model) {
        log.info("-- Entering GET: /find");
        log.info("-- {}", model);
        Owner owner = Owner.builder().build();
        model.addAttribute("owner", owner);

        return "owners/findOwners";
    }

    // TODO: 한 명의 Owner만 검색될 경우 해당 Owner의 디테일 페이지로 바로 이동되도록 구현
    // TODO: 2명 이상의 Owner가 검색될 경우 전체 Owner 목록으로 조회되도록
    @GetMapping
    public String processFindForm(Owner owner, BindResult result, Model model) {

        // 별도의 파라미터값 없이 /owners로 요청할 경우에는 모든 owners 데이터를 반환하도록
        if (owner.getLastName() == null)
            owner.setLastName(""); // 빈(empty) 값으로 검색할 수 있도록 공백 문자열 값 설정

        // OwnerService 호출
        List<Owner> results =
                ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        // results 객체(Model 데이터)를 ownersList.html로 전달
        // listOwners라는 이름의 key값으로 전체 Owners 목록 데이터(results)를 전달
        model.addAttribute("listOwners", results);

        return "owners/ownersList"; // owners/ownersList.html로 렌더링
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mnv = new ModelAndView("owners/ownerDetails");
        mnv.addObject(ownerService.findById(ownerId));
        return mnv;
    }

    // TODO: owner 추가, 수정 기능 직접 구현
}