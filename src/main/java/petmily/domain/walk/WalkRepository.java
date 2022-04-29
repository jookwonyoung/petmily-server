package petmily.domain.walk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WalkRepository extends JpaRepository<Walk, Long> {

    @Query("SELECT w FROM Walk w ORDER BY w.id DESC")
    List<Walk> findAllDesc();

    @Query("SELECT w FROM Walk w WHERE w.email = :email")
    List<Walk> findByEmail(@Param("email") String email);
}
