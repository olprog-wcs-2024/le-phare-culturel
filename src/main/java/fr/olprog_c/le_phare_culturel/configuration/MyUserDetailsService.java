package fr.olprog_c.le_phare_culturel.configuration;

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
//  @Cacheable("users")
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return this.userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne correspond à cet email"));
  }
  // Problème avec le cache. je le désactive temporairement car sinon je dois invalider le cache
  // lors d'un changement j'ai celui-ci. Exemple avec la validation par email
  //  @CacheEvict(value = "users", key = "#email")
  //  public void evictUserCache(String email) {
  //    // This method will be used to evict the user cache.
  //  }
  //
  //  public void enableUser(String email) {
  //    UserEntity user = this.userRepository.findByEmail(email)
  //            .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne correspond à cet email"));
  //    user.setUserEnabled(true);
  //    this.userRepository.save(user);
  //    evictUserCache(email);
  //  }
}
