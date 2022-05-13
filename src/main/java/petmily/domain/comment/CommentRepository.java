package petmily.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//
    @Query("SELECT c FROM Comment c ORDER BY c.id DESC")
    List<Comment> findAll();
}
