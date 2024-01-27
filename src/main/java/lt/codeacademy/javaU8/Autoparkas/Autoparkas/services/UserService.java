package lt.codeacademy.javaU8.Autoparkas.Autoparkas.services;

import lt.codeacademy.javaU8.Autoparkas.Autoparkas.entities.User;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    /*List<User> users;

    public UserService() {
        users = new ArrayList<>();
        addUser(new User("Tomas", "Razvanovičius", "68596532", "2024-05-20"));
        addUser(new User("Agnė", "Sidorovaitė", "1345768", "2026-05-20"));
        addUser(new User("Indrė", "Razvanovičiutė", "13465276", "2024-07-20"));
        addUser(new User("Ilona", "Čeponė", "1324565", "2030-02-10"));
    }*/
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    /*public List<User> findAllUsers() {
        return users;
    }*/
    public User addUser(User user) {
        return userRepository.save(user);
    }
    /*public User addUser(User user) {
        user.setId(getAvailableId());
        users.add(user);
        return user;
    }*/

    public void updateUser(User newUser) {
        userRepository.findById(newUser.getId()).ifPresent(userToUpdate -> userRepository.save(userToUpdate));
    }
    /*public void updateUser(User newUser) {
        for (User oldUser : users) {
            if (oldUser.getId() == (newUser.getId())) {
                oldUser.setFirstName(newUser.getFirstName());
                oldUser.setSecondName(newUser.getSecondName());
                oldUser.setLicenseNumber(newUser.getLicenseNumber());
                oldUser.setLicenseExpiryDate(newUser.getLicenseExpiryDate());
                break;
            }
        }
    }*/

    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresent(userToDelete -> userRepository.delete(userToDelete));
    }
    /*public void deleteUser(Long id) {
        getByID(id).ifPresent(userToDelete -> users.remove(userToDelete));
    }*/

    /*private Long getAvailableId() {
        if (users.isEmpty()) {
            return 0L;
        }
        return users.getLast().getId() + 1;
    }*/

    /*public Optional<User> getByID(Long id){
        for (User oldUser : users) {
            if (oldUser.getId() == (id)) {
                return Optional.of(oldUser);
            }
        }
        return Optional.empty();
    }*/
}
