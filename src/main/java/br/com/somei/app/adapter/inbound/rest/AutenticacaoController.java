package br.com.somei.app.adapter.inbound.rest;

import br.com.somei.app.domain.usuario.AutenticacaoDto;
import br.com.somei.app.domain.usuario.Usuario;
import br.com.somei.app.infra.security.TokenJTWDto;
import br.com.somei.app.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenServiece;

    @PostMapping
    public ResponseEntity login(@RequestBody AutenticacaoDto autenticacaoDto){

        var authenticationToken = new UsernamePasswordAuthenticationToken(autenticacaoDto.login(), autenticacaoDto.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenServiece.generateToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJTWDto(tokenJWT) );
    }

}
