package ru.epam.blog.core.service;

import org.springframework.stereotype.Service;
import ru.epam.blog.core.domain.Post;
import ru.epam.blog.core.domain.StatusPost;
import ru.epam.blog.core.exce.InvalidBodyException;
import ru.epam.blog.core.perository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceBean implements PostService {

    private final PostRepository postRepository;

    public PostServiceBean(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post created(Post post) throws InvalidBodyException {
        if (validCreated(post)) {
            post.setStatusPost(StatusPost.PUBLISHED);
            post.setViews(0);
            post.setComments(0);
            postRepository.add(post);
            return post;
        } else {
            throw new InvalidBodyException();
        }
    }

    private boolean validCreated(Post post) {
        return (post.getTitle() != null && post.getText() != null && post.getLoginUser() != null && post.getDescription() !=null);
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }

    @Override
    public List<Post> getAllByStatus(StatusPost statusPost) {
        return new ArrayList<>(postRepository.getAllByStatus(statusPost));
    }

    @Override
    public Post getById(Integer idPost) {
        return postRepository.getById(idPost);
    }
}
