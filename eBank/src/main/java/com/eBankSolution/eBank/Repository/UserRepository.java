package com.eBankSolution.eBank.Repository;

import com.eBankSolution.eBank.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
