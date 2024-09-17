package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.auth.Role;
import com.jamsirat.atmapi.model.auth.User;
import com.jamsirat.atmapi.model.profile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String username);

    Set<Role> findRolesById(@Param("userId") Long userId);

    @Query("select u from User u " +
            "INNER JOIN Token t on t.user.id = u.id " +
            "WHERE t.token = :token " +
            "AND (t.isTokenExpired = false or t.isRevoked = false)")
    User findByToken(String token);

    User findByUserProfile (UserProfile userProfile);
}