package com.xtlh.sbdemo.service.serviceimpl;

import com.xtlh.sbdemo.entity.User;
import com.xtlh.sbdemo.repository.UserRepository;
import com.xtlh.sbdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者 陈坤
 * @创建日期 2018/4/24
 * @功能描述 用户业务逻辑实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Qualifier("userRepository")
    @Autowired
    UserRepository userRepository;

    /**
     *
     * @param page:分页器
     * @return  分页对象
     */
    @Override
    public Page<User> findAll(int page) {
        Pageable pageable = new PageRequest(page-1, 3, new Sort(Sort.Direction.ASC, "id"));
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }

    public Page<User> findByCondition(String username, String type, int page)
    {
        Pageable pageable = new PageRequest(page-1, 3, new Sort(Sort.Direction.ASC, "id"));
        Specification<User> querySpecifi = new Specification<User>()
        {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if(!username.equals("") && null != username)
                {
                    predicates.add(criteriaBuilder.like(root.get("username"), "%"+username+"%"));
                }
                if(!type.equals("") && null != type)
                {
                    predicates.add(criteriaBuilder.equal(root.get("type"), type));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return userRepository.findAll(querySpecifi, pageable);
    }

    /*@Override
    public Page<User> findSearch(String username, Pageable pageable) {
        return null;
    }*/

}
