package security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
    @Query("select u from UserAccount u from u.username=:username")
    public UserAccount findByUsername(@Param("username") String username);

    @Query("select u from UserAccount u from u.password=:password")
    public UserAccount findBypassword(@Param("password") String password);
}