package com.thyraxx.scrada.smashgg.repository;

import com.thyraxx.scrada.smashgg.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmashggRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findAll();
//    List<Tournament> findAllByRegistrationOpen();
//    List<Tournament> findAllByHasMessageSentAfterRegistrationOpen();
}
