package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.Doacao;
import com.example.projeto2springwebmvc.modelsHelp.LinhaDoacoes;
import com.example.projeto2springwebmvc.repositories.DoacaoRepository;
import com.example.projeto2springwebmvc.util.ConnectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoacaoService {
    @Autowired
    private DoacaoRepository doacaoRepository;

    public List<LinhaDoacoes> doacoesDasRoupas() {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        Connection conn = connectionUtil.criarConexao();

        String sql = "SELECT d.idDoacao, u.username, r.tipoRoupa, r.tamanhoRoupa, rd.quantidade, d.datadadoacao " +
                "FROM tb_roupa_doacao rd " +
                "INNER JOIN tb_doacao d ON d.roupa_doacao_id = rd.id_roupa_doacao " +
                "INNER JOIN tb_roupa r ON r.roupa_doacao_id = rd.id_roupa_doacao " +
                "INNER JOIN tb_utilizador u ON u.idutilizador = d.utilizador_id ";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<LinhaDoacoes> linhaDoacoesList = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                linhaDoacoesList.add(new LinhaDoacoes(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6)));
            }
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return linhaDoacoesList;
    }

    public void salvarDoacao(Doacao doacao) {
        doacaoRepository.save(doacao);
    }

    public Doacao getDoacaoPorId(Integer id) {
        Optional<Doacao> result = doacaoRepository.findById(id);

        return result.orElse(null);
    }
}
