package com.denis.newsportal.newsportal.repository;

import com.denis.newsportal.newsportal.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    News findByName(final String name);
    boolean existsByName(final String name);

}
