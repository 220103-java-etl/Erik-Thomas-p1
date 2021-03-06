package dev.thomas.models;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li>
 *     <li>Address</li>
 * </ul>
 *
 */
public class User extends AbstractUser {

    /**
     * This includes the minimum parameters needed for the {@link dev.thomas.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */
    //For Servlets
    public User() {
        super();
    }
    //From Abstract User
    public User(int id, String username, String password, String role) {
        super(id, username, password, role);
    }


    //For UserDAO
    public User(int id, String firstName, String lastName, String username, String password, String role) {
        super(id, firstName, lastName, username, password, role);
    }

}
