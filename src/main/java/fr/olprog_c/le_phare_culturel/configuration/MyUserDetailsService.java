package fr.olprog_c.le_phare_culturel.configuration;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.olprog_c.le_phare_culturel.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public MyUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Cacheable("users")
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return this.userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne correspond à cet email"));
  }
}
