package com.denis.newsportal.newsportal.service;

import com.denis.newsportal.newsportal.converter.NewsConverter;
import com.denis.newsportal.newsportal.dto.NewsDto;
import com.denis.newsportal.newsportal.entity.Commentary;
import com.denis.newsportal.newsportal.entity.News;
import com.denis.newsportal.newsportal.repository.CommentaryRepository;
import com.denis.newsportal.newsportal.repository.NewsRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsConverter newsConverter;

    @Autowired
    private CommentaryRepository commentaryRepository;

    public String createNews(final NewsDto newsDto) {
        if (!newsRepository.existsByName(newsDto.getName())) {
            newsRepository.save(newsConverter.convertToDbo(newsDto));
            return "Successfully added";
        } else {
            return "Already exist";
        }
    }

    public String deleteNews(final String roomName) {
        if (newsRepository.existsByName(roomName)) {
            deleteMessageFromRoom(roomName);
            newsRepository.delete(newsRepository.findByName(roomName));
            return "Successfully deleted";
        } else {
            return "News not found";
        }
    }

    public String updateNews(final NewsDto newsDto) {
        if (newsRepository.existsByName(newsDto.getName())) {
            final News news = newsRepository.findByName(newsDto.getName());
            news.setDescription(newsDto.getDescription());
            newsRepository.save(news);
            return "Successfully updated";
        } else {
            return "News not found";
        }
    }


    private void deleteMessageFromRoom(final String roomName) {
        final List<Commentary> list = commentaryRepository.findAllByNews(newsRepository.findByName(roomName));
        for (Commentary message : list) {
            commentaryRepository.delete(message);
        }
    }

    public List<NewsDto> getNews(int page, int pageSize) {
        Page<News> news = newsRepository.findAll(PageRequest.of(page, pageSize));
        List<NewsDto> newsDtoList = new ArrayList<>();
        news.get().forEach(n -> newsDtoList.add(newsConverter.convertToDto(n)));
        return newsDtoList;
    }
}
