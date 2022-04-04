package petmily.domain.places;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlacesRepository extends JpaRepository<Places, Long> {

    @Query("SELECT p FROM Places p ORDER BY p.id DESC")
    List<Places> findAllDesc();
}
