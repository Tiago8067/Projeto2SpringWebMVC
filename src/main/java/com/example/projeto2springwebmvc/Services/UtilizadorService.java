package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.Utilizador;
import com.example.projeto2springwebmvc.models.enums.EstadoUtilizador;
import com.example.projeto2springwebmvc.models.enums.TipoUtilizador;
import com.example.projeto2springwebmvc.repositories.UtilizadorRepository;
import com.example.projeto2springwebmvc.util.ConnectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class UtilizadorService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    public void salvarCliente(Utilizador utilizador) {
        utilizador.setEstadoUtilizador(EstadoUtilizador.ATIVO);
        utilizador.setTipoUtilizador(TipoUtilizador.CLIENTE);
        utilizadorRepository.save(utilizador);
    }

    public Optional<Utilizador> verificaDadosLogin(String username, String password) {
        return utilizadorRepository.findUtilizadorByUsernameAndPassword(username, password);
    }

    public Utilizador retornaUtilizadorLogado(String username) {
        return utilizadorRepository.findUtilizadorByUsername(username);
    }

    public Utilizador retornaUser(String username) {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        Connection conn = connectionUtil.criarConexao();

        String sql = " SELECT u.username, u.email, u.nome, u.nif, u.numerocc, u.contacto FROM tb_utilizador u" +
                "INNER JOIN tb_localizacao l ON l.idlocalizacao = u.localizacaoid " +
                " WHERE username = ?";
        Utilizador utilizador = new Utilizador();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            utilizador.setNome(rs.getString(utilizador.getNome()));
            utilizador.setEmail();
            utilizador.setPassword();
            utilizador.setContacto();
            utilizador.setNif();
            utilizador.setNumeroCc();
            utilizador.setUsername();
            utilizador.getLocalizacao().setCidade();
            utilizador.getLocalizacao().setLocalidade();
            utilizador.getLocalizacao().setCodigoPostal();

        } catch (SQLException sqlException) {
            System.out.println("ERRO: " + sqlException.getMessage());
        }
    }
}
