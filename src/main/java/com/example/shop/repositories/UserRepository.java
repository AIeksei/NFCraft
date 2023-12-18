package com.example.shop.repositories;

import com.example.shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
@Query(value = "SELECT * FROM user where id = ?1", nativeQuery = true)
    User findByUserId(@Param("id") Long id);

@Query(value = "insert into user(addres,father_name,first_name,phone,sur_name,login_id) values (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
void saveUser(@Param("addres") String addres, @Param("father_name") String father_name, @Param("first_name") String first_name, @Param("phone") String phone,
                 @Param("sur_name") String sur_name,@Param("login_id") Long login_id);
}
