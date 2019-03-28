package ru.epam.blog.app.convector.blog;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.pojo.vo.post.PostVO;

@Component
@Lazy
public class PostVOConverter implements Converter<Post, PostVO> {

    @Override
    public PostVO convert(Post source) {
        return new PostVO(
                source.getId(),
                source.getTitle(),
                source.getDescription(),
                source.getText(),
                source.getViews()
        );
    }
}
