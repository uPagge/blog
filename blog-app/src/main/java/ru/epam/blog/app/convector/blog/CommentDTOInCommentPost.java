package ru.epam.blog.app.convector.blog;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.epam.blog.core.entity.CommentPost;
import ru.epam.blog.core.pojo.dto.CommentDTO;

@Component
@Lazy
public class CommentDTOInCommentPost implements Converter<CommentDTO, CommentPost> {

    @Override
    public CommentPost convert(CommentDTO source) {
        CommentPost commentPost = new CommentPost();
        commentPost.setMessage(source.getMessage());
        return commentPost;
    }
}
