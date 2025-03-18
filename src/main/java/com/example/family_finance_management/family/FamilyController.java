package com.example.family_finance_management.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/families")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @PostMapping("/create")
    public ResponseEntity<CreateFamilyResponseDTO> createFamily(
            @RequestBody CreateFamilyRequestDTO createFamilyRequestDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        // Get the logged-in user's ID
        Long familyHeadId = Long.valueOf(userDetails.getUsername());

        CreateFamilyResponseDTO responseDTO = familyService.createFamily(createFamilyRequestDTO, familyHeadId);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/join")
    public ResponseEntity<JoinFamilyResponseDTO> joinFamily(
            @RequestBody JoinFamilyRequestDTO joinFamilyRequestDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        // Get the logged-in user's ID
        Long userId = Long.valueOf(userDetails.getUsername());

        JoinFamilyResponseDTO responseDTO = familyService.joinFamily(joinFamilyRequestDTO, userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}