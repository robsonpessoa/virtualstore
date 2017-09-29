package br.org.rpessoa.vstore;

import br.org.rpessoa.vstore.dao.UserAccountDAO;
import br.org.rpessoa.vstore.model.UserAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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

    private static class VirtualStoreUserDetailsService implements UserDetailsService {

        private UserAccountDAO userAccountDAO;

        public VirtualStoreUserDetailsService() {
            userAccountDAO = new UserAccountDAO();
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserAccount account = userAccountDAO.findById(UserAccount.class, username);
            if(account != null) {
                return account.getUser();
            } else {
                throw new UsernameNotFoundException("The username '"
                        + username + "' doesn't exist");
            }
        }

    }
}