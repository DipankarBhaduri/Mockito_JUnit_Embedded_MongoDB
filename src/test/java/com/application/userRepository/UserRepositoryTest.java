package com.application.userRepository;
import com.application.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryTest extends MongoRepository <User, Integer > {

}
