package ru.epam.blog.core.perository;

import org.springframework.stereotype.Repository;
import ru.epam.blog.core.domain.Post;
import ru.epam.blog.core.domain.StatusPost;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryMap implements PostRepository {

    private final Map<Integer, Post> postMap = new HashMap<>();
    private Integer number = 0;

    @Override
    public Post add(Post post) {
        post.setId(number);
        postMap.put(number++, post);
        return post;
    }

    @Override
    public boolean remove(Integer id) {
        if (postMap.containsKey(id)) {
            postMap.remove(id);
        }
        return false;
    }

    @Override
    public Collection<Post> getAllByStatus(StatusPost statusPost) {
        return postMap.values().parallelStream().filter(post -> post.getStatusPost().equals(statusPost)).collect(Collectors.toList());
    }

    @Override
    public Post getById(Integer idPost) {
        return postMap.get(idPost);
    }
}
