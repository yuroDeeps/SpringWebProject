package pl.yuro.springsandbox.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.yuro.springsandbox.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	@Query("SELECT u FROM Role u WHERE u.name = :rolename")
    public Role getRoleByRoleName(@Param("rolename") String rolename);
}
