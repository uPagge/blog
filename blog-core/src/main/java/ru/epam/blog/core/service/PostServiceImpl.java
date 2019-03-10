package ru.epam.blog.core.service;

import org.springframework.stereotype.Service;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.PersonGroup;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.exce.AccessException;
import ru.epam.blog.core.exce.InvalidBodyException;
import ru.epam.blog.core.perository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final AuthService authService;
    private final PostRepository postRepository;

    public PostServiceImpl(AuthService authService, PostRepository postRepository) {
        this.authService = authService;
        this.postRepository = postRepository;
    }

    @Override
    public Post created(Post post) throws InvalidBodyException {
        if (validCreated(post)) {
            post.setPerson(authService.getPersonAuth());
            post.setStatusPost(StatusPost.PUBLISHED);
            post.setViews(0);
            return postRepository.save(post);
        } else {
            throw new InvalidBodyException();
        }
    }

    private boolean validCreated(Post post) {
        return (post.getTitle() != null && post.getText() != null && post.getDescription() != null);
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

    @Override
    public void view(Post post, Person person) throws AccessException {
        if (person.getPersonGroups().contains(PersonGroup.ADMIN) || person.getLogin().equals(post.getPerson().getLogin()) || post.getStatusPost().equals(StatusPost.PUBLISHED)) {
            post.setViews(post.getViews() + 1);
            postRepository.save(post);
        } else {
            throw new AccessException();
        }
    }
}
