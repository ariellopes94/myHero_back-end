package com.myhero.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myhero.domain.Alergia;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia, Integer> {

}
