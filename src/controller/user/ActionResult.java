package controller.user;

/**
 * The {@code ActionResult} class encapsulates the result of a user-related
 * action, such as a login attempt.
 * It holds a boolean result indicating the success or failure of the action and
 * an integer representing the user type.
 * 
 * <p>
 * This class provides getters and setters for accessing and modifying these
 * properties.
 * 
 * @see controller.user
 */
public class ActionResult {
    private boolean result;
    private int userType;

    /**
     * Retrieves the result of the login action.
     *
     * @return boolean The result of the login action, true if successful, false
     *         otherwise.
     */
    public boolean getLoginResult() {
        return result;
    }

    /**
     * Retrieves the type of the user.
     *
     * @return int An integer representing the user type.
     */
    public int getUserType() {
        return userType;
    }

    /**
     * Sets the result of the login action.
     *
     * @param result The boolean result to set, true for a successful login, false
     *               otherwise.
     */
    public void setLoginResult(boolean result) {
        this.result = result;
    }

    /**
     * Sets the type of the user.
     *
     * @param userType An integer representing the user type to be set.
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }
}
