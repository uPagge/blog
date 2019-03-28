package ru.epam.blog.app.convector.blog;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.pojo.dto.post.PostDTO;

@Component
@Lazy
public class PostDTOInPost implements Converter<PostDTO, Post> {

    @Override
    public Post convert(PostDTO source) {
        Post post = new Post();
        post.setTitle(source.getTitle());
        post.setDescription(source.getDescription());
        post.setText(source.getText());
        return post;
    }
}
