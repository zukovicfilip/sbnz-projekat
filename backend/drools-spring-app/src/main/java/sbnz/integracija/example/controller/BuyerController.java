package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.controller.dtos.AdviceDTO;
import sbnz.integracija.example.controller.dtos.CommissionDTO;
import sbnz.integracija.example.service.SellingService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/buyer")
public class BuyerController {

    @Autowired
    private SellingService sellingService;

    @PostMapping("/{bId}/reserve/{pId}")
    public ResponseEntity reserveProperty(@PathVariable UUID bId, @PathVariable UUID pId) {
        if(sellingService.reserveProperty(bId, pId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{bId}/reserve/{pId}/cancel")
    public ResponseEntity cancelReservation(@PathVariable UUID bId, @PathVariable UUID pId) {
        if(sellingService.cancelReservation(bId, pId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{bId}/reserve/{pId}/resolve")
    public ResponseEntity<CommissionDTO> resolveReservation(@PathVariable UUID bId, @PathVariable UUID pId) {
        CommissionDTO commissionDTO = sellingService.resolveReservation(bId, pId);
        if(commissionDTO != null) {
            return new ResponseEntity(commissionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{bId}/reserve/{pId}/refuse")
    public ResponseEntity refuseReservation(@PathVariable UUID bId, @PathVariable UUID pId) {
        if(sellingService.refuseReservation(bId, pId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
