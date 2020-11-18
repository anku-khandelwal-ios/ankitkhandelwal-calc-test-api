package com.ankitkhandelwalcalctestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalcTestApiService {

    @Autowired ComputationRepository repository;

    public String compute(String userId,
                          Double operand1,
                          Double operand2,
                          Computation.Operation operation) {
        String resultString = null;
        if(operation == Computation.Operation.ADD) {
            Double result = operand1 + operand2;
            resultString = result.toString();
        } else if(operation == Computation.Operation.SUBTRACT) {
            Double result = operand1 - operand2;
            resultString =  result.toString();
        } else if(operation == Computation.Operation.MULTIPLY) {
            Double result = operand1 * operand2;
            resultString =  result.toString();
        } else if(operation == Computation.Operation.DIVIDE) {
            if(operand2 == 0) {
                resultString =  "NaN";
            } else {
                Double result = operand1 / operand2;
                resultString = result.toString();
            }
        } else if(operation == Computation.Operation.IS_NATURAL) {
            if(operand1 >= 1) {
                resultString =  "true";
            } else {
                resultString =  "false";
            }
        } else if(operation == Computation.Operation.IS_WHOLE) {
            if(operand1 >= 0) {
                resultString =  "true";
            } else {
                resultString =  "false";
            }
        } else if(operation == Computation.Operation.IS_POSITIVE) {
            if(operand1 > 0) {
                resultString =  "true";
            } else {
                resultString =  "false";
            }
        } else if(operation == Computation.Operation.IS_NEGATIVE) {
            if(operand1 < 0) {
                resultString =  "true";
            } else {
                resultString =  "false";
            }
        } else if(operation == Computation.Operation.IS_PRIME) {
            if(checkPrime(operand1)) {
                resultString =  "true";
            } else {
                resultString =  "false";
            }
        }
        if(resultString != null) {
            Computation computation = Computation.builder()
                    .leftArgument(operand1)
                    .rightArgument(operand2)
                    .operation(operation)
                    .userId(userId)
                    .result(resultString)
                    .build();
            repository.save(computation);
        }
        return resultString;
    }

    public List<Computation> getRecentComputations(String userId) {
        return repository.getLast5EquationsForUser(userId);
    }


    private boolean checkPrime(Double number) {
        if(number <= 1) return false;
        if(number.longValue() == number) {
            Long toCheck = number.longValue();
            for(int i=2;i<=toCheck/2;i++) {
                if(toCheck % i == 0) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

}
