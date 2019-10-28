package br.com.alura.forum.config.security;


import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//PRECISA IMPLEMENTAR O UserDetailsService PARA AUTENTICAR NO SPRING
@Service
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //VAI CHAMAR ESSE MÉTODO QUANDO FOR AUTENTICAR
    @Override
    public UserDetails loadUserByUsername(String username) {
        return usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Dados inválidos"));
    }

}
