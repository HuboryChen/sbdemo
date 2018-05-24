package com.xtlh.sbdemo.service.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.xtlh.sbdemo.entity.User;
import com.xtlh.sbdemo.repository.UserRepository;
import com.xtlh.sbdemo.service.UserService;
import com.xtlh.sbdemo.util.PageBean;
import com.xtlh.sbdemo.util.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Pageable pageable = new PageRequest(page-1, 3, new Sort(Sort.Direction.DESC, "id"));
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }

    public Page<User> findByCondition(String username, String type, int page)
    {
        Pageable pageable = new PageRequest(page-1, 3, new Sort(Sort.Direction.DESC, "id"));
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

    public String findByCondition(int pageNumber, int pageSize)
    {
        Pageable pageable = new PageRequest(pageNumber-1, pageSize, new Sort(Sort.Direction.DESC, "id"));
        Page<User> userPage = userRepository.findAll(pageable);
        long total = userPage.getTotalElements();
        List<User> users = userPage.getContent();

        JSONObject result = new JSONObject();
        result.put("rows",users);
        result.put("total",total);

        return result.toJSONString();
    }

    @Override
    public User findByUsername(String username) {
        if(!username.trim().equals("") && username != null)
            return userRepository.findByUsername(username);
        return null;
    }

    @Override
    public void deleteInBatch(List list) {
        userRepository.deleteInBatch(list);
    }

    @Override
    public User saveUser(User user)
    {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        Optional<User> u = userRepository.findById(id);
        return u.orElse(null);  //存在即返回，不存在就返回null

    }

    @Override
    public PageBean findForPage(PageParams params) {
        Pageable pageable = new PageRequest(params.getOffset()-1, params.getLimit(), new Sort(Sort.Direction.fromString(params.getOrder()), params.getSort()));

//        Map map = params.getQueryparam();   //获取查询条件参数
//
//        Specification<User> querySpecifi = new Specification<User>()
//        {
//            @Override
//            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
//            {
//                List<Predicate> predicates = new ArrayList<Predicate>();
//                String username = map.get("username").toString();
//                String type = map.get("type").toString();
//                if(!username.equals("") && null != username)
//                {
//                    predicates.add(criteriaBuilder.like(root.get("username"), "%"+username+"%"));
//                }
//                if(!type.equals("") && null != type)
//                {
//                    predicates.add(criteriaBuilder.equal(root.get("type"), type));
//                }
//                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//            }
//        };
//        Page page = userRepository.findAll(querySpecifi, pageable);
        Page page = userRepository.findAll( pageable);
        PageBean pageBean = new PageBean(page.getTotalElements(),page.getContent());
        return pageBean;
    }



}
