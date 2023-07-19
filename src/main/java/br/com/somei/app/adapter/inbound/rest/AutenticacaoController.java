package br.com.somei.app.adapter.inbound.rest;

import br.com.somei.app.domain.usuario.AutenticacaoDto;
import br.com.somei.app.domain.usuario.Usuario;
import br.com.somei.app.domain.usuario.UsuarioRepository;
import br.com.somei.app.domain.usuario.UsuarioService;
import br.com.somei.app.infra.security.TokenJTWDto;
import br.com.somei.app.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AutenticacaoDto autenticacaoDto){

        var authenticationToken = new UsernamePasswordAuthenticationToken(autenticacaoDto.login(), autenticacaoDto.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJTWDto(tokenJWT) );
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastrar(@RequestBody Usuario usuario) {

        var existingUser = usuarioService.loadUserByUsername(usuario.getUsername());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("Usuario já existente");
        }

        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setSenha(encodedPassword);

        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuário cadastrado com sucesso");
    }
}
