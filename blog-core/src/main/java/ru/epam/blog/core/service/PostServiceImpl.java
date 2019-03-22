package ru.epam.blog.core.service;

import org.springframework.stereotype.Service;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.entity.Post;
import ru.epam.blog.core.entity.enums.PersonGroup;
import ru.epam.blog.core.entity.enums.StatusPost;
import ru.epam.blog.core.exception.AccessException;
import ru.epam.blog.core.exception.InvalidBodyException;
import ru.epam.blog.core.pojo.dto.OffsetAndCount;
import ru.epam.blog.core.repository.PostRepository;

import javax.validation.constraints.NotNull;
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
            assignDefaultValues(post);
            return postRepository.save(post);
        } else {
            throw new InvalidBodyException();
        }
    }

    @Override
    public void remove(Integer id) throws AccessException {
        Post post = postRepository.getById(id);
        if (post != null) {
            Person personAuth = authService.getPersonAuth();
            if (ownedByUser(post, personAuth) || userGroupAccess(personAuth)) {
                postRepository.delete(id);
            } else {
                throw new AccessException();
            }
        } else {
            throw new AccessException();
        }
    }

    @Override
    public List<Post> getAllByStatus(StatusPost statusPost, OffsetAndCount offsetAndCount) {
        if (offsetAndCount.getOffset() == null) {
            offsetAndCount.setOffset(0);
        }
        return new ArrayList<>(postRepository.getAllByStatus(statusPost, offsetAndCount));
    }

    @Override
    public Post getById(Integer idPost) {
        return postRepository.getById(idPost);
    }

    @Override
    public void view(Post post) throws AccessException {
        Person personAuth = authService.getPersonAuth();
        if ((StatusPost.PUBLISHED.equals(post.getStatusPost())) || (userGroupAccess(personAuth) || ownedByUser(post, personAuth))) {
            post.setViews(post.getViews() + 1);
            postRepository.save(post);
        } else {
            throw new AccessException();
        }
    }

    private void assignDefaultValues(Post post) {
        post.setPerson(authService.getPersonAuth());
        post.setStatusPost(StatusPost.PUBLISHED);
        post.setViews(0);
    }

    private boolean validCreated(Post post) {
        return (post.getTitle() != null && post.getText() != null && post.getDescription() != null);
    }

    private boolean userGroupAccess(@NotNull Person personAuth) {
        return personAuth.getPersonGroups().contains(PersonGroup.ADMIN);
    }

    private boolean ownedByUser(Post post, @NotNull Person personAuth) {
        return post.getPerson().getId().equals(personAuth.getId());
    }
}
