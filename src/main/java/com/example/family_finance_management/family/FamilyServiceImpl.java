package com.example.family_finance_management.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.family_finance_management.user.User;
import com.example.family_finance_management.user.UserRepository;

import java.util.UUID;

@Service
public class FamilyServiceImpl implements FamilyService {

        @Autowired
        private FamilyRepository familyRepository;

        @Autowired
        private UserRepository userRepository;

        @Override
        public CreateFamilyResponseDTO createFamily(CreateFamilyRequestDTO createFamilyRequestDTO, Long familyHeadId) {
                // Find the family head (user)
                User familyHead = userRepository.findById(familyHeadId)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // Generate a unique family code
                String familyCode = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

                // Create the family
                Family family = new Family();
                family.setFamilyCode(familyCode);
                family.setFamilyName(createFamilyRequestDTO.getFamilyName());
                family.setFamilyHead(familyHead);
                family.setMemberCount(createFamilyRequestDTO.getMemberCount());

                // Save the family
                Family savedFamily = familyRepository.save(family);

                // Return the response
                return new CreateFamilyResponseDTO(savedFamily.getId(), savedFamily.getFamilyCode(),
                                savedFamily.getFamilyName());
        }

        @Override
        public JoinFamilyResponseDTO joinFamily(JoinFamilyRequestDTO joinFamilyRequestDTO, Long userId) {
                // Find the family by family code
                Family family = familyRepository.findByFamilyCode(joinFamilyRequestDTO.getFamilyCode())
                                .orElseThrow(() -> new RuntimeException("Family not found"));

                // Find the user
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // Add the user to the family (you can add a list of members to the Family
                // entity if needed)
                // For now, we'll just associate the user with the family
                user.setFamily(family); // Add a `family` field to the User entity
                userRepository.save(user);

                // Return the response
                return new JoinFamilyResponseDTO(family.getId(), family.getFamilyName());
        }
}