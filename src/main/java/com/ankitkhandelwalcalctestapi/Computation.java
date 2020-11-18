package com.ankitkhandelwalcalctestapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "computation")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Computation {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;

    @NonNull
    @Column(name = "user_id")
    String userId;

    @NonNull
    @Column(name = "left_argument")
    Double leftArgument;

    @NonNull
    Operation operation;

    @Nullable
    @Column(name = "right_argument")
    Double rightArgument;

    @NonNull
    String result;

    @CreationTimestamp
    @Column(name = "created_at")
    Date createdAt;

    public enum Operation {
        ADD("ADD"),
        SUBTRACT("SUBTRACT"),
        MULTIPLY("MULTIPLY"),
        DIVIDE("DIVIDE"),
        IS_NATURAL("IS_NATURAL"),
        IS_WHOLE("IS_WHOLE"),
        IS_POSITIVE("IS_POSITIVE"),
        IS_NEGATIVE("IS_NEGATIVE"),
        IS_PRIME("IS_PRIME");

        private final String operation;

        Operation(String operation) {
            this.operation = operation;
        }

        @Override
        public String toString() {
            return operation;
        }

        public static boolean validate(Operation operation) {
            return validate(operation.toString());
        }

        public static boolean validate(String value) {
            for (Operation operation : Operation.values()) {
                if (operation.toString().compareTo(value) == 0) {
                    return true;
                }
            }
            return false;
        }

        public static Operation toEnum(String value) {
            for (Operation operation : Operation.values()) {
                if (operation.toString().compareTo(value) == 0) {
                    return operation;
                }
            }
            return null;
        }
    }

}
