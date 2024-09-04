package com.ahi.timecapsule.service;

import com.ahi.timecapsule.api.AlanAPI;
import com.ahi.timecapsule.api.STTAPI;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@Service
public class ApiService {

  private final AlanAPI alanAPI;
  private final STTAPI sttAPI;

  public ApiService(AlanAPI alanAPI, STTAPI sttAPI) {
    this.alanAPI = alanAPI;
    this.sttAPI = sttAPI;
  }

  public SseEmitter createContent(List<String> contents) {
    return alanAPI.get(contents);
  }

  public void auth() throws Exception {
    sttAPI.auth();
  }

  public void post() throws IOException, ParseException {
    sttAPI.post();
  }

  public List<String> get() throws IOException, ParseException, InterruptedException {
    return sttAPI.get();
  }
}
