package com.mokujin.repository;


import com.mokujin.domain.Profile;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public interface ProfileDAO extends GraphRepository<Profile> {

    @Query("MATCH (profile:Profile)\n" +
            "    WHERE profile.email = {email}\n" +
            "        RETURN profile")
    Profile findByEmail(@Param("email") String email);

    @Query("MATCH (profile:Profile)\n" +
            "    WHERE profile.username = {username}\n" +
            "        RETURN profile")
    Profile findByUsername(@Param("username") String username);

    @Query("MATCH (profile:Profile)\n" +
            "    WHERE profile.firstName = {firstName} AND profile.lastName={lastName}\n" +
            "        RETURN profile")
    HashSet<Profile> findByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("MATCH (profile:Profile)\n" +
            "    WHERE profile.firstName = {firstName}\n" +
            "        RETURN profile")
    HashSet<Profile> findByFirstName(@Param("firstName") String firstName);

    @Query("MATCH (profile:Profile)\n" +
            "    WHERE profile.lastName = {lastName}\n" +
            "        RETURN profile")
    HashSet<Profile> findByLastName(@Param("lastName") String firstName);


    @Query("MATCH (user:Profile),(friend:Profile)\n" +
            "WHERE user.username = {user} AND friend.username = {friend}\n" +
            "CREATE (user)-[relationship:BE_FRIEND_WITH]->(friend)\n"+
            "RETURN user")
    Profile addToFriends(@Param("user") String username, @Param("friend") String friendUsername);
}
