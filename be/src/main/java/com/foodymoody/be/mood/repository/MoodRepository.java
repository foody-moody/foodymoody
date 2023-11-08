package com.foodymoody.be.mood.repository;

import com.foodymoody.be.mood.domain.Mood;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodRepository extends JpaRepository<Mood, String> {

    Optional<Mood> findByName(String name);

}
