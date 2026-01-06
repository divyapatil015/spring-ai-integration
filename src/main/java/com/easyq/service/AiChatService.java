package com.easyq.service;

import com.easyq.dto.ChatRequestDto;
import com.easyq.dto.ChatResponseDto;

public interface AiChatService {

	ChatResponseDto sendChatMessage(ChatRequestDto request);

}
