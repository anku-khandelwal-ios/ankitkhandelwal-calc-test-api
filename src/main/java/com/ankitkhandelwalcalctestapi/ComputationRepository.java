package com.ankitkhandelwalcalctestapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputationRepository extends JpaRepository<Computation, String> {

    @Query(value = "SELECT * FROM computation WHERE user_id = :userId ORDER BY created_at DESC LIMIT 5", nativeQuery = true)
    public List<Computation> getLast5EquationsForUser(@Param("userId") String userId);

}
