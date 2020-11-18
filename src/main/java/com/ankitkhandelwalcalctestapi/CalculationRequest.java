package com.ankitkhandelwalcalctestapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculationRequest {

    @NonNull
    private Double operand1;

    @NonNull
    private String operator;

    @Nullable
    private Double operand2;

    public boolean validate() {
        if(operand1 == null) return false;
        if(!Computation.Operation.validate(operator)) return false;
        Computation.Operation operation = Computation.Operation.toEnum(operator);
        if(operation == Computation.Operation.IS_NATURAL
                || operation == Computation.Operation.IS_NEGATIVE
                || operation == Computation.Operation.IS_POSITIVE
                || operation == Computation.Operation.IS_WHOLE
                || operation == Computation.Operation.IS_PRIME) {
            return operand2 == null;
        } else {
            return operand2 != null;
        }
    }

}
