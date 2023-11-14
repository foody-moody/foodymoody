package com.foodymoody.be.mood.repository;

import com.foodymoody.be.mood.domain.Mood;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MoodRepository extends JpaRepository<Mood, String> {

    Optional<Mood> findByName(String name);

    @Query(value = "SELECT * FROM mood ORDER BY rand() LIMIT :count", nativeQuery = true)
    List<Mood> findRandom(int count);
}
