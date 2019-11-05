package ru.Illarionov.UsersService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.Illarionov.UsersService.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("select u from Users u where u.id = :id")
    Users findUserById (@Param("id") Long id);

    @Modifying
    @Query("update Users u set u.userStatus =:userStatus where u.id = :id")
    @Transactional
    void updateUserStatus (@Param("id") Long id, @Param("userStatus") String userStatus);

    @Modifying
    @Transactional
    @Query (value = "CALL public.proc_refresh_user_statuses()", nativeQuery = true)
    void checkLastOnline ();
}
