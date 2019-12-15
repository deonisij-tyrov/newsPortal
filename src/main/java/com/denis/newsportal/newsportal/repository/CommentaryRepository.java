package com.denis.newsportal.newsportal.repository;

import com.denis.newsportal.newsportal.entity.Commentary;
import com.denis.newsportal.newsportal.entity.News;
import javax.xml.stream.events.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

    List<Commentary> findAllByNewsOrderByIdDesc(final News news);
    Commentary getById(final Long id);
    List<Commentary> findAllByNews(final News news);
    List<Commentary> findByNewsAndDateBetween(final News news, final Date fromDate, final Date toDate);
}
