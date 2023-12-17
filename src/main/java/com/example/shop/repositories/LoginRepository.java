package com.example.shop.repositories;
import com.example.shop.models.Login;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {

//    @Modifying
//    @Transactional
//    @Query(value = "insert into login(number,password) values (?1,?2)", nativeQuery = true)
//    void saveLogin(@Param("number") Long number, @Param("password") String password);

//    @Query(value = "SELECT * FROM Login where (number) = ?1",nativeQuery = true)
//    Optional<Login> findByNumber(@Param("number") Long number);

    @Query(value ="SELECT * FROM Login WHERE username  = ?1", nativeQuery = true)
    Login findByLogin(@Param("email") String email);

    @Query(value ="SELECT id FROM Login WHERE username = ?1", nativeQuery = true)
    Long findPasswordByEmail(@Param("email") String email);

    @Query(value = """
            select * from login where email = :email
            """, nativeQuery = true)
    Optional<Login> findByEmail(String email);

    @Modifying // для внесения изменений в бд
    @Transactional
    @Query(value = "insert into user(addres,email,first_name,sur_name,father_name,phone) " +
                   "values (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    void saveUser(@Param("addres") String addres, @Param("email") String email,
                  @Param("first_name") String first_name, @Param("sur_name")
                  String sur_name, @Param("phone") Long phone);

    @Modifying
    @Transactional
    @Query(value = """
            insert into login (email, password, active, role)
            values (:#{#login.email}, :#{#login.password}, :#{#login.active},
              :#{#login.role.name()})
            """, nativeQuery = true)
    void saveLogin(Login login);

    @Modifying // для внесения изменений в бд
    @Transactional
    @Query(value = "insert into user_role(user_id,roles) " +
            "values (?1,?2)", nativeQuery = true)
    void saveRole(@Param("user_id") Long user_id, @Param("roles") String email);


//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Login WHERE Login.user = :user")
//    void deleteByUser(User user);
//
//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Login  WHERE Login.username = :username")
//    void deleteByUsername(String username);
}

