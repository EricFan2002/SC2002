package controller.camp;

public class OperationResult {
    private boolean result;
    private String comment;
    public OperationResult(boolean result, String comment){
        this.result = result;
        this.comment = comment;
    }

    public OperationResult(){
    }

    public boolean getLoginResult(){
        return result;
    }
    public String getComment(){
        return comment;
    }

    public void setLoginResult(boolean result){
        this.result = result;
    }   
    public void setUserType(String userType){
        this.comment = comment;
    }
}
