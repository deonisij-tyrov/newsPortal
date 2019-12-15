package com.denis.newsportal.newsportal.converter;

import com.denis.newsportal.newsportal.dto.NewsDto;
import com.denis.newsportal.newsportal.entity.News;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class NewsConverter implements DtoEntityConverter<NewsDto, News> {

    @Override
    public NewsDto convertToDto(News news) {
        final NewsDto newsDto = new NewsDto();
        newsDto.setName(news.getName());
        BeanUtils.copyProperties(news, newsDto);
        return newsDto;
    }

    @Override
    public News convertToDbo(NewsDto newsDto) {
        final News news = new News();
        BeanUtils.copyProperties(newsDto, news);
        return news;
    }
}
