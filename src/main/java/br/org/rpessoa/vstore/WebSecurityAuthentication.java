package br.org.rpessoa.vstore;

import br.org.rpessoa.vstore.dao.GenericDAO;
import br.org.rpessoa.vstore.model.UserAccount;
import br.org.rpessoa.vstore.model.UserAccountDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Configuration
public class WebSecurityAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new VirtualStoreUserDetailsService();
    }

    private class VirtualStoreUserDetailsService implements UserDetailsService {

        private GenericDAO<UserAccount> userAccountDAO;

        public VirtualStoreUserDetailsService() {
            userAccountDAO = new GenericDAO<>();
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserAccount account = userAccountDAO.findById(UserAccount.class, username);
            if(account != null) {
                List<? extends GrantedAuthority> rules;

                switch (account.getRole()) {
                    case 1:
                        rules = AuthorityUtils.createAuthorityList("ADMIN");
                        break;
                    default :
                        rules = AuthorityUtils.createAuthorityList("USER");
                        break;
                }

                return new UserAccountDetails(account.getUser(), username,
                        account.getPassword(), rules);
            } else {
                throw new UsernameNotFoundException("The username '"
                        + username + "' doesn't exist");
            }
        }

    }
}