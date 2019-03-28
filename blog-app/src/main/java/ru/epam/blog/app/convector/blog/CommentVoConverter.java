package ru.epam.blog.app.convector.blog;


import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.epam.blog.core.entity.CommentPost;
import ru.epam.blog.core.pojo.vo.CommentVO;

@Component
@Lazy
public class CommentVoConverter implements Converter<CommentPost, CommentVO> {

    @Override
    public CommentVO convert(CommentPost source) {
        return new CommentVO(
                source.getId(),
                source.getPost().getId(),
                source.getAuthor().getId(),
                source.getNumber(),
                source.getMessage(),
                source.getTimeCreate());
    }
}
