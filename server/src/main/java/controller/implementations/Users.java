package controller.implementations;
// Generated 10-Mar-2019 19:07:27 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name="users"
    ,catalog="chat"
)
public class Users  implements java.io.Serializable {


     private String phone;
     private String firstName;
     private String lastName;
     private String password;
     private String email;
     private byte[] pic;
     private String gender;
     private String country;
     private Date dateOfBirth;
     private String bio;
     private String status;
     private String registeredBy;
     private Set<UsersHaveUsers> usersHaveUsersesForPhoneB = new HashSet<UsersHaveUsers>(0);
     private Set<UsersHaveUsers> usersHaveUsersesForPhoneA = new HashSet<UsersHaveUsers>(0);

    public Users() {
    }

	
    public Users(String phone, String firstName, String lastName, String email, String gender, String country, Date dateOfBirth, String bio, String status, String registeredBy) {
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.status = status;
        this.registeredBy = registeredBy;
    }
    public Users(String phone, String firstName, String lastName, String password, String email, byte[] pic, String gender, String country, Date dateOfBirth, String bio, String status, String registeredBy, Set<UsersHaveUsers> usersHaveUsersesForPhoneB, Set<UsersHaveUsers> usersHaveUsersesForPhoneA) {
       this.phone = phone;
       this.firstName = firstName;
       this.lastName = lastName;
       this.password = password;
       this.email = email;
       this.pic = pic;
       this.gender = gender;
       this.country = country;
       this.dateOfBirth = dateOfBirth;
       this.bio = bio;
       this.status = status;
       this.registeredBy = registeredBy;
       this.usersHaveUsersesForPhoneB = usersHaveUsersesForPhoneB;
       this.usersHaveUsersesForPhoneA = usersHaveUsersesForPhoneA;
    }
   
     @Id 

    
    @Column(name="phone", unique=true, nullable=false, length=20)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    @Column(name="first_name", nullable=false, length=20)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    @Column(name="last_name", nullable=false, length=20)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    @Column(name="password", length=20)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="email", nullable=false, length=40)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="pic")
    public byte[] getPic() {
        return this.pic;
    }
    
    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    
    @Column(name="gender", nullable=false, length=20)
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    
    @Column(name="country", nullable=false, length=20)
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="date_of_birth", nullable=false, length=10)
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    
    @Column(name="bio", nullable=false, length=500)
    public String getBio() {
        return this.bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }

    
    @Column(name="status", nullable=false, length=40)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    
    @Column(name="registered_by", nullable=false, length=40)
    public String getRegisteredBy() {
        return this.registeredBy;
    }
    
    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usersByPhoneB")
    public Set<UsersHaveUsers> getUsersHaveUsersesForPhoneB() {
        return this.usersHaveUsersesForPhoneB;
    }
    
    public void setUsersHaveUsersesForPhoneB(Set<UsersHaveUsers> usersHaveUsersesForPhoneB) {
        this.usersHaveUsersesForPhoneB = usersHaveUsersesForPhoneB;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usersByPhoneA")
    public Set<UsersHaveUsers> getUsersHaveUsersesForPhoneA() {
        return this.usersHaveUsersesForPhoneA;
    }
    
    public void setUsersHaveUsersesForPhoneA(Set<UsersHaveUsers> usersHaveUsersesForPhoneA) {
        this.usersHaveUsersesForPhoneA = usersHaveUsersesForPhoneA;
    }




}


