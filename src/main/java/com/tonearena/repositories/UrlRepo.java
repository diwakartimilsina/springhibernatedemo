package com.tonearena.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tonearena.model.MyURL;
import com.tonearena.model.Song;

@Repository
public interface UrlRepo extends JpaRepository<MyURL,Long> {

}
