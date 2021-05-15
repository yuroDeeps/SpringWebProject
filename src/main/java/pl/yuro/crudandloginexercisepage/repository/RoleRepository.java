package pl.yuro.crudandloginexercisepage.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.yuro.crudandloginexercisepage.entity.Role;
import pl.yuro.crudandloginexercisepage.entity.User;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	@Query("SELECT u FROM Role u WHERE u.name = :rolename")
    public Role getRoleByRoleName(@Param("rolename") String rolename);
}
