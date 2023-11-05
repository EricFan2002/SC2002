public class LoginResult {
    private boolean result;
    private int userType;

    public boolean getLoginResult(){
        return result;
    }
    public int getUserType(){
        return userType;
    }

    public void setLoginResult(boolean result){
        this.result = result;
    }   
    public void setUserType(int userType){
        this.userType = userType;
    }
}
