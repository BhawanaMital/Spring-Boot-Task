package com.stackroute.Muzixapp.repository;

import com.stackroute.Muzixapp.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUp() {
        track = new Track();
        track.setTrackName("Waake");
        track.setTrackId(101);
        track.setTrackComments("Excellent");
    }

    @After
    public void tearDown(){
        trackRepository.deleteAll();
    }


    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(101,fetchTrack.getTrackId());
    }

    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track(102, "Photo", "good");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testTrack,track);
    }

    @Test
    public void testGetAllTracks(){
        Track t = new Track(102, "Photo", "good");
        Track t1 = new Track(103, "Jimmy Choo", "excellent");
        trackRepository.save(t);
        trackRepository.save(t1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Photo",list.get(0).getTrackName());
    }
}