package com.sai.sprinto.policy.controller.acknowledgement;

import com.sai.sprinto.policy.dto.acknowledgement.AcknowledgementRequestDto;
import com.sai.sprinto.policy.dto.common.PostResponseDto;
import com.sai.sprinto.policy.entity.sql.Acknowledgement;
import com.sai.sprinto.policy.service.acknowledgement.AcknowledgementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/u/")
@RequiredArgsConstructor
public class AcknowledgementController {

    private final AcknowledgementService acknowledgementService;

    @GetMapping("acknowledgements")
    public List<Acknowledgement> getAllAcknowledgements() {
        List<Acknowledgement> acknowledgements = acknowledgementService.getAllAcknowledgements();
        return acknowledgements;
    }

    @PostMapping("acknowledgement")
    public ResponseEntity acknowledgePolicy(@RequestBody AcknowledgementRequestDto acknowledgementRequestDto) {
        try {
            acknowledgementService.acknowledgePolicy(acknowledgementRequestDto);
            return ResponseEntity.ok(PostResponseDto
                    .builder()
                    .message("Acknowledgement Created")
                    .statusCode(HttpStatus.OK.value())
                    .build());
        } catch (Exception e) {
            return ResponseEntity.ok(PostResponseDto
                    .builder()
                    .message("Acknowledgement Creation Error")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build());
        }
    }

}
