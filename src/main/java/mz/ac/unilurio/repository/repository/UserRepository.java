package mz.ac.unilurio.repository.repository;

import mz.ac.unilurio.repository.model.Document;
import mz.ac.unilurio.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT d FROM User d")
    public List<User> findByUser();
}

