import java.util.Date;
import java.util.Objects;

public class User {
    private String userId;
    private String pwd;
    private String name;
    private String email;
    private String sns;
    private String address;
    private String phone_num;
    private Date create_date;

    User(){}
    public User(String userId, String pwd, String name, String email, String sns, String address, String phone_num, Date create_date) {
        this.userId = userId;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
        this.sns = sns;
        this.address = address;
        this.phone_num = phone_num;
        this.create_date = create_date;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(userId, user.userId) && Objects.equals(pwd, user.pwd) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(sns, user.sns) && Objects.equals(address, user.address) && Objects.equals(phone_num, user.phone_num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, pwd, name, email, sns, address, phone_num);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSns() {
        return sns;
    }

    public void setSns(String sns) {
        this.sns = sns;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sns='" + sns + '\'' +
                ", address='" + address + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", create_date=" + create_date +
                '}';
    }
}
