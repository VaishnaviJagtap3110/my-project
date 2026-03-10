<<<<<<< HEAD
package com.wheelshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheelshare.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
=======

  package com.wheelshare.repository;
  
  import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
  
  import com.wheelshare.entity.User;
  
  public interface UserRepository extends JpaRepository<User, Long>{
	  Optional<User> findByEmail(String email);

	    Optional<User> findByPhone(String phone);
  
  }
 
>>>>>>> 5c855c18d81fa1f7b7ca2016c8964dc5e1318727
