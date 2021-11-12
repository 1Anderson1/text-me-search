package com.test.search.textme.services;

import com.test.search.textme.converters.ChatConverter;
import com.test.search.textme.converters.FileConverter;
import com.test.search.textme.entities.LiveChatEntity;
import com.test.search.textme.models.LiveChat;
import com.test.search.textme.repositories.LiveChatRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataStorageServiceImpl implements DataStorageService {

    private final FileConverter fileConverter;
    private final ChatConverter chatConverter;
    private final LiveChatRepository liveChatRepository;

    @Override
    public void initDb(@NonNull MultipartFile file) {
        List<LiveChat> liveChats = fileConverter.convert(file);
        List<LiveChatEntity> convert = chatConverter.convert(liveChats);
        liveChatRepository.saveAll(convert);
    }

    @Override
    public void searchChats(@NonNull String text) {
        //Задача предполагает поиск только по вопросам пользователей по этому айди оператора нулл
        List<LiveChatEntity> liveChatsByContent = liveChatRepository.findByContentAndOperatorId(text, null);
        List<LiveChatEntity> liveChatsByChatId = liveChatRepository.findByChatIdIn(liveChatsByContent.stream()
                .map(LiveChatEntity::getChatId)
                .collect(Collectors.toList()));
    }

}
