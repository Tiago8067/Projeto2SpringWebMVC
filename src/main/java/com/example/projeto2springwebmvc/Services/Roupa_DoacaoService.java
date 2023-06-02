package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.Doacao;
import com.example.projeto2springwebmvc.models.Roupa_Doacao;
import com.example.projeto2springwebmvc.repositories.Roupa_DoacaoRepository;
import com.example.projeto2springwebmvc.util.ConnectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class Roupa_DoacaoService {
    @Autowired
    private Roupa_DoacaoRepository roupa_doacaoRepository;

    public void salvarRoupa_Doacao(Roupa_Doacao roupa_doacao) {
        roupa_doacaoRepository.save(roupa_doacao);
    }

    public Roupa_Doacao getRoupa_DoacaoPoId(Integer id) {
        Optional<Roupa_Doacao> result = roupa_doacaoRepository.findById(id);
        /*if (doacaoEdita.isPresent()){
            return doacaoEdita.get();
        }
        return null;
        Mesma coisa que em baixo*/
        return result.orElse(null);
    }

    public Roupa_Doacao getRoupa_Doacao(Doacao doacao) {
        /*Optional<Roupa_Doacao> result = roupa_doacaoRepository.findById(id);*/
        /*if (doacaoEdita.isPresent()){
            return doacaoEdita.get();
        }
        return null;
        Mesma coisa que em baixo*/
        /*return result.orElse(null);*/
        return roupa_doacaoRepository.findByDoacoes(doacao);
    }

    public List<Roupa_Doacao> roupa_doacaoList(){
        return roupa_doacaoRepository.findAll();
    }

    public void atualizarRoupa_Doacao(Integer id, Integer quantidade) {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        Connection conn = connectionUtil.criarConexao();

        String sql = "UPDATE tb_roupa_doacao " +
                "SET quantidade = ? " +
                "WHERE id_roupa_doacao = ? ";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, quantidade);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            System.out.println("ERRO: " + sqlException.getMessage());
        }
    }
}
