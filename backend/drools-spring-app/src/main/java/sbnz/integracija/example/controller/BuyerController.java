package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.controller.dtos.AdviceDTO;
import sbnz.integracija.example.service.SellingService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/buyer")
public class BuyerController {

    @Autowired
    private SellingService sellingService;

    @PostMapping("/{bId}/reserve/{pId}")
    public ResponseEntity reserverProprety(@PathVariable UUID bId, @PathVariable UUID pId) {
        if(sellingService.reserveProperty(bId, pId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{bId}/reserve/{pId}")
    public ResponseEntity cancelReservation(@PathVariable UUID bId, @PathVariable UUID pId) {
        if(sellingService.cancelReservation(bId, pId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}