package com.videostreaming.controller;

import com.videostreaming.model.Meeting;
import com.videostreaming.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/meetings")
@CrossOrigin(origins = "*") // For development; configure properly for production
public class MeetingController {

    @Autowired
    private MeetingRepository meetingRepository;

    @PostMapping("/schedule")
    public ResponseEntity<Meeting> scheduleMeeting(@RequestBody Meeting meetingRequest) {
        // Generate a unique room code like Google Meet (e.g. uuid parts)
        String roomCode = UUID.randomUUID().toString().substring(0, 8);
        meetingRequest.setRoomId(roomCode);
        
        Meeting savedMeeting = meetingRepository.save(meetingRequest);
        return ResponseEntity.ok(savedMeeting);
    }

    @GetMapping
    public ResponseEntity<List<Meeting>> getAllMeetings() {
        return ResponseEntity.ok(meetingRepository.findAll());
    }

    @GetMapping("/join/{roomId}")
    public ResponseEntity<?> joinMeeting(@PathVariable String roomId) {
        Optional<Meeting> meeting = meetingRepository.findByRoomId(roomId);
        if (meeting.isPresent()) {
            return ResponseEntity.ok(meeting.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
