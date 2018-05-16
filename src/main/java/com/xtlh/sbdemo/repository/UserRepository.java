package com.xtlh.sbdemo.repository;

import com.xtlh.sbdemo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long>,JpaSpecificationExecutor {
//    User findById(long id);
//    void deleteById(Long id);
//
//    @Query("select u from User u where u.username like %?1% or u.type=%?1%")
//    Page<User> findSearch(String query, Pageable pageable);

//    @Query("SELECT user from User user where ")
//    Page<User> search(@Param("searchText") String searchText, Pageable pageable);

}
