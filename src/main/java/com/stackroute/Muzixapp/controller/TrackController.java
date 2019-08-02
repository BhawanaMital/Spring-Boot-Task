
package com.stackroute.Muzixapp.controller;

import com.stackroute.Muzixapp.domain.Track;
import com.stackroute.Muzixapp.exception.TrackAlreadyExistsException;
import com.stackroute.Muzixapp.exception.TrackNotFoundException;
import com.stackroute.Muzixapp.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import com.stackroute.Muzixapp.exceptions.TrackAlreadyExistsException;

@RestController
@RequestMapping(value = "api/v2")
public class TrackController {
    TrackService trackService;

    public TrackController(TrackService trackService)
    {
        this.trackService=trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException{
        ResponseEntity responseEntity;
        try{
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        }catch(TrackAlreadyExistsException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }



    @PutMapping("/tracklist")
    public ResponseEntity<?> updateTracks(@RequestBody List<Track> tracks){
        for (Track t:tracks){
            try {
                trackService.saveTrack(t);
            } catch (TrackAlreadyExistsException e) {
                e.printStackTrace();
            }

        }

        return  new ResponseEntity<List<?>>(tracks,HttpStatus.CREATED);
    }


    @GetMapping("track")
    public ResponseEntity<?> getTracks(){
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable(value="id") int id) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        try{
            trackService.getTrackById(id);
            responseEntity=new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        }catch(TrackNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.OK);
        }
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable(value="id") int id){
        trackService.deleteTrack(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("track")
    public ResponseEntity<Track> updateUser(@RequestBody Track track){
        trackService.updateTrack(track);
        return new ResponseEntity<Track>(track,HttpStatus.OK);
    }


    @PostMapping("tracks")
    public ResponseEntity<?> getTracks(@RequestBody List<Track> track) throws RuntimeException,TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        for(Track t:track)
        {
            trackService.saveTrack(t);
        }
        responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.CREATED);
        return responseEntity;
    }
}