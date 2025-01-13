package br.ufrn.imd.dao;

/**
 * Excecao personalizada para usuarios.
 */
public class UserException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Construtor que recebe uma mensagem de erro.
     * 
     * @param message A mensagem de erro.
     */
    public UserException(String message) {
        super(message);
    }
}
