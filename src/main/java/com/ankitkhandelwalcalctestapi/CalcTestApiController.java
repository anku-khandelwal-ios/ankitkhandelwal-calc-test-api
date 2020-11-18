package com.ankitkhandelwalcalctestapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("calc-test-api/1.0/")
public class CalcTestApiController {

    @Autowired CalcTestApiService service;

    @PostMapping("users/{userId}/calculations")
    public ResponseEntity<?> performComputation(@PathVariable String userId, @Valid @RequestBody CalculationRequest request) {
        System.out.println("Calculation request: " + request.toString());
        if(!request.validate()) {
            return ResponseEntity.badRequest().build();
        }
        String result = service.compute(userId, request.getOperand1(), request.getOperand2(), Computation.Operation.toEnum(request.getOperator()));
        return ResponseEntity.ok(result);
    }

    @GetMapping("users/{userId}/calculations")
    public ResponseEntity<?> performComputation(@PathVariable String userId) {
        System.out.println("Get recent calculations request for userId: " + userId);
        List<Computation> result = service.getRecentComputations(userId);
        return ResponseEntity.ok(result);
    }

}
