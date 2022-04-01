package petmily.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import petmily.domain.posts.Posts;

public interface UserRepository extends JpaRepository<Posts, Long> {
}
