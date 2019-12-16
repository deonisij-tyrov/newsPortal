package com.denis.newsportal.newsportal.dto;

import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentaryCreateDto {

    private String message;
    private Date date;
    private String news;
}
