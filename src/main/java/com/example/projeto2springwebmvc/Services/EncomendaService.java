package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.Doacao;
import com.example.projeto2springwebmvc.models.Encomenda;
import com.example.projeto2springwebmvc.modelsHelp.LinhaEncomendas;
import com.example.projeto2springwebmvc.repositories.EncomendaRepository;
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
public class EncomendaService {
    @Autowired
    private EncomendaRepository encomendaRepository;

    public List<LinhaEncomendas> encomendasDasRoupas() {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        Connection conn = connectionUtil.criarConexao();

        String sql = "SELECT u.username, r.tiporoupa, r.tamanhoroupa, le.quantidade, f.nome, e.estadoencomenda, e.idencomenda, le.idlinhaencomenda, r.idroupadasencomendas " +
                "FROM tb_linha_encomenda le " +
                "INNER JOIN tb_encomenda e ON e.linha_encomenda_id = le.idlinhaencomenda " +
                "INNER JOIN tb_roupa_das_encomendas r ON r.linha_encomenda_id = le.idlinhaencomenda " +
                "INNER JOIN tb_fornecedor f ON f.idfornecedor = e.fornecedor_id " +
                "INNER JOIN tb_utilizador u ON u.idutilizador = e.utilizador_id ";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<LinhaEncomendas> linhaEncomendaList = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                linhaEncomendaList.add(new LinhaEncomendas(resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getString(1),
                        resultSet.getString(5), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(6)));
            }
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return linhaEncomendaList;
    }

    public void salvarEncomenda(Encomenda encomenda) {
        encomendaRepository.save(encomenda);
    }

    public Encomenda getEncomendaPorId(Integer id) {
        Optional<Encomenda> result = encomendaRepository.findById(id);

        return result.orElse(null);
    }
}
