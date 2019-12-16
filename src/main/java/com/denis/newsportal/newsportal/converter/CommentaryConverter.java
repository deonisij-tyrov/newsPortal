package com.denis.newsportal.newsportal.converter;

import com.denis.newsportal.newsportal.dto.CommentaryDto;
import com.denis.newsportal.newsportal.entity.Commentary;
import com.denis.newsportal.newsportal.repository.NewsRepository;
import com.denis.newsportal.newsportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaryConverter implements DtoEntityConverter<CommentaryDto, Commentary> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public CommentaryDto convertToDto(Commentary commentary) {
        CommentaryDto commentaryDto = new CommentaryDto();
        commentaryDto.setAuthor(commentary.getAuthor().getLogin());
        commentaryDto.setDate(commentary.getDate());
        commentaryDto.setMessage(commentary.getMessage());
        commentaryDto.setNews(commentary.getNews().getName());
        return commentaryDto;
    }

    @Override
    public Commentary convertToDbo(CommentaryDto commentaryDto) {
        Commentary commentary = new Commentary();
        commentary.setAuthor(userRepository.findByLogin(commentaryDto.getAuthor()));
        commentary.setDate(commentaryDto.getDate());
        commentary.setMessage(commentaryDto.getMessage());
        commentary.setNews(newsRepository.findByName(commentaryDto.getNews()));
        return commentary;
    }
}
