package com.ahi.timecapsule.controller;

import com.ahi.timecapsule.dto.request.StoryOptionDTO;
import com.ahi.timecapsule.service.ApiService;
import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api")
public class ApiController {

  private ApiService apiService;

  private StoryOptionDTO storyOptionDTO;

  public ApiController(ApiService apiService) {
    this.apiService = apiService;
  }

  @PostMapping("/story")
  public ResponseEntity<Void> postStoryOptionDTO(StoryOptionDTO storyOptionDTO) {
    this.storyOptionDTO = storyOptionDTO;

    return ResponseEntity.status(201).build();
  }

  @GetMapping(path = "/story", produces = "text/event-stream")
  public ResponseEntity<SseEmitter> createStory()
      throws IOException, ParseException, InterruptedException {
    apiService.post(storyOptionDTO.getSoundFile());

    List<String> contents = apiService.get();

    return ResponseEntity.ok(apiService.createContent(contents, storyOptionDTO));
  }

  @GetMapping("/stt/auth")
  public ResponseEntity<String> test() throws Exception {
    apiService.auth();

    return ResponseEntity.ok("확인되었어요!");
  }
}
