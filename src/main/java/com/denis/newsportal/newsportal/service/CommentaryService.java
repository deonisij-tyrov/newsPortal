package com.denis.newsportal.newsportal.service;

import com.denis.newsportal.newsportal.converter.CommentaryConverter;
import com.denis.newsportal.newsportal.dto.CommentaryCreateDto;
import com.denis.newsportal.newsportal.dto.CommentaryDto;
import com.denis.newsportal.newsportal.entity.Commentary;
import com.denis.newsportal.newsportal.entity.News;
import com.denis.newsportal.newsportal.repository.CommentaryRepository;
import com.denis.newsportal.newsportal.repository.NewsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentaryService {

    @Autowired
    private CommentaryConverter commentaryConverter;

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private NewsRepository newsRepository;

    public String addComment(CommentaryCreateDto commentaryCreateDto) {
        CommentaryDto commentaryDto = new CommentaryDto();

        commentaryDto.setMessage(commentaryCreateDto.getMessage());
        commentaryDto.setDate(commentaryCreateDto.getDate());
        commentaryDto.setNews(commentaryCreateDto.getNews());
        commentaryDto.setAuthor(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        Commentary commentary = commentaryConverter.convertToDbo(commentaryDto);
        commentaryRepository.save(commentary);

        return "Saved";
    }

    public List<CommentaryDto> getCommentsForNews(String newsName) {
        News news = newsRepository.findByName(newsName);
        List<Commentary> allByNews = commentaryRepository.findAllByNews(news);
        return commentaryConverter.convertToDto(allByNews);
    }
}
