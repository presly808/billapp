package ua.artcode.billapp.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {

    private String phone;
    private long id;
    private String token;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public JwtUserDetails(String phone, long id, String token, List<GrantedAuthority> grantedAuthorities) {
        this.phone = phone;
        this.id = id;
        this.token = token;
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return phone;
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
        return true;
    }

    public String getPhone() {
        return phone;
    }

    public long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

}
