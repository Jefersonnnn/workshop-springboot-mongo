package com.jm.workshopmongo.services;

import com.jm.workshopmongo.domain.User;
import com.jm.workshopmongo.dto.UserDTO;
import com.jm.workshopmongo.repositories.UserRepository;
import com.jm.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(String id){
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()){
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return user.get();
    }

    public User insert(User obj){
        return repository.insert(obj);
    }

    public void delete(String id){
        findById(id);
        repository.deleteById(id);
    }

    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
