package com.stackroute.Muzixapp.repository;

import com.stackroute.Muzixapp.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  TrackRepository extends MongoRepository<Track,Integer> {

}


