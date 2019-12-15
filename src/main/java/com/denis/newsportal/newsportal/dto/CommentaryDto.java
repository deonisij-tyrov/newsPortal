package com.denis.newsportal.newsportal.dto;

import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentaryDto {

    private String message;
    private String author;
    private Date date;
    private String news;
}
