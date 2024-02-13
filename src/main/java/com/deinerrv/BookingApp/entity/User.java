package com.deinerrv.BookingApp.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "name can't be blank")
    private String name;

    @NotBlank(message = "lastName can't be blank")
    private String lastName;

    @Nullable
    @Size(min = 8 , max = 8, message = "phone number's length must be 8 numbers")
    private String phone;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email is required")
    @Column(unique=true, nullable=false)
    private String email; 

    @NotBlank(message = "password can't be blank")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy="owner", fetch = FetchType.LAZY)
    private Set<Lodging> lodgings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( 
      name = "users_roles", 
      joinColumns = @JoinColumn(
        name = "user_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(
        name = "role_id", referencedColumnName = "id")) 
    private Collection<Role> roles;


    //UserDetails Methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      Set<GrantedAuthority> authorities = new HashSet<>();
      for(Role rol : roles){
        authorities.addAll(rol.getAuthorities());
      }
      return authorities;
    }

    @Override
    public String getUsername() {
      return email;
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return status == UserStatus.ACTIVE;
    }
}
