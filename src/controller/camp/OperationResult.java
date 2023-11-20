package controller.camp;

/**
 * Represents the outcome of an operation, encapsulating the result (success or failure)
 * and an associated comment or message.
 */
public class OperationResult {
    private boolean result;
    private String comment;

    /**
     * Constructs a new OperationResult with a specified result and comment.
     *
     * @param result  The outcome of the operation (true for success, false for failure).
     * @param comment A comment or message associated with the operation result.
     */
    public OperationResult(boolean result, String comment){
        this.result = result;
        this.comment = comment;
    }

    /**
     * Constructs an OperationResult with default values.
     */
    public OperationResult(){
    }

    /**
     * Retrieves the result of the operation.
     *
     * @return true if the operation was successful, false otherwise.
     */
    public boolean getLoginResult(){
        return result;
    }

    /**
     * Retrieves the comment associated with the operation result.
     *
     * @return The comment or message associated with the operation result.
     */
    public String getComment(){
        return comment;
    }

    /**
     * Sets the result of the operation.
     *
     * @param result The outcome of the operation to set (true for success, false for failure).
     */
    public void setLoginResult(boolean result){
        this.result = result;
    }

    /**
     * Sets the comment for the operation result.
     *
     * @param comment The comment or message to associate with the operation result.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
