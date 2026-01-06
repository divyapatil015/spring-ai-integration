package com.easyq.serviceImpl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.easyq.dto.ChatRequestDto;
import com.easyq.dto.ChatResponseDto;
import com.easyq.exception.AiServiceException;
import com.easyq.service.AiChatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiChatServiceImpl implements AiChatService {

	private final ChatClient chatClient;

	@Override
	public ChatResponseDto sendChatMessage(ChatRequestDto request) {

		try {
			String aiResponse = chatClient.prompt(request.getPrompt()).call().content();

			if (aiResponse == null || aiResponse.isBlank()) {
				throw new AiServiceException("Empty AI response");
			}

			return new ChatResponseDto(aiResponse);

		} catch (Exception ex) {
			throw new AiServiceException("Service is currently unavailable , Try again after some time", ex);
		}
	}

}
