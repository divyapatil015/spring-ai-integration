package com.easyq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyq.dto.ChatRequestDto;
import com.easyq.dto.ChatResponseDto;
import com.easyq.service.AiChatService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

	private final AiChatService aiChatService;

	@PostMapping("/chat")
	public ResponseEntity<ChatResponseDto> sendChatMessage(@Valid @RequestBody ChatRequestDto request) {
		return ResponseEntity.ok(aiChatService.sendChatMessage(request));
	}
}
