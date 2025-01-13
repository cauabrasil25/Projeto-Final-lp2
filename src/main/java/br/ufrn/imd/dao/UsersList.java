package br.ufrn.imd.dao;

import br.ufrn.imd.model.User;

import java.util.ArrayList;

/**
 * Esta classe representa uma lista de usuarios.
 */
public class UsersList {

    private ArrayList<User> users;

    /**
     * Construtor que inicializa a lista de usuarios.
     */
    public UsersList() {
        this.users = new ArrayList<>();
    }

    /**
     * Retorna a lista de usuarios.
     * 
     * @return A lista de usuarios.
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Define a lista de usuarios.
     * 
     * @param users A lista de usuarios.
     */
    public void setUsersList(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Adiciona um usuario a lista.
     * 
     * @param u O usuario a ser adicionado.
     */
    public void addUser(User u) {
        users.add(u);
    }

    /**
     * Encontra um usuario pelo nome de usuario.
     * 
     * @param userName O nome de usuario.
     * @return O usuario encontrado.
     * @throws UserException Se o usuario nao for encontrado.
     */
    public User findUserByUsername(String userName) throws UserException {
        for (User u : users){
            if (u.getUsername().equals(userName)){
                return u;
            }
        }
        throw new UserException("User not found");
    }
    
    /**
     * Encontra um usuario pelo nome de usuario e senha.
     * 
     * @param userName O nome de usuario.
     * @param userPassword A senha do usuario.
     * @return O usuario encontrado.
     * @throws UserException Se o usuario nao for encontrado ou a senha estiver incorreta.
     */
    public User findUser(String userName, String userPassword) throws UserException {
        User user = new User();
        for (User u : users){
            if (u.getUsername().equals(userName)){
                if (u.getPassword().equals(userPassword)){
                    user.setUsername(u.getUsername());
                    user.setPassword(u.getPassword());
                    user.setDifficulty(u.getDifficulty());
                    user.setMaxScore(u.getMaxScore());
                    return user;
                }
                throw new UserException("Incorrect password");
            }
        }
        throw new UserException("User not found");
    }

    /**
     * Atualiza as informacoes de um usuario.
     * 
     * @param user O usuario a ser atualizado.
     */
    public void updateUser(User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                u.setMaxScore(user.getMaxScore());
                u.setDifficulty(user.getDifficulty());
                break;
            }
        }
    }

    /**
     * Ordena os usuarios pela pontuacao maxima em ordem decrescente.
     */
    public void userOrdering() {
        users.sort((u1, u2) -> u2.getMaxScore() - u1.getMaxScore());
    }

}
